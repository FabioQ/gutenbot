package gutenbot.gutenbot.common;

import java.io.File;
import java.util.LinkedList;

import org.apache.log4j.Logger;

public class DomainConfiguration {
	
	Logger logger = Logger.getLogger(DomainConfiguration.class);
	File configurationFile;
	boolean valid;
	
	public DomainConfiguration(File configurationFile) {
		
		logger.info("loading configuration from: "+configurationFile.getName());

		this.configurationFile = configurationFile;
		
		valid = true;
	}
	
	public LinkedList<String> getRssURLs() {
		return null;
	}
	
	public String getName(){
		return configurationFile.getName();
	}

	public boolean isValid() {
		return valid;
	}

}
