package gutenbot.gutenbot.complete;

import gutenbot.gutenbot.common.DestinationConfiguration;
import gutenbot.gutenbot.dispatcher.Dispatcher;
import gutenbot.gutenbot.dto.Destination;
import gutenbot.gutenbot.dto.Feed;
import gutenbot.gutenbot.parser.Parser;
import gutenbot.gutenbot.publisher.BlogPublisher;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import redstone.xmlrpc.XmlRpcFault;

import com.google.common.collect.Lists;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class GutenBotTest {
	
	FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("resources/applicationContext.xml");

	@Test
	public void test() throws IllegalArgumentException, FeedException, IOException, URISyntaxException, XmlRpcFault{
		
		// load configuration
		File configurationFile = new File("domainconfigurations/quinzi.org.xml");
		DestinationConfiguration configuration = new DestinationConfiguration(configurationFile);
		if(configuration.isValid()){
			System.out.println("configuration file:\t"+configuration.getName());
		}
		
		// list feed url
		for(String feedUrl:configuration.getFeedURLs()){
			System.out.println("feed:\t"+feedUrl);
			
			// create library feed object
			
			URL feedSource = new URL(feedUrl);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feedLib = input.build(new XmlReader(feedSource));
			
			// create our feed object
			Feed f = new Feed(feedUrl, new Destination(), Lists.newLinkedList(feedLib.getEntries()));
			
			// dispatcher
			Dispatcher dispatcher = new Dispatcher();
			dispatcher.setFeed(f);
			System.out.println("dispatcher selected: "+dispatcher.getDomain());
			
			// create parser
			Parser parser = (Parser) ctx.getBean(dispatcher.getDomain());
			
			//configuring destination (TODO info has to be estrapolated frome destination)
			String destUsername = "admin";
			String destPassword = "alonso11";
			String destXmlrpcURL = "http://www.sportsponsorizzazioni.com/xmlrpc.php";
			BlogPublisher destinationBlog = new BlogPublisher();
			destinationBlog.blogConnect(destUsername, destPassword, destXmlrpcURL);
			
			//parse entries
			for(Object o:f.getSyndEntryList()){
				String contentText;
				SyndEntry entry = (SyndEntry) o;
				System.out.println("parsing:\t"+entry.getPublishedDate().toString()+ " - "+entry.getLink());
				contentText = parser.parse(entry.getLink());
				if (contentText.length() > 20){
					System.out.println("Posting an article");
					destinationBlog.blogPublish(entry.getTitle(), contentText);
				}
			}
		}
		
		
		
	}
	
}
