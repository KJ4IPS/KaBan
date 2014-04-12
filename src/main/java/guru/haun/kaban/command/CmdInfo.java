package guru.haun.kaban.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdInfo {

	//information related to the execution of a command
	
	private final CommandSender sender;
	private final Player player; //<-- Might be null
	private final String[] commandArgs;
	
	public CmdInfo(CommandSender sender,Player player, String[] commandArgs){
		this.sender = sender;
		this.player = player;
		this.commandArgs = commandArgs;
	}
	
	public CmdInfo(CommandSender sender, String[] commandArgs){
		this(sender,(Player) null, commandArgs);
	}
	
	public CmdInfo(Player player, String[] commandArgs){
		this(player,player,commandArgs);
	}
	
	
	
	public Player getPlayer() {
		return this.player;
	}
	
	public CommandSender getSender() {
		return this.sender;
	}
	
	public String[] getArgs() {
		return this.commandArgs;
	}
	
	public String getArg(int i) {
		return this.commandArgs[i];
	}
	
}
