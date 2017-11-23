package BigData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
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
 * Find the top ten rated businesses using the average ratings. 
 * Top rated business will come first. 
 * Recall that 4th column in review.csv file represents the rating.
 * 
 * @author nikitakothari
 *
 */
public class Query2 {
	public static class B_Map extends Mapper<LongWritable, Text, Text, FloatWritable> {
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			String dataset[] = value.toString().split("::");
			if (dataset.length > 3) {
				// column 2 is business id and column 3 is stars
				context.write(new Text(dataset[2]), new FloatWritable(Float.parseFloat(dataset[3])));
			}
		}
	}

	public static class Reduce extends Reducer<Text, FloatWritable, Text, NullWritable> {
		TreeMap<Float, List<Text>> review_id = new TreeMap<Float, List<Text>>();

		public void reduce(Text key, Iterable<FloatWritable> values, Context context)
				throws IOException, InterruptedException {
			float sum = (float) 0.0;
			int count = 0;
			for (FloatWritable value : values) {
				sum += value.get();
				count++; // to calculate the average
			}
			Float avg = sum / count;
			if (review_id.containsKey(avg)) {
				review_id.get(avg).add(new Text(key.toString()));
			} else {
				List<Text> bId_List = new ArrayList<Text>();
				bId_List.add(new Text(key.toString()));
				// putting average and the corresponding business ids in the
				// tree map
				review_id.put(avg, bId_List);
			}
		}

		@Override

		protected void cleanup(Reducer<Text, FloatWritable, Text, NullWritable>.Context context)
				throws IOException, InterruptedException {
			NavigableSet<Float> rev = review_id.descendingKeySet();
			Iterator<Float> businessidIterator = rev.iterator();
			int count = 0;
			while (businessidIterator.hasNext() && count < 10) {
				float avg = businessidIterator.next();
				List<Text> businessidTempList = review_id.get(avg);

				for (Text t : businessidTempList) {
					if (count >= 10)
						break;
					context.write(new Text(t.toString() + " " + avg), NullWritable.get());
					count++;
				}
			}
		}
	}

	// Driver program
	public static void main(String[] args) throws Exception {
		Configuration config = new Configuration();
		String[] otherArgs = new GenericOptionsParser(config, args).getRemainingArgs();
		// gets all args
		if (otherArgs.length != 2) {
			System.err.println("Usage: Program2 <in> <out>");
			System.exit(2);
		}
		@SuppressWarnings("deprecation")
		Job job = new Job(config, "Query2"); // creates a job with name
												// "Program2"
		job.setJarByClass(Query2.class);
		job.setMapperClass(B_Map.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FloatWritable.class);
		job.setReducerClass(Reduce.class);
		job.setOutputKeyClass(Text.class); // sets output key type
		job.setOutputValueClass(NullWritable.class); // sets output value type
		FileInputFormat.addInputPath(job, new Path(otherArgs[0])); // sets the
																	// HDFS path
																	// of the
																	// input
																	// data

		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1])); // sets the
																		// HDFS
																		// path
																		// for
																		// the
																		// output

		System.exit(job.waitForCompletion(true) ? 0 : 1); // Waits till job
															// completion

	}
}
