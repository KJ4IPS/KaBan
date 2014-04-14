package guru.haun.kaban;

import guru.haun.kaban.commands.KabanCmdGroup;
import guru.haun.kaban.listener.KabanPreLoginListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.PersistenceException;

import org.bukkit.plugin.java.JavaPlugin;

public class KaBan extends JavaPlugin {
	private static String kabanVersion;
	public KaBanMessenger messenger;
	
	
	public List<KaBanBanEntry> banlist = new ArrayList<KaBanBanEntry>();
	
	
	@Override
	public void onEnable(){
		messenger = new KaBanMessenger("KaBan");
		kabanVersion = this.getDescription().getVersion();
		this.getLogger().log(Level.WARNING, "This is a development version of KaBan, USE AT YOUR OWN RISK!");
		try {
			getDatabase().find(KaBanBanEntry.class).findRowCount();
		}catch(PersistenceException ex) {
			installDDL();
		}
		
		this.getCommand("kaban").setExecutor(new KabanCmdGroup(this));
		this.getServer().getPluginManager().registerEvents(new KabanPreLoginListener(this), this);
		loadBanlistFromDB();
	}
	
	public void removeBan(KaBanBanEntry ban){
		banlist.remove(ban);
		this.getDatabase().delete(ban);
	}
	
	public void addBan(KaBanBanEntry ban) {
		banlist.add(ban);
		this.getDatabase().save(ban);
		if(this.getServer().getPlayer(ban.getBanned()) != null){
			this.getServer().getPlayer(ban.getBanned()).kickPlayer(this.messenger.playerKickBanMessage(ban));
		}
	}
	
	public List<Class<?>> getDatabaseClasses() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(KaBanBanEntry.class);
		return list;
	}
	
	
	public void loadBanlistFromDB(){
		List<KaBanBanEntry> dbresults = this.getDatabase().find(KaBanBanEntry.class).findList(); // on the main thread, maybe async this later
		banlist.clear(); //clear the old banlist, else we get dupes
		banlist.addAll(dbresults);
	}
	
	
	public void onDisable(){
		
	}
	
	public String getKabanVersion(){
		return kabanVersion;
	}
}
