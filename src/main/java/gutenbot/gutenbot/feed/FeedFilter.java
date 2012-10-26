package gutenbot.gutenbot.feed;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedFilter {

	String domainName;
	String registerPath;
	Date defaultStartDate;

	
	
	public FeedFilter(String domainName, String registerPath) {
		this.domainName = domainName;
		this.registerPath = registerPath;
	}

	
	
	public void setDefaultStartDate(Date defaultStartDate) {
		this.defaultStartDate = defaultStartDate;
	}

	
	
	public LinkedList<SyndEntry> getSyndEntryFilteredList(String feedURL) {
		LinkedList<SyndEntry> list = new LinkedList<SyndEntry>();
		
		try {
			URL feedSource = new URL(feedURL);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));
			Date lastDate = getLastDate(feedURL);
			
			for (Object o : feed.getEntries()) {
				SyndEntry entry = (SyndEntry) o;
				
				if(lastDate == null || entry.getPublishedDate().after(lastDate)){
					list.add(entry);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	
	
	protected Date getLastDate(String feedUrl) {
		
		Date retval = null;
		
		if(defaultStartDate != null){
			retval = defaultStartDate;
		}else{
			retval = getLastDateFromFile(feedUrl);
		}
		
		if(retval == null){
			retval = new Date(0); // long time ago
		}
		
		return retval;
	}
	
	
	
	protected Date getLastDateFromFile(String feedUrl) {
		
		FeedRegister register = null;
		
		try {
			register = new FileFeedRegister(registerPath, generateFeedId(domainName, feedUrl));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return register.getDate();
	}
	
	
	
	protected String generateFeedId(String domainName, String feedUrl){
		return "domainName " + feedUrl.replaceAll(":", "_").replaceAll("/", "_");
	}

}
