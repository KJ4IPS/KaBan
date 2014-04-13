package guru.haun.kaban.commands;

import java.util.List;

import org.bukkit.ChatColor;

import guru.haun.kaban.KaBan;
import guru.haun.kaban.command.CmdInfo;
import guru.haun.kaban.command.SubCommandHandler;

public class KabanHelpCmd implements SubCommandHandler {
	
	private final KaBan kaban;
	
	private static String[][] helplist = new String[][] {
		{" /kaban", "about", "Show information about KaBan"},
		{" /kaban", "help", "Show this screen"}
	};
	
	public KabanHelpCmd(KaBan kaban){
		this.kaban = kaban;
	}

	@Override
	public void handle(CmdInfo info) {
		info.getSender().sendMessage(kaban.messenger.playerMsgHeader(ChatColor.AQUA + "KaBan" + ChatColor.GRAY + " - A banning soloution"));
		for(String[] help:helplist){
			info.getSender().sendMessage(kaban.messenger.playerHelp(help[0], help[1], help[2]));
		}
	}

	@Override
	public List<String> handleComplete(CmdInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

}
