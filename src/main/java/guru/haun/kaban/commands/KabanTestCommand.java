package guru.haun.kaban.commands;

import guru.haun.kaban.KaBan;
import guru.haun.kaban.KaBanBanEntry;
import guru.haun.kaban.command.CmdInfo;
import guru.haun.kaban.command.SubCommandHandler;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

public class KabanTestCommand implements SubCommandHandler {

	private KaBan kaban;
	public KabanTestCommand(KaBan kaban){
		this.kaban = kaban;
	}
	
	@Override
	public void handle(CmdInfo info) {
		Calendar expireTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		expireTime.add(Calendar.SECOND, Integer.parseInt(info.getArg(3)));
		KaBanBanEntry ban = new KaBanBanEntry(
				UUID.fromString(info.getArg(1)),
				info.getArg(2),
				Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime(),
				expireTime.getTime(),
				info.getPlayer().getUniqueId(),
				info.getPlayer().getName(),
				info.getArg(4)
				);
		kaban.banlist.add(ban);
		kaban.getDatabase().save(ban);
		
	}
		@Override
	public List<String> handleComplete(CmdInfo info) {
		// TODO Auto-generated method stub
		return null;
	}

}
