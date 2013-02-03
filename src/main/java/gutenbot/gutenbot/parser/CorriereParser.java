package gutenbot.gutenbot.parser;

import com.sun.syndication.feed.synd.SyndEntry;
import gutenbot.gutenbot.dto.Article;

public class CorriereParser implements Parser {

	@Override
	public Article parse(SyndEntry entry) {
		System.out.println("corriere!");
		return null;
	}

	@Override
	public Article addEnclosure(Article withoutEnclosure, String enclosure) {
		// TODO Auto-generated method stub
		return null;
	}

}
