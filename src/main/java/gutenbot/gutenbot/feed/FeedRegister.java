package gutenbot.gutenbot.feed;

import java.util.Date;

public interface FeedRegister {

	public abstract Date getLastDate();

	public abstract void setLastDate(Date date);

}