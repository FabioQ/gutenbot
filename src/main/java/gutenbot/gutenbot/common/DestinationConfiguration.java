package gutenbot.gutenbot.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DestinationConfiguration {

	Logger logger = Logger.getLogger(DestinationConfiguration.class);
	File configurationFile;
	LinkedList<String> urls = new LinkedList<String>();
	boolean valid;
	String destinationName;
	String destinationUserName;
	String destinationUserPassword;
	String destinationXmlRpcUrl;

	public DestinationConfiguration(File configurationFile) {

		logger.info("loading configuration from: " + configurationFile.getName());

		this.configurationFile = configurationFile;

		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new FileInputStream(configurationFile));
			List list = document.selectNodes("/gbconfig/feeds/url");
			for(Object o:list){
				Element url = (Element) o;
				urls.add(url.getText());
			}
			
			destinationName = ( (Element) document.selectSingleNode("/gbconfig/destination/domain") ).getText();
			destinationUserName = ( (Element) document.selectSingleNode("/gbconfig/destination/username") ).getText();
			destinationUserPassword = ( (Element) document.selectSingleNode("/gbconfig/destination/password") ).getText();
			destinationXmlRpcUrl = ( (Element) document.selectSingleNode("/gbconfig/destination/xmlrpcurl") ).getText();
					
			valid = true;
		} catch (Exception e) {
			valid = false;
			logger.error("Error parsing configuration file", e);
		}

	}

	public LinkedList<String> getFeedURLs() {
		return urls;
	}

	public String getName() {
		return configurationFile.getName();
	}

	public boolean isValid() {
		return valid;
	}
	
	public String getDestinationName() {
		return destinationName;
	}
	
	public String getDestinationUserName() {
		return destinationUserName;
	}
	
	public String getDestinationUserPassword() {
		return destinationUserPassword;
	}
	
	public String getDestinationXmlRpcUrl() {
		return destinationXmlRpcUrl;
	}

}
