package guru.haun.kaban;

import guru.haun.kaban.commands.KabanCmdGroup;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class KaBan extends JavaPlugin {
	private static String kabanVersion;
	
	@Override
	public void onEnable(){
		kabanVersion = this.getDescription().getVersion();
		this.getLogger().log(Level.WARNING, "This is a development version of KaBan, USE AT YOUR OWN RISK!");
		this.getCommand("kaban").setExecutor(new KabanCmdGroup(this));
	}
	
	public void onDisable(){
		
	}
	
	public String getKabanVersion(){
		return kabanVersion;
	}
}
