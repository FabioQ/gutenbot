package gutenbot.gutenbot.common;

import java.io.File;
import java.util.LinkedList;

import org.apache.log4j.Logger;

public class GutenBotConfiguration {
	
	Logger logger = Logger.getLogger(GutenBotConfiguration.class);
	LinkedList<DestinationConfiguration> configurations = new LinkedList<DestinationConfiguration>();
	File dir;
	
	public GutenBotConfiguration(String directory) {
		
		dir = new File(directory);
		
		logger.info("loading configuration files from: "+dir.getAbsolutePath());
		
		if(dir.exists() && dir.isDirectory()){
			for(String s:dir.list()){
				String configurationPath = dir.getAbsolutePath()+System.getProperty("file.separator")+s;
				
				if(configurationPath.endsWith("xml")) {
					File configurationFile = new File(configurationPath);
					DestinationConfiguration configuration = new DestinationConfiguration(configurationFile);
					if(configuration.isValid()){
						configurations.add(configuration);
					}
				}
			}
		}else{
			logger.error("Invalid configurations directory");
		}
		
	}
	
	public LinkedList<DestinationConfiguration> getDomainConfigurations() {
		return configurations;
	}

}
