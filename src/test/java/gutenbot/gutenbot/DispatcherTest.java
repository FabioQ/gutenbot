package gutenbot.gutenbot;

import gutenbot.gutenbot.dto.Destination;
import gutenbot.gutenbot.dto.Feed;
import gutenbot.gutenbot.dispatcher.Dispatcher;

import org.junit.Test;
import java.util.LinkedList;
import com.sun.syndication.feed.synd.SyndEntry;

public class DispatcherTest {

	@Test
	public void test() {
		
		Destination destination = null;
		String feedURL = "http://www.gazzetta.it/rss/Calcio.xml";
		SyndEntry newsA = null;
		SyndEntry newsB = null;
		// TODO create test articles
		LinkedList<SyndEntry> newslist = null;
		newslist.add(newsA);
		newslist.add(newsB);
		System.out.println("test dispatcher");
		Feed feedObj = new Feed(feedURL, destination, newslist);
		//Test Start
	}

}
