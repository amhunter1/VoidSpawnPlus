package com.voidspawn;

import org.bukkit.plugin.java.JavaPlugin;
import org.bstats.bukkit.Metrics;
import java.util.logging.Level;

public class VoidSpawn extends JavaPlugin {
    
    private static VoidSpawn instance;
    private ConfigManager configManager;
    private SpawnManager spawnManager;
    
    @Override
    public void onEnable() {
        instance = this;
        
        this.configManager = new ConfigManager(this);
        this.spawnManager = new SpawnManager(this);
        
        configManager.loadConfig();
        
        spawnManager.loadSpawns();

        int pluginId = 28656;
        new Metrics(this, pluginId);
        
        CommandHandler commandHandler = new CommandHandler(this);
        getCommand("vs").setExecutor(commandHandler);
        
        VoidListener voidListener = new VoidListener(this);
        getServer().getPluginManager().registerEvents(voidListener, this);
        getServer().getPluginManager().registerEvents(new PlayerCleanupListener(voidListener), this);
        
        getLogger().info("VoidSpawn has been enabled successfully!");
    }
    
    @Override
    public void onDisable() {
        if (spawnManager != null) {
            spawnManager.saveSpawns();
        }
        
        getLogger().info("VoidSpawn has been disabled successfully!");
    }
    
    public static VoidSpawn getInstance() {
        return instance;
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }
    
    public SpawnManager getSpawnManager() {
        return spawnManager;
    }
    
    public void reload() {
        configManager.loadConfig();
        spawnManager.loadSpawns();
    }
}
