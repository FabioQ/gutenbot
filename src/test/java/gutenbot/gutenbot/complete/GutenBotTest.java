package gutenbot.gutenbot.complete;

import gutenbot.gutenbot.common.DestinationConfiguration;
import gutenbot.gutenbot.common.GutenBotConfiguration;
import gutenbot.gutenbot.dispatcher.Dispatcher;
import gutenbot.gutenbot.dto.Article;
import gutenbot.gutenbot.dto.Destination;
import gutenbot.gutenbot.dto.Feed;
import gutenbot.gutenbot.feed.FeedRegister;
import gutenbot.gutenbot.feed.FileFeedRegister;
import gutenbot.gutenbot.parser.Parser;
import gutenbot.gutenbot.publisher.BlogPublisher;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
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
	
	int sleepMs = 500;

	FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("resources/applicationContext.xml");

	@Test
	public void test() throws IllegalArgumentException, FeedException, IOException, URISyntaxException, XmlRpcFault, InterruptedException {

		while (true) {
			
			GutenBotConfiguration conf = new GutenBotConfiguration("domainconfigurations");
			for (DestinationConfiguration configuration : conf.getDomainConfigurations()) {
				System.out.println("### Configuration: " + configuration.getName());

				FeedRegister fr = new FileFeedRegister("feedregister", configuration.getName());
				Date maxDate = null;
				if (fr.getLastDate() == null) {
					System.out.println("empty file");
					maxDate = new Date(0);
				} else {
					System.out.println("last date: " + fr.getLastDate());
					maxDate = fr.getLastDate();
				}

				// list feed url
				for (String feedUrl : configuration.getFeedURLs()) {
					System.out.println("feed:\t" + feedUrl);

					// create library feed object

					URL feedSource = new URL(feedUrl);
					SyndFeedInput input = new SyndFeedInput();
					SyndFeed feedLib = input.build(new XmlReader(feedSource));

					// create our feed object
					Feed f = new Feed(feedUrl, new Destination(configuration.getDestinationUserName(), configuration.getDestinationUserPassword(), configuration.getDestinationXmlRpcUrl()), Lists.newLinkedList(feedLib.getEntries()));

					// dispatcher
					Dispatcher dispatcher = new Dispatcher();
					dispatcher.setFeed(f);
					System.out.println("dispatcher selected: " + dispatcher.getDomain());
					String blogCategory = "Calcio"; // TODO this category should
													// be
													// part
													// of feed detail to split
													// feeds
													// between categories.

					// create parser
					Parser parser = (Parser) ctx.getBean(dispatcher.getDomain());

					// configuring destination
					BlogPublisher destinationBlog = new BlogPublisher();
					destinationBlog.blogConnect(f.getDestination().getDestUsername(), f.getDestination().getDestPassword(), f.getDestination().getDestXmlrpcURL());

					// parse entries
					for (Object o : f.getSyndEntryList()) {
						Article articleContent;
						SyndEntry entry = (SyndEntry) o;
						System.out.println("parsing:\t" + entry.getPublishedDate().toString() + " - " + entry.getLink());

						if (entry.getPublishedDate().after(maxDate)) {

							System.out.println("Article accepted, pubDate=" + entry.getPublishedDate().toString() + " maxDate=" + maxDate.toString());

							// if I have any enclosure, it will be set on the
							// article object
							// so the parser can use it.
							List<SyndEnclosure> articleEnclosuresList = entry.getEnclosures();
							articleContent = parser.parse(entry.getLink());
							if (articleContent.getArticleContent().length() > 20) {
								// added in the parser a method able to add
								// enclosures, it
								// takes the first enclosure of an article, if
								// any,
								// and use
								// it in the article.
								articleContent = parser.addEnclosure(articleContent, articleEnclosuresList.get(0).getUrl());
								System.out.println("Posting an article");
								destinationBlog.blogPublish(articleContent.getArticleTitle(), articleContent.getArticleContent(), dispatcher.getDomain(), entry.getAuthor(), blogCategory);
							}

						} else {
							System.out.println("Article discarded, pubDate=" + entry.getPublishedDate().toString() + " maxDate=" + maxDate.toString());
						}
					}

				}

				fr.setLastDate(new Date());

			}

			System.out.println("Cycle ended, sleep for "+sleepMs+" ms");

			Thread.sleep(sleepMs);

		}

	}
}
