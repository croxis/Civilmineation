package net.croxis.plugins.civilmineation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandDebug implements CommandExecutor {

	public CommandDebug() {
		super();
	}
	
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(args.length == 0)
    		return false;
    	if (args[0].equalsIgnoreCase("debug")){
    		Civilmineation.debug = !Civilmineation.debug;
    		sender.sendMessage("Civilmineation debug toggled");
    		return true;
    	}
    	return false;
	}

}
