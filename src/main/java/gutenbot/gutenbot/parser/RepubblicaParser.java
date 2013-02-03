package gutenbot.gutenbot.parser;

import gutenbot.gutenbot.dto.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: diegocastronuovo
 * Date: 03/02/13
 * Time: 17:41
 */
public class RepubblicaParser implements Parser{
    @Override
    public Article parse(String url) throws IOException {
        System.out.println("Fetching "+ url);
        Document doc = Jsoup.connect(url).get();
        Elements toCleanTitle = doc.select("title");
        Article currentArticle = new Article("","","");

        String title = toCleanTitle.text().replace(" - Repubblica.it", "");
        /*Elements toCleanArticle = doc.select("div.first-col");
        //System.out.println(toCleanArticle);
        Elements RawArticle = toCleanArticle.select("p.p");

        String ArticleContent = "";
        for (Element paragraph : RawArticle) {
            ArticleContent += paragraph.text();
            ArticleContent += " <br> ";
        }
        currentArticle.setArticleContent(ArticleContent);*/
        currentArticle.setArticleTitle(title);
        return currentArticle;
    }

    @Override
    public Article addEnclosure(Article withoutEnclosure, String enclosure) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
