package gutenbot.gutenbot;

import gutenbot.gutenbot.dto.Destination;
import gutenbot.gutenbot.dto.Feed;
import gutenbot.gutenbot.dispatcher.Dispatcher;
import gutenbot.gutenbot.feed.FeedRegister;

import org.junit.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;

public class DispatcherTest {

	@Test
	public void dispatcherTest() throws URISyntaxException {
		//initialization phase
		Destination destination = null;
		String feedURL = "http://www.gazzetta.it/rss/Calcio.xml";
		LinkedList<SyndEntry> newsList = CreateSyndEntry();
		System.out.println("test dispatcher");
		Feed feedObj = new Feed(feedURL, destination, newsList);
		System.out.println("Stampo il titolo del primo elemento della lista di feed per testare la creazione dell'oggetto Feed:");
		System.out.println(feedObj.getSyndEntryList().get(0).getTitle());
		//TODO parser selection phase
		Dispatcher dispatcher = new Dispatcher();
	}
	
	@Test
	public void testDispatcher() throws URISyntaxException{
		Dispatcher dispatcher = new Dispatcher();
		Feed feed = DispatcherTest.createFeed();
		dispatcher.setFeed(feed);
		System.out.println(dispatcher.getDomain());
	}

	private static LinkedList<SyndEntry> CreateSyndEntry(){
		List enclosuresA = new ArrayList();
		enclosuresA.add("http://images2.gazzettaobjects.it/Media/Foto/2012/10/20/combo_365--630x365.jpg");
		List enclosuresB = new ArrayList();
		enclosuresB.add("http://images2.gazzettaobjects.it/Media/Foto/2012/10/20/LAPR0734--630x365.JPG");
		SyndEntry newsA = new SyndEntryImpl();
		SyndEntry newsB = new SyndEntryImpl();
		newsA.setTitle("Juve-Napoli, sfida totale Tra rivincite e sospetti");
		newsA.setLink("http://www.gazzetta.it/Calcio/Squadre/Juventus/20-10-2012/juve-napoli-sfida-totale-rivincite-sospetti-912961817332.shtml");
		newsA.setEnclosures(enclosuresA);
		newsB.setTitle("Bonucci reagisce a una rapina Un pugno e l'aggressore fugge");
		newsB.setLink("http://www.gazzetta.it/Calcio/Squadre/Juventus/20-10-2012/bonucci-aggredito-reagisce-pugno-rapinatore-che-scappa-912961418231.shtml");
		newsA.setEnclosures(enclosuresB);
		LinkedList<SyndEntry> newsList = new LinkedList<SyndEntry>();
		newsList.add(newsA);
		newsList.add(newsB);
		return newsList;	
	}
	
	//just used for test purpose, create a feed object example to dispatch
		public static Feed createFeed(){
			Destination destination = null;
			String feedURL = "http://www.gazzetta.it/rss/Calcio.xml";
			LinkedList<SyndEntry> newsList = CreateSyndEntry();
			//System.out.println("test dispatcher");
			Feed feedObj = new Feed(feedURL, destination, newsList);
			//System.out.println("Stampo il titolo del primo elemento della lista di feed per testare la creazione dell'oggetto Feed:");
			//System.out.println(feedObj.getSyndEntryList().get(0).getTitle());
			return feedObj;
		}
		
		

}