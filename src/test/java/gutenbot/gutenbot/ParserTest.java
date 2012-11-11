package gutenbot.gutenbot;

import gutenbot.gutenbot.parser.Parser;

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

}
