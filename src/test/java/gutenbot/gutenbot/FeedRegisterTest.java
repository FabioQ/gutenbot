package gutenbot.gutenbot;

import gutenbot.gutenbot.feed.FileFeedRegister;
import gutenbot.gutenbot.feed.FeedRegister;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

public class FeedRegisterTest {

	@Test
	public void test() {
		
		FeedRegister register = null;
		try {
			register = new FileFeedRegister("feedregister", "test.txt");
			
			if(register.getLastDate() == null){
				System.out.println("empty file");
			}else{
					System.out.println("last date: "+register.getLastDate());
			}
			
			for (int i = 0; i < 3; i++) {
				register.setLastDate(new Date());
				System.out.println(i+": "+register.getLastDate().toString());
			}
		} catch (IOException e) {
			System.out.println("Impossible write this file");
			e.printStackTrace();
		}
		
	}

}
