package gutenbot.gutenbot.dto;

public class Article {
	
	String articleTitle;
	String articleContent;
	String articlePhotoURL;
	
	public Article(String articleTitle, String articleContent, String articleEnclosure) {
		super();
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articlePhotoURL = articleEnclosure;
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
	
	public String getArticlePhotoURL() {
		return articlePhotoURL;
	}

	public void setArticlePhotoURL(String articlePhotoURL) {
		this.articlePhotoURL = articlePhotoURL;
	}
}
