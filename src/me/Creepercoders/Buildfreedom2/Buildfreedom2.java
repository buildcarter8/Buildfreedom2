package me.Creepercoders.Buildfreedom2;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Buildfreedom2 extends JavaPlugin{
  public final Logger logger = Logger.getLogger("Minecraft");
	public static Buildfreedom2 plugin;
	public final String creators = (ChatColor.RED + "Version: 1.0 by Buildcarter8 & xXWilee999Xx");
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + pdfFile.getVersion() + " Has Been Disabled!");
	}	
	
    @Override
    public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled!");
    }	
    
    public boolean onCommand(CommandSender sender, Command cmd,  String commandLabel, String[] args){
        Player player = (Player) sender;
    	if(commandLabel.equalsIgnoreCase("buildfreedom")){
    		sender.sendMessage(buildfreedom2.creators)
    
        }
		return false;   
    }
    
}    
    
