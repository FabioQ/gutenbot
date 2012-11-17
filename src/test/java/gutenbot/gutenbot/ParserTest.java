package gutenbot.gutenbot;

import gutenbot.gutenbot.parser.Parser;
import gutenbot.gutenbot.parser.SaxonParser;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ParserTest {
	
	@Test
	public void testParsers() throws BeansException, IOException {
		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("resources/applicationContext.xml");
		Parser gazzetta = (Parser) ctx.getBean("gazzetta.it");
		gazzetta.parse("http://www.gazzetta.it/Calcio/Squadre/Juventus/20-10-2012/juve-napoli-sfida-totale-rivincite-sospetti-912961817332.shtml");
	}
	
	@Test
	public void testSaxon() throws IOException {
		SaxonParser p = new SaxonParser();
		p.parse("http://www.corriere.it/economia/12_novembre_11/romney-facebook-fan_b776cb92-2bfa-11e2-a3f0-bca5bc7cc62d.shtml");
	}

}
