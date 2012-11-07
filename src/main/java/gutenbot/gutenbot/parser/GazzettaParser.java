package gutenbot.gutenbot.parser;

import gutenbot.gutenbot.dto.Article;

public class GazzettaParser implements Parser {

	@Override
	public Article parse(String url) {
		System.out.println("gazzetta!");
		return null;
	}

}