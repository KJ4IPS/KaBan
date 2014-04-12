package guru.haun.kaban.command;

import org.bukkit.entity.Player;

public class SubCommand {

	private SubCommandHandler cmdHandler = null;
	private int minimumArguments = 0;
	private final String[] aliases;	
	private String[] permNodes;
	private boolean needsPlayer = true;
	
	//with aliases and perms
	public SubCommand(String[] aliases, String[] permNodes) {
		this.aliases = aliases;
		this.permNodes = permNodes;
	}
	
	//add a handler
	public SubCommand(String[] aliases, String[] permNodes, SubCommandHandler handler) {
		this(aliases,permNodes);
		this.cmdHandler = handler;
	}
	
	
	
	public SubCommand allowNonPlayer() {
		this.needsPlayer = false;
		return this;
	}
	
	public boolean isNonPlayerAllowed() {
		return !this.needsPlayer;
	}
	
	
	public int getMinArgs(){
		return this.minimumArguments;
	}
	
	public SubCommand setMinArgs(int num) {
		this.minimumArguments = num;
		return this;
	}
	
	
	public SubCommandHandler getHandler() {
		return this.cmdHandler;
	}
	
	public SubCommand setHandler(SubCommandHandler handler) {
		this.cmdHandler = handler;
		return this;
	}
	
	
	public void setPermNodes(String[] permNodes) {
		this.permNodes = permNodes;
	}
	
	public boolean mayPlayer(Player player) {
		for(String node : permNodes) {
			if(player.hasPermission(node))
				return true;
			//now check for all of the possible * nodes
			int i = node.lastIndexOf('.');
			while(i != -1) {
				node = node.substring(0,i);//chop off the end
				if(player.hasPermission(node+".*")) //if they have this level as a *
					return true;
				i = node.lastIndexOf('.'); //locate the next point to chop on
			}
		}
		//if we got this far, the player/permissible does not have the node
		return false;
	}


	public String[] getAliases() {
		return this.aliases;
	}
}