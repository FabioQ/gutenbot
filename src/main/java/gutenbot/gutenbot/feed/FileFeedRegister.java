package gutenbot.gutenbot.feed;

import gutenbot.gutenbot.util.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class FileFeedRegister implements FeedRegister {
	
	Logger logger = Logger.getLogger(FileFeedRegister.class);
	File register;
	
	public FileFeedRegister(String registerPath, String filename) throws IOException{
		File dir = new File(registerPath);
		
		if(dir.exists() && dir.isDirectory()){
			File register = new File(dir.getAbsolutePath()+System.getProperty("file.separator")+filename);
			if(!register.exists()){
				register.createNewFile();
			}
			this.register = register;
		}else{
			logger.error("Invalid register directory: "+registerPath);
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see gutenbot.gutenbot.feed.Register#getDate()
	 */
	@Override
	public Date getLastDate() {
		Date date = null; 
		String lastLine = Util.readLastLine(register);
		Scanner sc = new Scanner(lastLine);
		if(sc.hasNextLong()){
			date = new Date (sc.nextLong());
		}
		return date;
	}
	
	/* (non-Javadoc)
	 * @see gutenbot.gutenbot.feed.Register#setDate(java.util.Date)
	 */
	@Override
	public void setLastDate(Date date){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(register, true)); 
			out.write(" "+date.getTime()+" "+date.toString()+"\n"); 
			out.close();
		} catch (IOException e) {
			logger.error("Error setting last date", e);
			e.printStackTrace();
		}
	}

}
