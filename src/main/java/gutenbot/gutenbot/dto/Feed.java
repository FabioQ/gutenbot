package gutenbot.gutenbot.dto;

import java.util.LinkedList;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

public class Feed {

	String feedURL;
	Destination destination;
	LinkedList<SyndEntry> syndEntryList;

	public Feed(String feedURL, Destination destination, LinkedList<SyndEntry> syndEntryList) {
		super();
		this.feedURL = feedURL;
		this.destination = destination;
		this.syndEntryList = syndEntryList;
	}

	public String getFeedURL() {
		return feedURL;
	}

	public Destination getDestination() {
		return destination;
	}

	public LinkedList<SyndEntry> getSyndEntryList() {
		return syndEntryList;
	}

//	public static Feed convertFeed(SyndFeed inputFeed){
//		Feed outputFeed = new Feed();
//		return outputFeed;
//	}
	
}
