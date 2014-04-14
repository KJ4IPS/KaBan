package guru.haun.kaban.commands;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import guru.haun.kaban.KaBan;
import guru.haun.kaban.KaBanBanEntry;
import guru.haun.kaban.command.CmdInfo;
import guru.haun.kaban.command.SubCommandHandler;

public class KabanBanCmd implements SubCommandHandler {

	
	private final KaBan kaban;
	
	public KabanBanCmd(KaBan kaban){
		this.kaban = kaban;
	}

	@Override
	public void handle(CmdInfo info) {
		String[] args = info.getArgs();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		KaBanBanEntry ban = new KaBanBanEntry();
		ban.setBanner(info.getPlayer().getUniqueId());
		ban.setBannerName(info.getPlayer().getName());
		ban.setBannedTime(cal.getTime());
		//TODO: better time handling
		if(Integer.parseInt(args[2]) == 0)
			cal.setTimeInMillis(0);
		else
			cal.add(DateFormat.SECOND_FIELD, Integer.parseInt(args[2]));
		ban.setExpireTime(cal.getTime());
		//Now, for the UUID
			//Is the player online
		if(kaban.getServer().getPlayer(info.getArg(1)) != null){
			Player player = kaban.getServer().getPlayer(args[1]);
			ban.setBanned(player.getUniqueId());
			ban.setBannedName(player.getName());
		}else{ //player is not online, have to go Name -> UUID
			OfflinePlayer player = kaban.getServer().getOfflinePlayer(args[1]);
			ban.setBanned(player.getUniqueId());
			ban.setBannedName(player.getName());
		}
		
		String reason = "";
		for(Integer i=3; i < args.length;i++){
			reason += args[i];
		}
		kaban.addBan(ban);
	}

	@Override
	public List<String> handleComplete(CmdInfo info) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
