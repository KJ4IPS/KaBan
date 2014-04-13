package guru.haun.kaban.command;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Executor implements CommandExecutor, TabCompleter {

	public final Plugin plugin;
	
	public boolean isSubCommand = false;
	public String defaultSubCommand = "defaulthandler";
	
	public final Map<String, SubCommand> subCmds = new LinkedHashMap<String,SubCommand>();
	
	
	public Executor(Plugin plugin, Boolean subCommand) {
		this.isSubCommand = subCommand;
		this.plugin = plugin;
	}

	//fully featured
	protected SubCommand addSub(String[] cmdAliases, String[] permNodes, SubCommandHandler handler) {
		final SubCommand subCmd = new SubCommand(cmdAliases,permNodes,handler);
		for (final String alias :  cmdAliases)
			subCmds.put(alias,subCmd);
		return subCmd;
	}
	
	//no handler
	protected SubCommand addSub(String[] cmdAliases, String[] permNodes) {
		return addSub(cmdAliases, permNodes,null);
	}
	
	//single Alias
	protected SubCommand addSub(String cmdAlias, String[] permNodes) {
		return addSub(new String[] {cmdAlias} , permNodes,null);
	}
	
	//single perm node
	protected SubCommand addSub(String[] cmdAliases, String permNode) {
		return addSub(cmdAliases, new String[] {permNode},null);
	}

	//single perm node and alias
	protected SubCommand addSub(String cmdAlias, String permNode) {
		return addSub(new String[] {cmdAlias}, new String[] {permNode},null);
	}

	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] commandArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] commandArgs) {
		Player player;
		if(sender instanceof Player)
			player=(Player)sender;
		else
			player=null;
		
		String cmdName;
		if(isSubCommand && commandArgs.length > 0) { //If this is a subcommand
			cmdName = commandArgs[0].toLowerCase(); //get the subcommand's name
		}else{
			cmdName = command.getName();
		}
		
		boolean effSubCommand = isSubCommand; //we may want to go into command, when an invalid command is given
		
		SubCommand subCmd = subCmds.get(cmdName);
		if(subCmd == null) {//unknown command
			subCmd = subCmds.get(defaultSubCommand); //try the default
			if(subCmd == null){ //if we still have none, a command got passed to the plugin that should not have been...
				sender.sendMessage(ChatColor.RED + "Unreconginzed Comand");
				plugin.getLogger().log(Level.WARNING,"Somthing tried to execute the command \"" + commandLabel + "\"(" + cmdName + "), this command is unknown to KaBan");
				return true; //dont let bukkit whine about it too
			} else { //use the default command
				//for this case, we need to leave subcommand mode
				effSubCommand = false;
			}
		}
		
		//now that we know the command exists, we need to see of the user has the required permissions
		//also check for non-player stuff here
		
		if(player == null){
			if(!subCmd.isNonPlayerAllowed()){
				sender.sendMessage("This command requires a player to work properly");
				return true;
			}
		} else {
			if(!subCmd.mayPlayer(player)){
				sender.sendMessage(ChatColor.RED + "Sorry, you lack the permissions required to use '"+ commandLabel + "'(" + cmdName + ").") ;
				return true;
			}
		}
		
		//now to check the arugment count
		
		if((effSubCommand && (commandArgs.length - 1) < subCmd.getMinArgs())
				|| (!effSubCommand && commandArgs.length < subCmd.getMinArgs())){
				sender.sendMessage("This command requires more arguments than you have passed");
				return true;
		}
			
		final CmdInfo info = new CmdInfo(sender,player,commandArgs);
		subCmd.getHandler().handle(info);
		
		return true;
	}

}
