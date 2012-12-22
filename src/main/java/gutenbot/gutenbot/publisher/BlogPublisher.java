package gutenbot.gutenbot.publisher;

import gutenbot.gutenbot.parser.GazzettaParser;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;

import redstone.xmlrpc.XmlRpcArray;
import redstone.xmlrpc.XmlRpcFault;

import net.bican.wordpress.Page;
import net.bican.wordpress.Wordpress;

import java.util.Date;

public class BlogPublisher {

	Logger logger = Logger.getLogger(BlogPublisher.class);
	private Wordpress WP;
	private Page post;
	
	public void blogConnect(String username, String password, String xmlrpcurl) throws MalformedURLException, XmlRpcFault {
		//xmlrpc url template http://www.sportsponsorizzazioni.com/xmlrpc.php
		this.WP = new Wordpress(username, password, xmlrpcurl);
	    logger.debug("Connected to blog.");
	}
	
	public void blogPublish(String articleTitle, String articleText, Date pubDate, String sourceDomain, String sourceAuthor, String blogCategory) throws XmlRpcFault{
		String fullArticleContent = articleText + "<br><br><br>- Author: " + sourceAuthor + "<br>- Source: <a href=\"http://" + sourceDomain + "\" rel=\"nofollow\">"+ sourceDomain +"</a>" ;
		//TODO use the category arg to create the XMLRPC array and select categories
		//add tags - there is no tag methos, so it will be done with an automatic tag system plugin.
		this.post = new Page();
	    this.post.setTitle(articleTitle);
	    this.post.setDescription(fullArticleContent);
	    this.post.setDateCreated(pubDate);
	    String newPostIds = this.WP.newPost(post, true);
	    int newPostId = Integer.valueOf(newPostIds).intValue();
	    Page postNow = WP.getPost(newPostId);
		System.out.println("Article Posted.  Title=> "+ articleTitle);
	}

}
