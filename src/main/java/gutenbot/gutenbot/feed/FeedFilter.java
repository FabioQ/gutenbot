package gutenbot.gutenbot.feed;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.collect.Lists;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedFilter {

	Logger logger = Logger.getLogger(FeedFilter.class);
	
	String destinationName;
	String registerPath;
	String feedUrl;
	FeedRegister register = null;
	Date defaultStartDate;
	LinkedList<SyndEntry> list = new LinkedList<SyndEntry>();
	
	public FeedFilter(String destinationName, String registerPath, String feedUrl) {
		this.destinationName = destinationName;
		this.registerPath = registerPath;
		this.feedUrl = feedUrl;
		
		try {
			register = new FileFeedRegister(registerPath, generateFeedId(destinationName, feedUrl));
		} catch (IOException e) {
			logger.error("Error creating register (destination="+destinationName+", path="+registerPath+")", e);
			e.printStackTrace();
		}
		
	}
	
	public boolean isInstantiated(){
		return register == null ? true : false;
	}

	
	
	public void setDefaultStartDate(Date defaultStartDate) {
		this.defaultStartDate = defaultStartDate;
	}

	
	
	public LinkedList<SyndEntry> getSyndEntryFilteredList() {
		list.clear();
		
		try {
			URL feedSource = new URL(feedUrl);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));
			Date lastDate = getLastDate(feedUrl);
			
			List<SyndEntry> entries = (List<SyndEntry>) feed.getEntries();
			//TODO ordinare per data
			for (SyndEntry entry : entries) {
				
				if(lastDate == null || entry.getPublishedDate().after(lastDate)){
					list.add(entry);
//					setLastDate(entry.getPublishedDate());
					logger.debug("entry ADDED:\t"+entry.getPublishedDate().toString()+" "+entry.getUri().toString());
				}else{
					logger.debug("entry IGNORED:\t"+entry.getPublishedDate().toString()+" "+entry.getUri().toString());
				}
			}

		} catch (Exception e) {
			logger.error("Error reading article",e);
			e.printStackTrace();
		}

		return list;
	}

	
	
	protected Date getLastDate(String feedUrl) {
		
		Date retval = null;
		
		if(defaultStartDate != null){
			retval = defaultStartDate;
		}else{
			retval = register.getLastDate();
		}
		
		if(retval == null){
			retval = new Date(0); // long time ago
		}
		
		return retval;
	}
	
	protected void setLastDate(Date lastDate){
		register.setLastDate(lastDate);
	}
	
	protected String generateFeedId(String domainName, String feedUrl){
		return domainName+ " " + feedUrl.replaceAll(":", "_").replaceAll("/", "_")+".txt";
	}

}
