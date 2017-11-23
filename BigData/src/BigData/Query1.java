package BigData;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
/**
 * List the unique categories of business located in “Palo Alto” 
 * @author nikitakothari
 *
 */
public class Query1

{
	public static class B_Map extends Mapper<LongWritable, Text, Text, NullWritable> {
		private Text category = new Text();

		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String[] dataset = value.toString().split("::");
			if (dataset[1].contains("Palo Alto")) {
				category.set(dataset[2]); // column 2 contains categories
				context.write(category, NullWritable.get());
			}
		}
	}

	public static class MyReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
		@Override
		public void reduce(Text key, Iterable<NullWritable> values, Context context)
				throws IOException, InterruptedException {
			context.write(key, NullWritable.get());
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration config = new Configuration();
		String[] otherArgs = new GenericOptionsParser(config, args).getRemainingArgs();
		if (otherArgs.length != 2) // 2 arguments are needed: input and output
		{
			System.err.println("Usage: Program1 <in> <out>");
			System.exit(2);
		}
		// create a job with name "Program1"
		@SuppressWarnings("deprecation")
		Job job = new Job(config, "Query1");
		job.setJarByClass(Query1.class);
		job.setMapperClass(B_Map.class);
		job.setReducerClass(MyReducer.class);
		// set output key type
		job.setOutputKeyClass(Text.class);
		// set output value type
		job.setOutputValueClass(NullWritable.class);
		// set the HDFS path of the input data
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		// set the HDFS path for the output
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		// Wait till job completion
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
