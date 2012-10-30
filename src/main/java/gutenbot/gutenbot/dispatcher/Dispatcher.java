package gutenbot.gutenbot.dispatcher;

import gutenbot.gutenbot.dto.Feed;
import java.util.LinkedList;
import com.sun.syndication.feed.synd.SyndEntry;
import java.util.regex.*;
import java.net.URI;
import java.net.URISyntaxException;


public class Dispatcher {
	
	public Dispatcher(Feed feed) throws URISyntaxException{
        Pattern p = Pattern.compile(".*?([^.]+\\.[^.]+)");
		Feed toDispatch = feed;
		URI uri = new URI(toDispatch.getFeedURL());
		System.out.println(uri);
		Matcher m = p.matcher(uri.getHost());
        if (m.matches()) {
            System.out.println(m.group(1));
        }	
	}
}
