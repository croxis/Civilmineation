package net.croxis.plugins.civilmineation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TechCommand implements CommandExecutor {

	private Civilmineation plugin;

	public TechCommand(Civilmineation civilmineation) {
		plugin = civilmineation;
	}

	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}

}
