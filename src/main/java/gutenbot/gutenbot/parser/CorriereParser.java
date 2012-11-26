package gutenbot.gutenbot.parser;

import gutenbot.gutenbot.dto.Article;

public class CorriereParser implements Parser {

	@Override
	public Article parse(String url) {
		System.out.println("corriere!");
		return null;
	}

	@Override
	public Article addEnclosure(Article withoutEnclosure, String enclosure) {
		// TODO Auto-generated method stub
		return null;
	}

}
