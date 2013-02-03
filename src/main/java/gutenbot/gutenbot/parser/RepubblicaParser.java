package gutenbot.gutenbot.parser;

import com.sun.syndication.feed.synd.SyndEntry;
import gutenbot.gutenbot.dto.Article;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: diegocastronuovo
 * Date: 03/02/13
 * Time: 17:41
 */
public class RepubblicaParser extends GenericParser{

    @Override
    Article doParse(Document doc, SyndEntry entry) {
        Elements toCleanTitle = doc.select("title");
        Article currentArticle = new Article("","","");

        String title = toCleanTitle.text().replace(" - Repubblica.it", "");
        String fullText = doc.toString();
        List<String> lines = Arrays.asList(fullText.split("\n"));
        //TODO: What to do? use SyndEntryObject?
/*         for (String line : lines) {
            if (line.contains("<!-- inizio TESTO -->")) {
                System.out.println(line);

            }
        }
       System.out.println(fullText);
        Elements toCleanArticle = doc.select("div.first-col");
        //System.out.println(toCleanArticle);
        Elements RawArticle = toCleanArticle.select("p.p");

        String ArticleContent = "";
        for (Element paragraph : RawArticle) {
            ArticleContent += paragraph.text();
            ArticleContent += " <br> ";
        }
        currentArticle.setArticleContent(ArticleContent);*/
        currentArticle.setArticleTitle(title);
        System.out.println("The title is:"+ title);
        return currentArticle;
    }

    @Override
    public Article addEnclosure(Article withoutEnclosure, String enclosure) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
