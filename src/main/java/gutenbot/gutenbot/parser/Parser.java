package gutenbot.gutenbot.parser;

import java.io.IOException;

import gutenbot.gutenbot.dto.Article;

public interface Parser {
	
	Article parse(String url) throws IOException, ArticleNotFoundException;
	Article addEnclosure(Article withoutEnclosure, String enclosure);

}
