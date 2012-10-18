package gutenbot.gutenbot;

import gutenbot.gutenbot.common.DomainConfiguration;
import gutenbot.gutenbot.common.GutenBotConfiguration;

import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void test() {

		GutenBotConfiguration conf = new GutenBotConfiguration("domainconfigurations");
		
		for(DomainConfiguration domainConf:conf.getDomainConfigurations()) {
			System.out.println(domainConf.getName());
		}
	}

}
