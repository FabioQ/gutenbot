package gutenbot.gutenbot.complete;

import java.io.IOException;
import java.net.URISyntaxException;

import gutenbot.gutenbot.common.DestinationConfiguration;
import gutenbot.gutenbot.common.GutenBotConfiguration;
import gutenbot.gutenbot.feed.FeedFilter;
import gutenbot.gutenbot.parser.Parser;
import gutenbot.gutenbot.dispatcher.Dispatcher;
import gutenbot.gutenbot.dto.Feed;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class GutenBotTest {

	@Test
	public void test() {
		System.out.println("test urls");

		GutenBotConfiguration conf = new GutenBotConfiguration("domainconfigurations");

		for (DestinationConfiguration domainConf : conf.getDomainConfigurations()) {
			System.out.println("configuration:\t" + domainConf.getName());
			for (String url : domainConf.getFeedURLs()) {
				System.out.println("feed url:\t" + url);
				FeedFilter feedFilter = new FeedFilter(domainConf.getName(), "feedregister", url);
				feedFilter.getSyndEntryFilteredList();
			}
		}
	}
	
	@Test
	public void testDispatcher() throws URISyntaxException{
		Dispatcher dispatcher = new Dispatcher();
		System.out.println(dispatcher.getDomain());
	}

	@Test
	public void testParsers() throws BeansException, IOException {
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("resources/applicationContext.xml");
		Parser gazzetta = (Parser) ctx.getBean("gazzetta.it");
		gazzetta.parse("http://www.gazzetta.it/Calcio/Squadre/Juventus/20-10-2012/juve-napoli-sfida-totale-rivincite-sospetti-912961817332.shtml");
	}
}
