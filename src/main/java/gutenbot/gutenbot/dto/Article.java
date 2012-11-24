package gutenbot.gutenbot.dto;

public class Article {
	
	String articleTitle;
	String articleContent;
	
	public Article(String articleTitle, String articleContent) {
		super();
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
}
