package guru.haun.kaban;

import guru.haun.kaban.commands.KabanCmdGroup;
import guru.haun.kaban.listener.KabanPreLoginListener;

import java.util.ArrayList;
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
		//	getDatabase().find(ActiveBansDBO.class).findRowCount();
		}catch(PersistenceException ex) {
			installDDL();
		}
		
		this.getCommand("kaban").setExecutor(new KabanCmdGroup(this));
		this.getServer().getPluginManager().registerEvents(new KabanPreLoginListener(this), this);
	}
	
	public List<Class<?>> getDatabaseClasses() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(KaBanBanEntry.class);
		return list;
	}
	
	
	public void loadBanlistFromDB(){
		/*List<ActiveBansDBO> dbresults = this.getDatabase().find(ActiveBansDBO.class).findList(); // on the main thread, maybe async this later
		banlist.clear(); //clear the old banlist, else we get dupes
		for(ActiveBansDBO ab : dbresults){
			banlist.add(new KaBanBanList(
					ab.getBanned(),
					ab.getBannedName(),
					ab.getBannedTime(),
					ab.getExpireTime(),
					ab.getBanner(),
					ab.getBannerName(),
					ab.getReason()
					));
		}*/
	}
	
	public void saveBanToDB(KaBanBanEntry ban){
		//ActiveBansDBO dbban = new ActiveBansDBO(ban.dbid, ban.banned, ban.bannedName, ban.bannedTime,
		//		ban.expireTime, ban.banner, ban.bannerName, ban.reason);
		//this.getDatabase().save(dbban);
	}
	
	
	public void onDisable(){
		
	}
	
	public String getKabanVersion(){
		return kabanVersion;
	}
}
