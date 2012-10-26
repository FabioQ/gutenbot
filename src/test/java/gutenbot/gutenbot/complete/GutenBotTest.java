package gutenbot.gutenbot.complete;

import org.junit.Test;

import gutenbot.gutenbot.common.DestinationConfiguration;
import gutenbot.gutenbot.common.GutenBotConfiguration;
import gutenbot.gutenbot.feed.FeedFilter;

public class GutenBotTest {
	
	@Test
	public void test(){
		System.out.println("test urls");

		GutenBotConfiguration conf = new GutenBotConfiguration("domainconfigurations");
		
		for(DestinationConfiguration domainConf:conf.getDomainConfigurations()) {
			System.out.println("configuration:\t"+domainConf.getName());
			for(String url:domainConf.getFeedURLs()){
				System.out.println("feed url:\t"+url);
				FeedFilter feedFilter = new FeedFilter(domainConf.getName(), "feedregister",url);
				feedFilter.getSyndEntryFilteredList();
			}
		}
	}

}
