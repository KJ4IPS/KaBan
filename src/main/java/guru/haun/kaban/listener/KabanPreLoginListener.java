package guru.haun.kaban.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import guru.haun.kaban.KaBan;
import guru.haun.kaban.KaBanBanEntry;

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
		for(KaBanBanEntry ban : kaban.banlist){
			if(ban.getBanned().equals(uuid)&&!ban.hasExpired()){
				e.setLoginResult(Result.KICK_BANNED);
				banmsg = ChatColor.DARK_AQUA + "You were banned by " + ChatColor.GOLD + ban.getBannerName() +
						ChatColor.DARK_AQUA +"\non " + ChatColor.YELLOW +ban.getBannedTime().toString() + 
						ChatColor.DARK_AQUA +"\nuntil " + ChatColor.GREEN +
						( ban.getExpireTime().compareTo(timeZero) == 0 ? ChatColor.RED + "the end of time" : ban.getExpireTime().toString() ) + 
						ChatColor.DARK_AQUA + "\nReason: " + ChatColor.AQUA + ban.getReason();
				if(!ban.getBannedName().equalsIgnoreCase(e.getName()))
					banmsg += "\n" + ChatColor.LIGHT_PURPLE + "Nice try changing your name, " + ban.getBannedName() + 
					", or shall I say, " + e.getName();
				e.setKickMessage(banmsg);
			}
		}
	}
}
