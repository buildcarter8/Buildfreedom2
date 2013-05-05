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
    
    public static final String SUPERADMIN_FILE = "superadmin.yml";

    public void onEnable()
    {
    	loadSuperadminConfig();
    	
        log.info("[Buildfreedom2] - Enabled! - v1.1 by buildcarter8 and xXWilee999Xx");
        
        BF2_Util.deleteFolder(new File("./_deleteme"));
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
    
    public void loadSuperadminConfig()
    {
        BF2_Util.createDefaultConfiguration(SUPERADMIN_FILE, this, getFile());
        FileConfiguration config = YamlConfiguration.loadConfiguration(new File(getDataFolder(), SUPERADMIN_FILE));

        superadmins = new ArrayList<String>();
        superadmin_ips = new ArrayList<String>();

        for (String user : config.getKeys(false))
        {
            superadmins.add(user.toLowerCase().trim());

            List<String> user_ips = (List<String>) config.getStringList(user);
            for (String ip : user_ips)
            {
                ip = ip.toLowerCase().trim();
                if (!superadmin_ips.contains(ip))
                {
                    superadmin_ips.add(ip);
                }
            }
        }
    }

