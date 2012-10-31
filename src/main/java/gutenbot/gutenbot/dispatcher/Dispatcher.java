package gutenbot.gutenbot.dispatcher;

import gutenbot.gutenbot.dto.Destination;
import gutenbot.gutenbot.dto.Feed;
import gutenbot.gutenbot.parser.Parser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;

import java.util.regex.*;
import java.net.URI;
import java.net.URISyntaxException;


public class Dispatcher {
	
	Feed feed = CreateFeed();	//Creo un Feed di prova.
	String domain = null;
	
	//il dispatcher poi prendera' un Feed feed come input, al posto di crearlo sopra
	public Dispatcher() throws URISyntaxException{
		System.out.println("Utilizzo un Feed di prova");
        Pattern p = Pattern.compile(".*?([^.]+\\.[^.]+)");
		Feed toDispatch = feed;
		URI uri = new URI(toDispatch.getFeedURL());
		System.out.println(uri);
		Matcher m = p.matcher(uri.getHost());
        if (m.matches()) {
            domain = m.group(1);
        }
	}
	
	public String getDomain(){
		return domain;
	}
	
	//just used for test purpose, create a feed object example to dispatch
	public Feed CreateFeed(){
		Destination destination = null;
		String feedURL = "http://www.gazzetta.it/rss/Calcio.xml";
		LinkedList<SyndEntry> newsList = CreateSyndEntry();
		//System.out.println("test dispatcher");
		Feed feedObj = new Feed(feedURL, destination, newsList);
		//System.out.println("Stampo il titolo del primo elemento della lista di feed per testare la creazione dell'oggetto Feed:");
		//System.out.println(feedObj.getSyndEntryList().get(0).getTitle());
		return feedObj;
	}
	
	//just used for test purpose
	public LinkedList<SyndEntry> CreateSyndEntry(){
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
	
}
