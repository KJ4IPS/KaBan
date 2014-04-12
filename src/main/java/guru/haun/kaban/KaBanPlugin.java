package guru.haun.kaban;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class KaBanPlugin extends JavaPlugin {
	@Override
	public void onEnable(){
		this.getLogger().log(Level.WARNING, "This is a development version of KaBan, USE AT YOUR OWN RISK!");
	}
	
	public void onDisable(){
		
	}
}
