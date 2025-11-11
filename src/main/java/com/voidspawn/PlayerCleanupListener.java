package com.voidspawn;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerCleanupListener implements Listener {
    
    private final VoidListener voidListener;
    
    public PlayerCleanupListener(VoidListener voidListener) {
        this.voidListener = voidListener;
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        voidListener.cleanup(event.getPlayer().getUniqueId());
    }
}
