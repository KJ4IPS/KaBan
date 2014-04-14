package guru.haun.kaban.commands;

import guru.haun.kaban.KaBan;
import guru.haun.kaban.command.Executor;

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
		
		addSub("test","kaban.test").allowNonPlayer();
		
	}
	
}
