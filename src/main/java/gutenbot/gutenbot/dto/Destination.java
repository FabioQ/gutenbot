package gutenbot.gutenbot.dto;

import java.util.LinkedList;

import com.sun.syndication.feed.synd.SyndEntry;

public class Destination {
	
	String destUsername;
	String destPassword;
	String destXmlrpcURL;
	
	public Destination(String destUsername, String destPassword, String destXmlrpcURL) {
		super();
		this.destUsername = destUsername;
		this.destPassword = destPassword;
		this.destXmlrpcURL = destXmlrpcURL;
	}

	public String getDestUsername() {
		return destUsername;
	}

	public void setDestUsername(String destUsername) {
		this.destUsername = destUsername;
	}

	public String getDestPassword() {
		return destPassword;
	}

	public void setDestPassword(String destPassword) {
		this.destPassword = destPassword;
	}

	public String getDestXmlrpcURL() {
		return destXmlrpcURL;
	}

	public void setDestXmlrpcURL(String destXmlrpcURL) {
		this.destXmlrpcURL = destXmlrpcURL;
	}
	

}
