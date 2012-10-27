package gutenbot.gutenbot.complete;

import gutenbot.gutenbot.common.DestinationConfiguration;
import gutenbot.gutenbot.common.GutenBotConfiguration;
import gutenbot.gutenbot.feed.FeedFilter;
import gutenbot.gutenbot.parser.Parser;

import org.junit.Test;
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
	public void testParsers(){
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("resources/applicationContext.xml");
		Parser p1 = (Parser) ctx.getBean("corriere");
		p1.parse();
		Parser p2 = (Parser) ctx.getBean("gazzetta");
		p2.parse();

	}
}
