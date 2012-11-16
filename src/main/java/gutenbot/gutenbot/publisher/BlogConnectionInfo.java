package gutenbot.gutenbot.publisher;

public class BlogConnectionInfo {

	private String apiKey;
	private String userName;
	private String password;
	private String blogId;

	public BlogConnectionInfo(String apiKey, String userName,
		String password, String blogId) {
	    this.apiKey = apiKey;
	    this.userName = userName;
	    this.password = password;
	    this.blogId = blogId;
	}

	public String getApiKey() {
	    return apiKey;
	}
	public String getBlogId() {
	    return blogId;
	}
	public String getPassword() {
	    return password;
	}
	public String getUserName() {
	    return userName;
	}
	
}
