package me.Creepercoders.Buildfreedom2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.Creepercoders.Buildfreedom2.Commands.BF2_Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Buildfreedom2 extends JavaPlugin
{
    private Logger log = Logger.getLogger("Minecraft-Server");
    
    public static final String SUPERADMIN_FILE = "superadmin.yml";
    public static final String CONFIG_FILE = "config.yml"
    public static final String COMMAND_PATH = "me.Creepercoders.Buildfreedom2.Commands";
    public static final String COMMAND_PREFIX = "Command_";

    BF2_Util.enablePlugin();

    public void onDisable()
    {
        log.info("[Buildfreedom2] - Enabled V.1.0 by buildcarter8");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        try
        {
            Player sender_p = null;
            boolean senderIsConsole = false;
            if (sender instanceof Player)
            {
                sender_p = (Player) sender;
                log.info(String.format("[PLAYER_COMMAND] %s(%s): /%s %s",
                        sender_p.getName(),
                        ChatColor.stripColor(sender_p.getDisplayName()),
                        commandLabel,
                        BF2_Util.implodeStringList(" ", Arrays.asList(args))));
            }
            else
            {
                senderIsConsole = true;
                log.info(String.format("[CONSOLE_COMMAND] %s: /%s %s",
                        sender.getName(),
                        commandLabel,
                        BF2_Util.implodeStringList(" ", Arrays.asList(args))));
            }

            BF2_Command dispatcher;
            try
            {
                ClassLoader classLoader = Buildfreedom2.class.getClassLoader();
                dispatcher = (BF2_Command) classLoader.loadClass(String.format("%s.%s%s", COMMAND_PATH, COMMAND_PREFIX, cmd.getName().toLowerCase())).newInstance();
                dispatcher.setPlugin(this);
            }
            catch (Throwable ex)
            {
                log.log(Level.SEVERE, "[" + getDescription().getName() + "] Command not loaded: " + cmd.getName(), ex);
                sender.sendMessage(ChatColor.RED + "Command Error: Command not loaded: " + cmd.getName());
                return true;
            }

            try
            {
                return dispatcher.run(sender, sender_p, cmd, commandLabel, args, senderIsConsole);
            }
            catch (Throwable ex)
            {
                sender.sendMessage(ChatColor.RED + "Command Error: " + ex.getMessage());
            }

            dispatcher = null;
        }
        catch (Throwable ex)
        {
            log.log(Level.SEVERE, "[" + getDescription().getName() + "] Command Error: " + commandLabel, ex);
            sender.sendMessage(ChatColor.RED + "Unknown Command Error.");
        }

        return true;
    }

    public void loadMainConfig()
    {

    }
    
    public static List<String> superadmins = new ArrayList<String>();
    public static List<String> superadmin_ips = new ArrayList<String>();
    
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
}
