package guru.haun.kaban;

import java.util.Calendar;
import java.util.TimeZone;

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
		;
				message += ChatColor.RED + "time interval" + " remaining";
		return message;
	}
	public String playerKickMessage(String kicker, String reason){
		return ChatColor.DARK_AQUA + "You were kicked by " + ChatColor.GOLD + kicker +
				ChatColor.DARK_AQUA + "\nReason: " + reason;
	}
}
