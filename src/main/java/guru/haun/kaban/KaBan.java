package guru.haun.kaban;

import guru.haun.kaban.commands.KabanCmdGroup;
import guru.haun.kaban.listener.KabanPreLoginListener;
import guru.haun.kaban.persistance.ActiveBansDBO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class KaBan extends JavaPlugin {
	private static String kabanVersion;
	public KaBanMessenger messenger;
	
	
	public List<KaBanBanList> banlist = new ArrayList<KaBanBanList>();
	
	
	@Override
	public void onEnable(){
		messenger = new KaBanMessenger("KaBan");
		kabanVersion = this.getDescription().getVersion();
		this.getLogger().log(Level.WARNING, "This is a development version of KaBan, USE AT YOUR OWN RISK!");
		
		KaBanBanList testentry = new KaBanBanList();
		testentry.banned = UUID.fromString("65e697b0-e110-4f27-911f-a64b1eb47463");
		testentry.banner = UUID.fromString("718cf671-9084-4e78-b91f-033e80aa11bf");
		testentry.bannedName = "NotBahamut";
		testentry.bannerName = "KJ4IPS";
		testentry.bannedTime = new Date();
		testentry.bannedTime.setTime(5);
		testentry.expireTime = new Date();
		testentry.expireTime.setTime(0);
		testentry.reason = "We do what we must because we can";
		banlist.add(testentry);
		
		this.getCommand("kaban").setExecutor(new KabanCmdGroup(this));
		this.getServer().getPluginManager().registerEvents(new KabanPreLoginListener(this), this);
	}
	
	
	public void loadBanlistFromDB(){
		List<ActiveBansDBO> dbresults = this.getDatabase().find(ActiveBansDBO.class).findList(); // on the main thread, maybe async this later
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
		}
	}
	
	public void saveBanToDB(KaBanBanList ban){
		ActiveBansDBO dbban = new ActiveBansDBO(ban.dbid, ban.banned, ban.bannedName, ban.bannedTime,
				ban.expireTime, ban.banner, ban.bannerName, ban.reason);
		this.getDatabase().save(dbban);
	}
	
	
	public void onDisable(){
		
	}
	
	public String getKabanVersion(){
		return kabanVersion;
	}
}
