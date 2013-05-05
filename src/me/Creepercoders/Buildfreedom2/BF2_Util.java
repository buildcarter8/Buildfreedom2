//Workin' on this.
package me.Creepercoders.Buildfreedom2;

public class BF2_Util
{
   private static final Logger log = Logger.getLogger("Minecraft");
   
       public static boolean isUserSuperadmin(CommandSender user, TotalFreedomMod tfm)
    {
        try
        {
            if (!(user instanceof Player))
            {
                return true;
            }

            if (Bukkit.getOnlineMode())
            {
                if (TotalFreedomMod.superadmins.contains(user.getName().toLowerCase()))
                {
                    return true;
                }
            }

            Player p = (Player) user;
            if (p != null)
            {
                InetSocketAddress ip_address_obj = p.getAddress();
                if (ip_address_obj != null)
                {
                    String user_ip = ip_address_obj.getAddress().toString().replaceAll("/", "").trim();
                    if (user_ip != null && !user_ip.isEmpty())
                    {
                        if (TotalFreedomMod.superadmin_ips.contains(user_ip))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        catch (Exception ex)
        {
            log.severe("Exception in TFM_Util.isUserSuperadmin: " + ex.getMessage());
        }

        return false;
    }

    public static boolean checkPartialSuperadminIP(String user_ip, TotalFreedomMod tfm)
    {
        user_ip = user_ip.trim();

        if (TotalFreedomMod.superadmin_ips.contains(user_ip))
        {
            return true;
        }
        else
        {
            String[] user_octets = user_ip.split("\\.");
            if (user_octets.length != 4)
            {
                return false;
            }

            String match_ip = null;
            for (String test_ip : TotalFreedomMod.superadmin_ips)
            {
                String[] test_octets = test_ip.split("\\.");
                if (test_octets.length == 4)
                {
                    if (user_octets[0].equals(test_octets[0]) && user_octets[1].equals(test_octets[1]) && user_octets[2].equals(test_octets[2]))
                    {
                        match_ip = test_ip;
                        break;
                    }
                }
            }

            if (match_ip != null)
            {
                TotalFreedomMod.superadmin_ips.add(user_ip);

                FileConfiguration config = YamlConfiguration.loadConfiguration(new File(tfm.getDataFolder(), TotalFreedomMod.SUPERADMIN_FILE));

                fileloop:
                for (String user : config.getKeys(false))
                {
                    List<String> user_ips = (List<String>) config.getStringList(user);
                    for (String ip : user_ips)
                    {
                        ip = ip.toLowerCase().trim();
                        if (ip.equals(match_ip))
                        {
                            log.info("New IP '" + user_ip + "' matches old IP '" + match_ip + "' via partial match, adding it to superadmin list.");
                            user_ips.add(user_ip);
                            config.set(user, user_ips);
                            break fileloop;
                        }
                    }
                }

                try
                {
                    config.save(new File(tfm.getDataFolder(), TotalFreedomMod.SUPERADMIN_FILE));
                }
                catch (IOException ex)
                {
                    log.log(Level.SEVERE, null, ex);
                }
            }

            return match_ip != null;
        }
    }
    
        public static boolean deleteFolder(File file)
    {
        if (file.exists())
        {
            if (file.isDirectory())
            {
                for (File f : file.listFiles())
                {
                    if (!TFM_Util.deleteFolder(f))
                    {
                        return false;
                    }
                }
            }
            file.delete();
            return !file.exists();
        }
        else
        {
            return false;
        }
    }
