package guru.haun.kaban.commands;

import java.util.List;

import org.bukkit.ChatColor;

import guru.haun.kaban.KaBan;
import guru.haun.kaban.command.CmdInfo;
import guru.haun.kaban.command.SubCommandHandler;

public class KabanAboutCmd implements SubCommandHandler {
	
	private final KaBan kaban;
	
	public KabanAboutCmd(KaBan kaban) {
		this.kaban = kaban;
	}
	
	public void handle(CmdInfo info) {
		info.getSender().sendMessage(kaban.messenger.playerMsgSubHeader(ChatColor.AQUA + "KaBan" + ChatColor.GRAY+ 
				" by " + ChatColor.GOLD + "KJ4IPS" + ChatColor.GRAY + " version " + ChatColor.WHITE + kaban.getKabanVersion()));
		info.getSender().sendMessage(kaban.messenger.playerMsgSub("For help, run " + ChatColor.GREEN + "/kaban help"));
	}

	@Override
	public List<String> handleComplete(CmdInfo info) {
		return null;
	}
}
