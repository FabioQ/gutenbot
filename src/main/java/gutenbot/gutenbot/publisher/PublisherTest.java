package gutenbot.gutenbot.publisher;

import java.io.IOException;
import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import gutenbot.gutenbot.publisher.BlogPublisher.PostType;

public class PublisherTest {
	
	 public static void main(String[] args) throws Exception {
		    // the url of your xmlrpc.php, typically
		    // of the form http://your.domain.here/wordpress/xmlrpc.php
		 	// or http://sUsername.wordpress.com/xmlrpc.php?
		    String xmlRpcUrl = args[0];
		    // this key is not used in my wordpress version
		    String apiKey = args[1];
		    String userName = args[2];
		    String password = args[3];
		    // in my wordpress version the blogId is "1"
		    String blogId = args[4];

		    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		    config.setServerURL(new URL(xmlRpcUrl));

		    XmlRpcClient client = new XmlRpcClient();
		    client.setConfig(config);

		    BlogConnectionInfo blogInfo = new BlogConnectionInfo(apiKey, userName, password, blogId);

		    BlogPublisher poster = new BlogPublisher(client, blogInfo);
		    poster.setPostType(PostType.publish);
		    poster.post(contents());
		  }

		  private static String contents() throws IOException {
		    // According to the wordpress post format the title and
		    // category id of the post get some special mark up.
		    return ("<title>Look how this wordpress post got created from java!</title>"
		    + "<category>6</category>"
		    + "Testo Articolo");
		  }

}
