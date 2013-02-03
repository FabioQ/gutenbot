package gutenbot.gutenbot.parser;

import java.io.IOException;

import com.sun.syndication.feed.synd.SyndEntry;
import gutenbot.gutenbot.dto.Article;

public interface Parser {
	
	Article parse(SyndEntry entry) throws IOException, ArticleNotFoundException;
	Article addEnclosure(Article withoutEnclosure, String enclosure);

}
