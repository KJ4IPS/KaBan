package guru.haun.kaban.listener;

import java.util.UUID;

import guru.haun.kaban.KaBan;
import guru.haun.kaban.KaBanBanEntry;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

public class KabanPreLoginListener implements Listener {

	private KaBan kaban;
	public KabanPreLoginListener(KaBan kaban){
		this.kaban = kaban;
	}
	
	@EventHandler
	public void onPlayerPreLogin(AsyncPlayerPreLoginEvent e){
		UUID uuid = e.getUniqueId();
		String banmsg;
		for(KaBanBanEntry ban : kaban.banlist){
			if(ban.getBanned().equals(uuid)){
				if(ban.hasExpired()){
					kaban.removeBan(ban);
					return;
				}
				e.setLoginResult(Result.KICK_BANNED);
				banmsg = kaban.messenger.playerKickBanMessage(ban);
				if(!ban.getBannedName().equalsIgnoreCase(e.getName()))
					banmsg += "\n" + ChatColor.LIGHT_PURPLE + "Nice try changing your name, " + ban.getBannedName() + 
					", or shall I say, " + e.getName();
				e.setKickMessage(banmsg);
			}
		}
	}
}
