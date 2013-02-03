package gutenbot.gutenbot.parser;

import com.sun.syndication.feed.synd.SyndEntry;
import gutenbot.gutenbot.dto.Article;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: diegocastronuovo
 * Date: 03/02/13
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericParser implements Parser{

    public Article parse(SyndEntry entry) throws IOException, ArticleNotFoundException{
        System.out.println("Fetching " + entry);
        try {
            Document document = Jsoup.connect(entry.getLink()).get();
            return doParse(document,entry);
        } catch (HttpStatusException e) {
            throw new ArticleNotFoundException("Problem fetching article...");
        } catch (IOException e) {
            throw new ArticleNotFoundException("Problems fetching url...");
        }
    }

    abstract Article doParse(Document doc, SyndEntry entry);

}
