package guru.haun.kaban.commands;

import java.util.List;

import guru.haun.kaban.KaBan;
import guru.haun.kaban.command.CmdInfo;
import guru.haun.kaban.command.SubCommandHandler;

public class KabanAboutCmd implements SubCommandHandler {
	
	private final KaBan kaban;
	
	public KabanAboutCmd(KaBan kaban) {
		this.kaban = kaban;
	}
	
	public void handle(CmdInfo info) {
		info.getSender().sendMessage("This is KaBan by KJ4IPS v" + kaban.getKabanVersion());
	}

	@Override
	public List<String> handleComplete(CmdInfo info) {
		return null;
	}
}
