package gutenbot.gutenbot.parser;

import com.sun.syndication.feed.synd.SyndEntry;
import gutenbot.gutenbot.dto.Article;
import gutenbot.gutenbot.parser.Parser;import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.lang.Exception;import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: diegocastronuovo
 * Date: 03/02/13
 * Time: 17:43
 * To change this template use File | Settings | File Templates.
 */
public class RepubblicaParserTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParse() throws Exception, ArticleNotFoundException {
        FileSystemXmlApplicationContext ctx = new FileSystemXmlApplicationContext("resources/applicationContext.xml");
        Parser repubblica = (Parser) ctx.getBean("feedsportal.com");
        SyndEntry mockedEntry = Mockito.mock(SyndEntry.class);
        when(mockedEntry.getLink()).thenReturn("http://www.repubblica.it/sport/formulauno/2013/02/03/news/red_bull-51870719/");
        Article article = repubblica.parse(mockedEntry);
        assertThat(article.getArticleTitle(), is("F1, Red Bull: la differenza è nei dettagli"));

    }

    @Test
    public void testAddEnclosure() throws Exception {

    }
}
