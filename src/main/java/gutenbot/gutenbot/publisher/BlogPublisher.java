package gutenbot.gutenbot.publisher;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

public class BlogPublisher {
	
	private static final String POST_METHOD_NAME = "blogger.newPost";

	  private XmlRpcClient client;
	  private BlogConnectionInfo blogInfo;
	  private PostType postType = PostType.draft;

	  public BlogPublisher(XmlRpcClient client, BlogConnectionInfo blogInfo) {
	    this.client = client;
	    this.blogInfo = blogInfo;
	  }

	  public void setPostType(PostType postType) {
	    this.postType = postType;
	  }

	  public Integer post(String contents) throws XmlRpcException {
	    Object[] params = new Object[] {
	        blogInfo.getApiKey(),
	        blogInfo.getBlogId(),
	        blogInfo.getUserName(),
	        blogInfo.getPassword(),
	        contents,
	        postType.booleanValue()
	    };
	    return (Integer) client.execute(POST_METHOD_NAME, params);
	  }

	  public static enum PostType {
	    publish(true), draft(false);

	    private final boolean value;

	    PostType(boolean value) {
	      this.value = value;
	    }

	    public boolean booleanValue() {
	      return value;
	    }
	  }

}
