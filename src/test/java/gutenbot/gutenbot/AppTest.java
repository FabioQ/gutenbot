package gutenbot.gutenbot;

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.Test;

public class AppTest {

	static Logger logger = Logger.getLogger(AppTest.class);

	@Test
	public void test() {
		logger.debug("this is a test");
		fail("Not yet implemented");
	}

}
