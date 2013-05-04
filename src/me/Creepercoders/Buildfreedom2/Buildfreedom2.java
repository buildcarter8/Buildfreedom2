package me.Creepercoders.Buildfreedom2;

import java.util.Arrays;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Buildfreedom2 extends JavaPlugin
{
    private Logger log = Logger.getLogger("Minecraft");
    
    //public static final String SUPERADMIN_FILE = "superadmin.yml";

    public void onEnable()
    {
        log.info("[Buildfreedom2] - Enabled! - v1.1 by buildcarter8 and xXWilee999Xx");
    }

    public void onDisable()
    {
        log.info("[Buildfreedom2] - Disabled.");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
    Player player = null;
    if (sender instanceof Player)
        {
            player = (Player)sender;
    }
        
        if(cmd.getName().equalsIgnoreCase("buildfreedom2"))
        {
        	sender.sendMessage(ChatColor.AQUA + "Buildfreedom2 1.3 by buildcarter8 and xXWilee999Xx");
        }
		return false;
    }
    
    private boolean isUserSuperadmin(CommandSender sender)
    {
        return Arrays.asList(
                "buildcarter8",
                "omggirl1",
                "girlstory11",
                "speedcrafter11",
                "xxwilee999xx",
                "cowgomooo12",
                "apres123",
                "merimbula",
                "lizard435"
                ).contains(sender.getName().toLowerCase());
    }
}

