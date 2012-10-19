package gutenbot.gutenbot;

import gutenbot.gutenbot.feed.FeedRegister;

import java.util.Date;

import org.junit.Test;

public class FeedRegisterTest {

	@Test
	public void test() {
		FeedRegister register = new FeedRegister("feedregister", "aaa");

		for (int i = 0; i < 3; i++) {
			register.setDate(new Date());
			System.out.println(i+": "+register.getDate().toString());
		}
	}

}
