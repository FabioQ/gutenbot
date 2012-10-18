package gutenbot.gutenbot;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RssTest {

	@Test
	public void listItemTest() throws IllegalArgumentException, FeedException, IOException {

		URL feedSource = new URL("http://xml.corriereobjects.it/rss/sport.xml");
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(feedSource));
		
		for(Object o:feed.getEntries()){
			SyndEntry entry = (SyndEntry) o;
			System.out.println(entry.getPublishedDate().toString()+ " - "+entry.getLink());
		}

	}

}
