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
	
	private Feed feed;	//Creo un Feed di prova.
	String domain = null;
	
	//il dispatcher poi prendera' un Feed feed come input, al posto di crearlo sopra
	public Dispatcher() {
		
	}
	
	public void setFeed(Feed feed){
		this.feed = feed;
	}
	
	public String getDomain() throws URISyntaxException{
        Pattern p = Pattern.compile(".*?([^.]+\\.[^.]+)");
		URI uri = new URI(feed.getFeedURL());
		Matcher m = p.matcher(uri.getHost());
        if (m.matches()) {
            domain = m.group(1);
        }
		
		return domain;
	}
	
		
}
