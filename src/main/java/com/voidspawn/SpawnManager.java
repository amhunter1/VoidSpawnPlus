package com.voidspawn;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpawnManager {
    
    private final VoidSpawn plugin;
    private final File spawnFile;
    private FileConfiguration spawnConfig;
    
    private final Map<String, SpawnData> spawnLocations;
    
    public SpawnManager(VoidSpawn plugin) {
        this.plugin = plugin;
        this.spawnFile = new File(plugin.getDataFolder(), "spawns.yml");
        this.spawnLocations = new HashMap<>();
    }
    
    public void loadSpawns() {
        spawnLocations.clear();
        
        if (!spawnFile.exists()) {
            plugin.getLogger().info("No spawn data found. Creating new file.");
            return;
        }
        
        spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);
        
        for (String worldName : spawnConfig.getKeys(false)) {
            double x = spawnConfig.getDouble(worldName + ".x");
            double y = spawnConfig.getDouble(worldName + ".y");
            double z = spawnConfig.getDouble(worldName + ".z");
            float yaw = (float) spawnConfig.getDouble(worldName + ".yaw");
            float pitch = (float) spawnConfig.getDouble(worldName + ".pitch");
            
            spawnLocations.put(worldName, new SpawnData(x, y, z, yaw, pitch));
        }
        
        plugin.getLogger().info("Loaded " + spawnLocations.size() + " spawn location(s).");
    }
    
    public void saveSpawns() {
        spawnConfig = new YamlConfiguration();
        
        for (Map.Entry<String, SpawnData> entry : spawnLocations.entrySet()) {
            String worldName = entry.getKey();
            SpawnData data = entry.getValue();
            
            spawnConfig.set(worldName + ".x", data.x);
            spawnConfig.set(worldName + ".y", data.y);
            spawnConfig.set(worldName + ".z", data.z);
            spawnConfig.set(worldName + ".yaw", data.yaw);
            spawnConfig.set(worldName + ".pitch", data.pitch);
        }
        
        try {
            spawnConfig.save(spawnFile);
            plugin.getLogger().info("Spawn locations saved successfully.");
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to save spawn locations: " + e.getMessage());
        }
    }
    
    public void setSpawn(String worldName, Location location) {
        SpawnData data = new SpawnData(
            location.getX(),
            location.getY(),
            location.getZ(),
            location.getYaw(),
            location.getPitch()
        );
        spawnLocations.put(worldName, data);
        saveSpawns();
    }
    
    public Location getSpawn(String worldName) {
        SpawnData data = spawnLocations.get(worldName);
        if (data == null) {
            return null;
        }
        
        org.bukkit.World world = plugin.getServer().getWorld(worldName);
        if (world == null) {
            return null;
        }
        
        return new Location(world, data.x, data.y, data.z, data.yaw, data.pitch);
    }
    
    public boolean hasSpawn(String worldName) {
        return spawnLocations.containsKey(worldName);
    }
    
    private static class SpawnData {
        private final double x, y, z;
        private final float yaw, pitch;
        
        public SpawnData(double x, double y, double z, float yaw, float pitch) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
        }
    }
}
