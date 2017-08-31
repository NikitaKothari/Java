package producer;

import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class KafkaTwitterProducer {

	private static final String topic = "twitter-topic";

	public void run(String consumerKey, String consumerSecret, String token, String secret)
			throws InterruptedException, ParseException {
		
		Properties properties = new Properties();
		properties.put("metadata.broker.list", "localhost:9092");
		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		properties.put("client.id", "camus");
		ProducerConfig producerConfig = new ProducerConfig(properties);
		kafka.javaapi.producer.Producer<String, String> producer = new kafka.javaapi.producer.Producer<String, String>(
				producerConfig);

		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
		endpoint.trackTerms(Lists.newArrayList("#obama", "#trump", "#india", "#google", "#united", "#hello", "#goodmorning"));

		Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);

		Client client = new ClientBuilder().hosts(Constants.STREAM_HOST).endpoint(endpoint).authentication(auth)
				.processor(new StringDelimitedProcessor(queue)).build();

		client.connect();

		while (true) {
			KeyedMessage<String, String> message = null;
			
			try {
				message = new KeyedMessage<String, String>(topic, queue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(message.toString().substring(37, message.toString().length() - 2));
			
			try {
				String coordinate = (String) jsonObject.get("place");
			} catch (Exception e) {
				JSONObject jj = (JSONObject) jsonObject.get("place");
				JSONObject jj1 = (JSONObject) jj.get("bounding_box");
				JSONArray msg = (JSONArray) jj1.get("coordinates");
				JSONArray msg1 = (JSONArray) msg.get(0);
				JSONArray msg2 = (JSONArray) msg1.get(0);
				String tweet = (String) jsonObject.get("text");
				String final_msg = tweet + "::" + msg2.get(0) + "::" + msg2.get(1);
				System.out.println(final_msg);
				producer.send(new KeyedMessage<String, String>(topic, final_msg));
			}
		}
	}
	
	public static void main(String[] args) {
		KafkaTwitterProducer kafkaTwitterProducer = new KafkaTwitterProducer();
		
		String consumerKey = "I1dx4nwcrT2drdYSDNt5QmYTR";
		String consumerSecret = "DWkmDPGYvJTWp1WxyDlzbemPShEgql3aJ3wadhoxxHiwQL4ZbO";
		String token = "723816473848852480-0eFUrM8LzsT560AQonJnSvFERAMHIir";
		String secret = "rgoVarJl0LQgTrCLWgfj5zzcqN1hBaal828as5pfd7ye7";
		
		try {
			kafkaTwitterProducer.run(consumerKey, consumerSecret, token, secret);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}