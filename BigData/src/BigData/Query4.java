package BigData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * List the 'user id' and 'rating' of users that reviewed businesses located in 
 * Stanford Required files are 'business'  and 'review'.
 * @author nikitakothari
 *
 */
public class Query4 {

	public static class Map extends Mapper<LongWritable, Text, Text, Text> {
		HashSet<String> businessIdSet = new HashSet<String>();

		@Override
		protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			Configuration conf = context.getConfiguration();
			String businessDetailsFile = conf.get("businessDetailsFile");
			// e.g /user/hue/input/
			Path part = new Path(businessDetailsFile);// Location of file in
														// HDFS

			FileSystem fs = FileSystem.get(conf);
			FileStatus[] fss = fs.listStatus(part);
			for (FileStatus status : fss) {
				Path pt = status.getPath();
				BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(pt)));
				String line = br.readLine();
				while (line != null) {
					String[] mydata = line.split("::");
					if (mydata.length > 2 && mydata[1].contains("Stanford")) {
						businessIdSet.add(mydata[0]);
					}
					line = br.readLine();
				}
			}
		}

		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String[] mydata = value.toString().split("::");
			if (mydata.length > 3 && businessIdSet.contains(mydata[2])) {
				context.write(new Text(mydata[1]), new Text(mydata[3]));
			}
		}
	}

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs(); // get
																						// all
																						// args
		if (otherArgs.length < 2) {
			System.err.println("Usage: ProgramFour <review id directory> <business id directory> <output directory>");
			System.exit(2);
		}
		conf.set("businessDetailsFile", otherArgs[1]);
		Job job = new Job(conf, "User rating");
		job.setJarByClass(Query4.class);
		job.setMapperClass(Map.class);
		job.setNumReduceTasks(0);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		// set the HDFS path of the input data
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileSystem fs = FileSystem.get(conf);
		/* Check if output path (args[1])exist or not */
		if (fs.exists(new Path(args[2]))) {
			/* If exist delete the output path */
			fs.delete(new Path(args[2]), true);
		}
		// set the HDFS path for the output
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));
		// Wait till job completion
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
