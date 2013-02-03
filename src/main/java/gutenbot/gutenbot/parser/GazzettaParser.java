package gutenbot.gutenbot.parser;

import gutenbot.gutenbot.dto.Article;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GazzettaParser extends GenericParser {

	Logger logger = Logger.getLogger(GazzettaParser.class);

    @Override
    Article doParse(Document doc) {
        Elements toCleanTitle = doc.select("title");
        String title = toCleanTitle.text().replace("\n - La Gazzetta dello Sport", "");
        Elements toCleanArticle = doc.select("div.first-col");
        //System.out.println(toCleanArticle);
        Elements RawArticle = toCleanArticle.select("p.p");
        Article currentArticle = new Article("", "", "");
        String ArticleContent = "";
        for (Element paragraph : RawArticle) {
            ArticleContent += paragraph.text();
            ArticleContent += " <br> ";
        }
        currentArticle.setArticleContent(ArticleContent);
        currentArticle.setArticleTitle(title);
        return currentArticle;
    }

    @Override
	public Article addEnclosure(Article withoutEnclosure, String enclosure){
		if (enclosure.length() > 1){
			//Standard code to add an image aligned on left and with a 300px width
			String imgCode = "<img style=\"float:left;margin:5px 4% 20px 0;\" title=\"" + withoutEnclosure.getArticleTitle() + "\" src=\""+ enclosure + "\" width=\"300px\" >";
			//System.out.println("DEBUG imgCode: " + imgCode);
			withoutEnclosure.setArticleContent(imgCode + withoutEnclosure.getArticleContent());
		}
		//TODO add enclosure to article content.
		//System.out.println("AAAAAAAA"+enclosure);
		return withoutEnclosure;
	}

}