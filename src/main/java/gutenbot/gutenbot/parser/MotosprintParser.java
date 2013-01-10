package gutenbot.gutenbot.parser;

import gutenbot.gutenbot.dto.Article;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MotosprintParser implements Parser {

	Logger logger = Logger.getLogger(MotosprintParser.class);
	
	@Override
	public Article parse(String url) throws IOException{
        System.out.println("Fetching "+ url);
        Document doc = Jsoup.connect(url).get();
        Elements toCleanTitle = doc.select("h1.tit_Article");
        
        //String title = toCleanTitle.text().replace(" - Motosprint", "");
        String title = toCleanTitle.text();
        //System.out.println("PARSED TITLE: " + title);
        
        Elements toCleanArticle = doc.select("div.txt_Article");
        //System.out.println("TOCLEAN ARTICLE: " + toCleanArticle);
        Elements RawArticle = toCleanArticle.select("p");
        Article currentArticle = new Article("","","");
        String ArticleContent = "";
        for (Element paragraph : RawArticle) {
        	ArticleContent += paragraph.text();
        	ArticleContent += " <br> ";
        }
        currentArticle.setArticleContent(ArticleContent);
        currentArticle.setArticleTitle(title);
        //System.out.println("ARTICLE CONTENT: " + ArticleContent);
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