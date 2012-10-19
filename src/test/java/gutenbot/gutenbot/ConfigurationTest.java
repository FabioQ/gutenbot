package gutenbot.gutenbot;

import gutenbot.gutenbot.common.DomainConfiguration;
import gutenbot.gutenbot.common.GutenBotConfiguration;

import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void test() {
		
		System.out.println("test domain config");

		GutenBotConfiguration conf = new GutenBotConfiguration("domainconfigurations");
		for(DomainConfiguration domainConf:conf.getDomainConfigurations()) {
			System.out.println(domainConf.getName());
		}
	}
	
	@Test
	public void rssTest(){
		
		System.out.println("test urls");

		GutenBotConfiguration conf = new GutenBotConfiguration("domainconfigurations");
		for(DomainConfiguration domainConf:conf.getDomainConfigurations()) {
			System.out.println("configuration: "+domainConf.getName());
			for(String url:domainConf.getFeedURLs()){
				System.out.println(url);
			}
		}

	}

}
