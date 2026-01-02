package com.voidspawn;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConfigManager {
    
    private final VoidSpawn plugin;
    private FileConfiguration config;
    
    private boolean fadeEffectEnabled;
    private boolean preventFallDamage;
    private Sound teleportSound;
    private float soundVolume;
    private float soundPitch;
    private int teleportCooldown;
    private String teleportMessage;
    private String spawnNotSetMessage;
    private String spawnSetMessage;
    private String cooldownMessage;
    private String reloadMessage;
    private Map<String, WorldConfig> worldConfigs;
    
    public ConfigManager(VoidSpawn plugin) {
        this.plugin = plugin;
        this.worldConfigs = new HashMap<>();
    }
    
    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        config = plugin.getConfig();
        
        fadeEffectEnabled = config.getBoolean("fade-effect-enabled", true);
        preventFallDamage = config.getBoolean("prevent-fall-damage", true);
        
        String soundName = config.getString("teleport-sound", "ENTITY_ENDERMAN_TELEPORT");
        try {
            teleportSound = soundName.equalsIgnoreCase("none") ? null : Sound.valueOf(soundName);
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Invalid sound: " + soundName + ". Using default.");
            teleportSound = Sound.ENTITY_ENDERMAN_TELEPORT;
        }
        
        soundVolume = (float) config.getDouble("sound-volume", 1.0);
        soundPitch = (float) config.getDouble("sound-pitch", 1.0);
        teleportCooldown = config.getInt("teleport-cooldown", 3);
        
        teleportMessage = colorize(config.getString("teleport-message", "&6[VoidSpawn] &eYou have been teleported to safety!"));
        spawnNotSetMessage = colorize(config.getString("spawn-not-set-message", "&6[VoidSpawn] &cWarning: Custom spawn not set for this world!"));
        spawnSetMessage = colorize(config.getString("spawn-set-message", "&6[VoidSpawn] &aSpawn location set successfully!"));
        cooldownMessage = colorize(config.getString("cooldown-message", "&6[VoidSpawn] &cPlease wait before teleporting again!"));
        reloadMessage = colorize(config.getString("reload-message", "&6[VoidSpawn] &aConfiguration reloaded successfully!"));
        
        worldConfigs.clear();
        ConfigurationSection worldsSection = config.getConfigurationSection("worlds");
        if (worldsSection != null) {
            Set<String> worlds = worldsSection.getKeys(false);
            for (String worldName : worlds) {
                int yLevel = worldsSection.getInt(worldName + ".y-level", -64);
                boolean enabled = worldsSection.getBoolean(worldName + ".enabled", true);
                worldConfigs.put(worldName, new WorldConfig(yLevel, enabled));
            }
        }
        
        plugin.getLogger().info("Configuration loaded successfully! Monitoring " + worldConfigs.size() + " world(s).");
    }
    
    private String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public boolean isFadeEffectEnabled() {
        return fadeEffectEnabled;
    }

    public boolean isPreventFallDamage() {
        return preventFallDamage;
    }
    
    public Sound getTeleportSound() {
        return teleportSound;
    }
    
    public float getSoundVolume() {
        return soundVolume;
    }
    
    public float getSoundPitch() {
        return soundPitch;
    }
    
    public int getTeleportCooldown() {
        return teleportCooldown;
    }
    
    public String getTeleportMessage() {
        return teleportMessage;
    }
    
    public String getSpawnNotSetMessage() {
        return spawnNotSetMessage;
    }
    
    public String getSpawnSetMessage(String worldName) {
        return spawnSetMessage.replace("{world}", worldName);
    }
    
    public String getCooldownMessage() {
        return cooldownMessage;
    }
    
    public String getReloadMessage() {
        return reloadMessage;
    }
    
    public WorldConfig getWorldConfig(String worldName) {
        return worldConfigs.get(worldName);
    }
    
    public boolean isWorldEnabled(String worldName) {
        WorldConfig wc = worldConfigs.get(worldName);
        return wc != null && wc.enabled;
    }
    
    public int getYLevel(String worldName) {
        WorldConfig wc = worldConfigs.get(worldName);
        return wc != null ? wc.yLevel : -64;
    }
    
    public static class WorldConfig {
        private final int yLevel;
        private final boolean enabled;
        
        public WorldConfig(int yLevel, boolean enabled) {
            this.yLevel = yLevel;
            this.enabled = enabled;
        }
        
        public int getYLevel() {
            return yLevel;
        }
        
        public boolean isEnabled() {
            return enabled;
        }
    }
}
