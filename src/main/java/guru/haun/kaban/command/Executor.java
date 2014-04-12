package guru.haun.kaban.command;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

public class Executor implements CommandExecutor, TabCompleter {

	public final Plugin plugin;
	
	public boolean isSubCommand = false;
	public String defaultSubCommand = "help";
	
	public final Map<String, SubCommand> subCmds = new LinkedHashMap<String,SubCommand>();
	
	
	public Executor(Plugin plugin, Boolean subCommand) {
		this.isSubCommand = subCommand;
		this.plugin = plugin;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String commandLabel, String[] commandArgs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] commandArgs) {
		// TODO Auto-generated method stub
		return false;
	}

}
