package guru.haun.kaban.command;

import java.util.List;

public interface SubCommandHandler {
	
	//A wimpy stub-interface for things to process subcommands
	public void handle(CmdInfo info);
	public List<String> handleComplete(CmdInfo info);

}
