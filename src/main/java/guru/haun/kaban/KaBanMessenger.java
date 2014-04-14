package guru.haun.kaban;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;

public class KaBanMessenger {
	protected final String pluginName;
	
	//In the style of Crafty's and Darmok's chat messages
	private static ChatColor colorTitle = ChatColor.DARK_AQUA;
	private static String titleSeperator = " // ";
	private static ChatColor colorText = ChatColor.WHITE;
	private static ChatColor colorSubdued = ChatColor.GRAY;
	private static ChatColor colorError = ChatColor.RED;
	private static ChatColor colorSuccess = ChatColor.GREEN;
	
	public KaBanMessenger(String pluginName) {
		this.pluginName = pluginName;
	}
	
	public String playerMsgHeader(String msg) {
		if(msg!=null) 
			return colorTitle + pluginName + titleSeperator + colorText + msg;
		return "";
	}
	
	public String playerMsgSubHeader(String msg) {
		if(msg!=null) 
			return colorTitle + pluginName + titleSeperator + colorSubdued + msg;
		return "";
	}
	
	public String playerMsg(String msg) {
		if(msg!=null) 
			return colorText + msg;
		return "";
	}
	
	public String playerMsgSub(String msg) {
		if(msg!=null) 
			return colorSubdued + msg;
		return "";
	}
	
	public String playerMsgError(String msg) {
		if(msg!=null) 
			return colorTitle + pluginName + titleSeperator + colorError + msg;
		return "";
	}
	
	public String playerMsgSuccess(String msg) {
		if(msg!=null) 
			return colorTitle + pluginName + titleSeperator + colorSuccess + msg;
		return "";
	}
	
	public String playerHelp(String precmd, String cmd, String desc){
		return colorSubdued + precmd + " " + colorText + cmd + colorSubdued + " - " + colorTitle + desc;
	}
	
	public String playerKickBanMessage(KaBanBanEntry ban){
		Calendar tempCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		tempCal.setTimeInMillis(0);
		String message;
		message =  ChatColor.DARK_AQUA + "You were banned by " + ChatColor.GOLD + ban.getBannerName() +
				ChatColor.DARK_AQUA +"\non " + ChatColor.YELLOW +ban.getBannedTime().toString() + 
				ChatColor.DARK_AQUA +"\nuntil " + ChatColor.GREEN +
				( ban.getExpireTime().compareTo(tempCal.getTime()) == 0 ? ChatColor.RED + "the end of time" : ban.getExpireTime().toString() ) + 
				ChatColor.DARK_AQUA + "\nReason: " + ChatColor.AQUA + ban.getReason();
		if(!ban.isPerma())
				message += ChatColor.RED + durationString(banTimeRemain(ban)) + " remaining";
		return message;
	}
	public String playerKickMessage(String kicker, String reason){
		return ChatColor.DARK_AQUA + "You were kicked by " + ChatColor.GOLD + kicker +
				ChatColor.DARK_AQUA + "\nReason: " + reason;
	}
	
	public long banTimeRemain(KaBanBanEntry ban) {
		if(ban.isPerma())
			return -1;
		return ban.getBannedTime().getTime() - Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime().getTime();
	}
	
	public String durationString(long ms){
		//sometime do better stings here
		long seconds;
		long minutes;
		long hours;
		long days;
		long weeks;
		long years;
		
		seconds = ms/1000l;
		ms = ms % 1000;
		minutes = seconds/60l;
		seconds = seconds % 60;
		hours = minutes/60l;
		minutes = minutes % 60;
		days = hours/24l;
		hours = hours % 24;
		weeks = days/7l;
		weeks = weeks % 7;
		years = weeks/52l;
		weeks = weeks % 25;
		return  (years>0?(String.valueOf(years) + "Y "):"") +
				(weeks>0?(String.valueOf(weeks) + "M "):"") +
				(days>0?(String.valueOf(days) + "D "):"") +
				(hours>0?(String.valueOf(hours) + "h "):"") +
				(minutes>0?(String.valueOf(minutes) + "m "):"") +
				(seconds>0?(String.valueOf(seconds) + "s "):"");
	}
}
