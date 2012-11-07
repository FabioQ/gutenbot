package gutenbot.gutenbot.parser;

import gutenbot.gutenbot.dto.Article;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class GazzettaParser implements Parser {

	@Override
	public Article parse(String url) throws IOException{
		System.out.println("gazzetta!");
        System.out.println("Fetching "+ url);
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            System.out.println(link.attr("abs:href"));
        }
		return null;
	}

}