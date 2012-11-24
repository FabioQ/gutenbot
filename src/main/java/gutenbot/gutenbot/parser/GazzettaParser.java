package gutenbot.gutenbot.parser;

import gutenbot.gutenbot.dto.Article;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GazzettaParser implements Parser {

	Logger logger = Logger.getLogger(GazzettaParser.class);
	
	@Override
	public Article parse(String url) throws IOException{
        System.out.println("Fetching "+ url);
        Document doc = Jsoup.connect(url).get();
        Elements toCleanTitle = doc.select("title");
        String title = toCleanTitle.text().replace("\n - La Gazzetta dello Sport", "");
        Elements toCleanArticle = doc.select("div.first-col");
        //System.out.println(toCleanArticle);
        Elements RawArticle = toCleanArticle.select("p.p");
        Article currentArticle = new Article("","");
        String ArticleContent = "";
        for (Element paragraph : RawArticle) {
        	ArticleContent += paragraph.text();
        	ArticleContent += " <br> ";
        }
        currentArticle.setArticleContent(ArticleContent);
        currentArticle.setArticleTitle(title);
		return currentArticle;
	}

}