package me.Creepercoders.Buildfreedom2.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_buildfreedom2 extends BF2_Command
{
  @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
		sender.sendMessage(ChatColor.AQUA + "Buildfreedom2 1.0 by buildcarter8");
		return false;
        
    }
}
