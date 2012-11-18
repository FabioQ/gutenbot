package gutenbot.gutenbot.publisher;

import gutenbot.gutenbot.parser.GazzettaParser;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import redstone.xmlrpc.XmlRpcFault;

import net.bican.wordpress.Page;
import net.bican.wordpress.Wordpress;

public class BlogPublisher {
	
	Logger logger = Logger.getLogger(BlogPublisher.class);
	private Wordpress WP;
	private Page post;
	
	public void blogConnect(String username, String password, String xmlrpcurl) throws MalformedURLException, XmlRpcFault {
		//xmlrpc url template http://www.sportsponsorizzazioni.com/xmlrpc.php
		this.WP = new Wordpress(username, password, xmlrpcurl);
	    logger.debug("Connected to blog.");
	}
	
	public void blogPublish(String articleTitle, String articleText) throws XmlRpcFault{
		this.post = new Page();
	    this.post.setTitle(articleTitle);
	    this.post.setDescription(articleText);
	    String newPostIds = this.WP.newPost(post, true);
	    int newPostId = Integer.valueOf(newPostIds).intValue();
	    Page postNow = WP.getPost(newPostId);
		System.out.println("Article Posted.");
	}

}
