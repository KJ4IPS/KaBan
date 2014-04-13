package guru.haun.kaban.commands;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import guru.haun.kaban.KaBan;
import guru.haun.kaban.KaBanBanList;
import guru.haun.kaban.command.CmdInfo;
import guru.haun.kaban.command.Executor;
import guru.haun.kaban.command.SubCommandHandler;

public class KabanCmdGroup extends Executor {
	
	public KabanCmdGroup(KaBan kaban) {
		super(kaban, true);
		initCommands();
	}
	
	private void initCommands() {
		final KaBan kaban = (KaBan) plugin;
		
		// /kaban about
		addSub( new String[] {"about", "defaulthandler"}, "kaban.about").allowNonPlayer().setHandler(new KabanAboutCmd(kaban));
		addSub( new String[] {"help", "h", "wtf", "?"}, "kaban.help").allowNonPlayer().setHandler(new KabanHelpCmd(kaban));
		
		addSub("test","kaban.test").allowNonPlayer().setHandler(new SubCommandHandler(){

	
			@Override
			public void handle(CmdInfo info) {
				Calendar expireTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
				expireTime.add(Calendar.SECOND, Integer.parseInt(info.getArg(3)));
				kaban.saveBanToDB(new KaBanBanList(
						UUID.fromString(info.getArg(1)),
						info.getArg(2),
						Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime(),
						expireTime.getTime(),
						info.getPlayer().getUniqueId(),
						info.getPlayer().getName(),
						info.getArg(4)
						));
				
			}

			@Override
			public List<String> handleComplete(CmdInfo info) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
		});
		
	}
	
}
