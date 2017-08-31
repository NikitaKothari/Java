package consumer;

import java.util.Date;

import org.apache.spark.api.java.function.Function;

public class ProcessedTweet implements Function<String, Tweet>{
	private static final long serialVersionUID = -1814450874634020500L;
	private static final String DELIMETER = "\\::";
	
	SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer();

	@Override
	public Tweet call(String tweet) throws Exception {
		Tweet tweet2 = new Tweet();
		String[] arr= tweet.split(DELIMETER);
	    
		try{
			tweet2.setText(arr[0]);
			tweet2.setLocation(arr[2] + "," + arr[1]);
			tweet2.setPostDate(new Date().getTime());
			tweet2.setSentiment(sentimentAnalyzer.findSentiment(arr[0]));
		}
		catch(Exception e)
		{
			System.out.println("Error" + e);
			e.printStackTrace();
		}
		return tweet2;
	}

}
