package guru.haun.kaban;

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
}
