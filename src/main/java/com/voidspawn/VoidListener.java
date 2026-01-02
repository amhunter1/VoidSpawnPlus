package com.voidspawn;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.UUID;

public class VoidListener implements Listener {
    
    private final VoidSpawn plugin;
    
    private final Map<UUID, Long> cooldowns;
    
    private final Map<UUID, Double> lastYPositions;
    
    public VoidListener(VoidSpawn plugin) {
        this.plugin = plugin;
        this.cooldowns = new ConcurrentHashMap<>();
        this.lastYPositions = new ConcurrentHashMap<>();
    }
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();
        
        if (to == null || from.getY() == to.getY()) {
            return;
        }
        
        Player player = event.getPlayer();
        UUID playerId = player.getUniqueId();
        
        if (player.hasPermission("voidspawn.bypass")) {
            return;
        }
        
        String worldName = player.getWorld().getName();
        
        if (!plugin.getConfigManager().isWorldEnabled(worldName)) {
            return;
        }
        
        int yLevel = plugin.getConfigManager().getYLevel(worldName);
        double currentY = to.getY();
        
        Double lastY = lastYPositions.get(playerId);
        if (lastY != null && lastY == currentY) {
            return;
        }
        lastYPositions.put(playerId, currentY);
        
        if (currentY >= yLevel) {
            return;
        }
        
        long currentTime = System.currentTimeMillis();
        Long lastTeleport = cooldowns.get(playerId);
        
        int cooldownSeconds = plugin.getConfigManager().getTeleportCooldown();
        if (lastTeleport != null && (currentTime - lastTeleport) < (cooldownSeconds * 1000L)) {
            return;
        }
        
        cooldowns.put(playerId, currentTime);
        
        teleportPlayer(player, worldName);
    }
    
    private void teleportPlayer(Player player, String worldName) {
        Location spawnLocation = plugin.getSpawnManager().getSpawn(worldName);
        boolean customSpawnSet = true;
        
        if (spawnLocation == null) {
            spawnLocation = player.getWorld().getSpawnLocation();
            customSpawnSet = false;
            player.sendMessage(plugin.getConfigManager().getSpawnNotSetMessage());
        }
        
        if (plugin.getConfigManager().isFadeEffectEnabled()) {
            applyFadeEffect(player, spawnLocation, customSpawnSet);
        } else {
            performTeleport(player, spawnLocation, customSpawnSet);
        }
    }
    
    private void applyFadeEffect(Player player, Location destination, boolean customSpawnSet) {
        player.sendTitle("", "", 10, 40, 10);

        AtomicInteger ticks = new AtomicInteger(0);

        player.getScheduler().runAtFixedRate(plugin, (task) -> {
            if (!player.isOnline()) {
                task.cancel();
                return;
            }

            int currentTick = ticks.incrementAndGet();

            if (currentTick == 1) {
                player.sendTitle(" ", " ", 0, 40, 0);
            } else if (currentTick == 20) {
                performTeleport(player, destination, customSpawnSet);
            } else if (currentTick == 40) {
                player.sendTitle("", "", 0, 0, 10);
                task.cancel();
            }
        }, null, 1L, 1L);
    }
    
    private void performTeleport(Player player, Location destination, boolean customSpawnSet) {
        player.teleportAsync(destination).thenAccept(success -> {
            if (success) {
                if (plugin.getConfigManager().isPreventFallDamage()) {
                    player.setFallDistance(0);
                }

                player.sendMessage(plugin.getConfigManager().getTeleportMessage());

                Sound sound = plugin.getConfigManager().getTeleportSound();
                if (sound != null) {
                    player.playSound(
                            player.getLocation(),
                            sound,
                            plugin.getConfigManager().getSoundVolume(),
                            plugin.getConfigManager().getSoundPitch()
                    );
                }
            }
        });
    }
    
    public void cleanup(UUID playerId) {
        cooldowns.remove(playerId);
        lastYPositions.remove(playerId);
    }
}
