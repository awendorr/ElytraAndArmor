package com.davud.elytraandarmor.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class DurabilityListener implements Listener {

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) {
        ItemStack item = event.getItem();
        
        if (AnvilListener.isSpecialElytra(item)) {
            Player player = event.getPlayer();

            // Sadece uçarken dayanıklılık kaybını engelle
            if (player.isGliding()) {
                event.setCancelled(true);
            }
        }
    }
}
