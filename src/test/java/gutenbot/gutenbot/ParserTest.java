package gutenbot.gutenbot;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import gutenbot.gutenbot.parser.ArticleNotFoundException;
import gutenbot.gutenbot.parser.Parser;
import gutenbot.gutenbot.parser.SaxonParser;

import java.io.IOException;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static org.mockito.Mockito.when;

public class ParserTest {
	
//	@Test
//	public void testParsers() throws BeansException, IOException, ArticleNotFoundException {
//		FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("resources/applicationContext.xml");
//		Parser gazzetta = (Parser) ctx.getBean("motosprint.it");
//        SyndEntry mockedEntry = Mockito.mock(SyndEntry.class);
//        when(mockedEntry.getLink()).thenReturn("http://www.motosprint.it/sbk/2013/01/03-7979/SBK%3A+Dal+2014+la+rivoluzione+della+Dorna");
//        gazzetta.parse(mockedEntry);
//	}

	@Test
	public void testSaxon() throws IOException {
		SaxonParser p = new SaxonParser();
        SyndEntry se = new SyndEntryImpl();
        se.setLink("http://bologna.repubblica.it/cronaca/2013/02/03/news/grillo_sosterremo_dario_fo_al_quirinale-51882952/?rss");

        p.parse(se);
	}

}
