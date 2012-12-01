package gutenbot.gutenbot.complete;

import gutenbot.gutenbot.common.DestinationConfiguration;
import gutenbot.gutenbot.dispatcher.Dispatcher;
import gutenbot.gutenbot.dto.Article;
import gutenbot.gutenbot.dto.Destination;
import gutenbot.gutenbot.dto.Feed;
import gutenbot.gutenbot.parser.Parser;
import gutenbot.gutenbot.publisher.BlogPublisher;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import redstone.xmlrpc.XmlRpcFault;

import com.google.common.collect.Lists;
import com.sun.syndication.feed.synd.SyndEnclosure;
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
		File configurationFile = new File("domainconfigurations/sportsponsorizzazioni.com.xml");
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
			Feed f = new Feed(feedUrl, new Destination(configuration.getDestinationUserName(), configuration.getDestinationUserPassword(), configuration.getDestinationXmlRpcUrl()), Lists.newLinkedList(feedLib.getEntries()));
			
			// dispatcher
			Dispatcher dispatcher = new Dispatcher();
			dispatcher.setFeed(f);
			System.out.println("dispatcher selected: "+dispatcher.getDomain());
			String blogCategory = "Calcio"; //TODO this category should be part of feed detail to split feeds between categories.
			
			// create parser
			Parser parser = (Parser) ctx.getBean(dispatcher.getDomain());
			
			//configuring destination 
			BlogPublisher destinationBlog = new BlogPublisher();
			destinationBlog.blogConnect(f.getDestination().getDestUsername(), f.getDestination().getDestPassword(), f.getDestination().getDestXmlrpcURL());
			
			//parse entries
			for(Object o:f.getSyndEntryList()){
				Article articleContent;
				SyndEntry entry = (SyndEntry) o;
				System.out.println("parsing:\t"+entry.getPublishedDate().toString()+ " - "+entry.getLink());
				//if I have any enclosure, it will be set on the article object so the parser can use it.
				List<SyndEnclosure> articleEnclosuresList = entry.getEnclosures();
				articleContent = parser.parse(entry.getLink());
				if (articleContent.getArticleContent().length() > 20){
					//added in the parser a method able to add enclosures, it takes the first enclosure of an article, if any, and use it in the article.
					articleContent = parser.addEnclosure(articleContent, articleEnclosuresList.get(0).getUrl());
					System.out.println("Posting an article");
					destinationBlog.blogPublish(articleContent.getArticleTitle(), articleContent.getArticleContent(), dispatcher.getDomain(), entry.getAuthor(), blogCategory);
				}
			}
		}
		
		
		
	}
	
}
