package guru.haun.kaban.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;
import java.util.logging.Level;

import guru.haun.kaban.KaBan;
import guru.haun.kaban.KaBanBanList;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

public class KabanPreLoginListener implements Listener {

	private static Date timeZero;
	private KaBan kaban;
	public KabanPreLoginListener(KaBan kaban){
		this.kaban = kaban;
		Calendar tempCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		tempCal.setTimeInMillis(0);
		timeZero = tempCal.getTime();
	}
	
	@EventHandler
	public void onPlayerPreLogin(AsyncPlayerPreLoginEvent e){
		UUID uuid = e.getUniqueId();
		String banmsg;
		kaban.getServer().getLogger().log(Level.INFO, "Checking for bans on " +uuid);
		for(KaBanBanList ban : kaban.banlist){
			kaban.getServer().getLogger().log(Level.INFO,"Ban found for: " + ban.banned);
			if(ban.banned.equals(uuid)){
				kaban.getServer().getLogger().log(Level.INFO, "Found a ban for " +uuid);
				banmsg = "You were banned by " + ban.bannerName + " on " + ban.bannedTime.toString() + " until " +
						( ban.expireTime.compareTo(timeZero) == 0 ? "the end of time" : ban.expireTime.toString() ) + 
						"for the following reason: " + ban.reason;
				if(ban.bannedName != e.getName())
					banmsg += ChatColor.LIGHT_PURPLE + "Nice try changing your name, " + ban.bannedName;
				e.setKickMessage(banmsg);
				e.setLoginResult(Result.KICK_BANNED);
			}
		}
	}
}
