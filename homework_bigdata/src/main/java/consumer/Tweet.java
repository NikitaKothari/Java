package consumer;
import java.io.Serializable;

public class Tweet implements Serializable{
	
	private String location;
	private String sentiment;
	private String text;
	private long postDate;

	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public long getPostDate() {
		return postDate;
	}
	public void setPostDate(long postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return (new String(this.text + "," + this.postDate + ","+ this.location + "," + this.sentiment));
	}

}
