package gutenbot.gutenbot.feed;

import gutenbot.gutenbot.util.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class FeedRegister {
	
	Logger logger = Logger.getLogger(FeedRegister.class);
	File register;
	
	public FeedRegister(String registerPath, String filename){
		File dir = new File(registerPath);
		
		if(dir.exists() && dir.isDirectory()){
			File register = new File(dir.getAbsolutePath()+System.getProperty("file.separator")+filename);
			if(register.exists()){
				this.register = register;
			}
		}else{
			logger.error("Invalid register directory: "+registerPath);
		}
		
		
	}
	
	public Date getDate() {
		Date date = null; 
		String lastLine = Util.tail(register);
		Scanner sc = new Scanner(lastLine);
		if(sc.hasNextLong()){
			date = new Date (sc.nextLong());
		}
		return date;
	}
	
	public void setDate(Date date){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(register, true)); 
			out.write(" "+date.getTime()+" "+date.toString()+"\n"); 
			out.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error", e);
			e.printStackTrace();
		}
	}

}
