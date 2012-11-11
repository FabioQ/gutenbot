package gutenbot.gutenbot;

import gutenbot.gutenbot.common.DestinationConfiguration;
import gutenbot.gutenbot.common.GutenBotConfiguration;
import gutenbot.gutenbot.feed.FeedFilter;

import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void test() {
		
		System.out.println("test domain config");

		GutenBotConfiguration conf = new GutenBotConfiguration("domainconfigurations");
		for(DestinationConfiguration domainConf:conf.getDomainConfigurations()) {
			System.out.println(domainConf.getName());
		}
	}
	
	@Test
	public void rssTest(){
		
		System.out.println("test urls");

		GutenBotConfiguration conf = new GutenBotConfiguration("domainconfigurations");
		for(DestinationConfiguration domainConf:conf.getDomainConfigurations()) {
			System.out.println("configuration: "+domainConf.getName());
			for(String url:domainConf.getFeedURLs()){
				System.out.println(url);
			}
		}

	}
	
	@Test
	public void completeConfigurationTest() {
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

}
