package guru.haun.kaban;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class KaBan extends JavaPlugin {
	private static String kabanVersion;
	
	@Override
	public void onEnable(){
		kabanVersion = this.getDescription().getVersion();
		this.getLogger().log(Level.WARNING, "This is a development version of KaBan, USE AT YOUR OWN RISK!");
	}
	
	public void onDisable(){
		
	}
	
	public String getKabanVersion(){
		return kabanVersion;
	}
}
