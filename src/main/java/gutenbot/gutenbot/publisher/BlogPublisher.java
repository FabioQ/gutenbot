package gutenbot.gutenbot.publisher;

import java.net.MalformedURLException;

import redstone.xmlrpc.XmlRpcFault;

import net.bican.wordpress.Page;
import net.bican.wordpress.Wordpress;

public class BlogPublisher {
	
	public static void main(String[] args) throws MalformedURLException, XmlRpcFault {
		Wordpress WP = new Wordpress("admin", "alonso11", "http://www.sportsponsorizzazioni.com/xmlrpc.php");
	    Page post = new Page();
	    post.setTitle("titolo prova");
	    post.setDescription("prova post");
	    String newPostIds = WP.newPost(post, true);
	    int newPostId = Integer.valueOf(newPostIds).intValue();
	    Page postNow = WP.getPost(newPostId);
		
	}

}
