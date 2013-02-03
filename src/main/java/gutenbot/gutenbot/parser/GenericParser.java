package gutenbot.gutenbot.parser;

import gutenbot.gutenbot.dto.Article;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: diegocastronuovo
 * Date: 03/02/13
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 */
public abstract class GenericParser implements Parser{

    public Article parse(String url) throws IOException, ArticleNotFoundException{
        System.out.println("Fetching " + url);
        try {
            return doParse(Jsoup.connect(url).get());
        } catch (HttpStatusException e) {
            throw new ArticleNotFoundException("Problem fetching article...");
        } catch (IOException e) {
            throw new ArticleNotFoundException("Problems fetching url...");
        }
    }

    abstract Article doParse(Document doc);

}
