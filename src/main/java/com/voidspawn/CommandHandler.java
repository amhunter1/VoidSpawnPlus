package com.voidspawn;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {
    
    private final VoidSpawn plugin;
    
    public CommandHandler(VoidSpawn plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("voidspawn.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }
        
        if (args.length == 0) {
            sendHelp(sender);
            return true;
        }
        
        switch (args[0].toLowerCase()) {
            case "setspawn":
                return handleSetSpawn(sender);
            case "reload":
                return handleReload(sender);
            default:
                sendHelp(sender);
                return true;
        }
    }
    
    private boolean handleSetSpawn(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players!");
            return true;
        }
        
        Player player = (Player) sender;
        Location location = player.getLocation();
        String worldName = location.getWorld().getName();
        
        plugin.getSpawnManager().setSpawn(worldName, location);
        player.sendMessage(plugin.getConfigManager().getSpawnSetMessage(worldName));
        
        return true;
    }
    
    private boolean handleReload(CommandSender sender) {
        plugin.reload();
        sender.sendMessage(plugin.getConfigManager().getReloadMessage());
        return true;
    }
    
    private void sendHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "=== VoidSpawn Commands ===");
        sender.sendMessage(ChatColor.YELLOW + "/vs setspawn" + ChatColor.WHITE + " - Set spawn point for current world");
        sender.sendMessage(ChatColor.YELLOW + "/vs reload" + ChatColor.WHITE + " - Reload configuration");
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        
        if (args.length == 1) {
            if (sender.hasPermission("voidspawn.admin")) {
                completions.add("setspawn");
                completions.add("reload");
            }
        }
        
        return completions;
    }
}
