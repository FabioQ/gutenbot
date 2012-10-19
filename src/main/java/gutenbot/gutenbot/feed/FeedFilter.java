package gutenbot.gutenbot.feed;

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

	LinkedList<SyndEntry> getSyndEntryFilteredList(String feedURL) {
		LinkedList<SyndEntry> list = new LinkedList<SyndEntry>();
		
		try {
			URL feedSource = new URL(feedURL);

			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));

			for (Object o : feed.getEntries()) {
				SyndEntry entry = (SyndEntry) o;
				if(entry.getPublishedDate().after(getLastDate())){
					list.add(entry);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	protected Date getLastDate() {
		
		Date retval = null;
		
		if(defaultStartDate != null){
			retval = defaultStartDate;
		}else{
			//TODO read from file
		}
		
		return null;
	}
	
	protected Date getLastDateFromFile() {
		return null;
	}

}
