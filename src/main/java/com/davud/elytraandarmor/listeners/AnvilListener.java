package com.davud.elytraandarmor.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import com.davud.elytraandarmor.ElytraAndArmor;

import java.util.ArrayList;
import java.util.List;

public class AnvilListener implements Listener {

    private static final NamespacedKey ELYTRA_KEY = new NamespacedKey("elytraandarmor", "is_elytra");

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack slot1 = event.getInventory().getItem(0);
        ItemStack slot2 = event.getInventory().getItem(1);

        if (slot1 == null || slot2 == null) return;

        // Check if slot 1 is a chestplate
        if (isChestplate(slot1.getType())) {
            // Check if slot 2 is Elytra
            if (slot2.getType() == Material.ELYTRA) {

                // Do not allow if it already has the elytra effect
                if (isSpecialElytra(slot1)) return;

                ItemStack result = slot1.clone();
                ItemMeta meta = result.getItemMeta();
                if (meta != null) {
                    // Add PDC tag
                    meta.getPersistentDataContainer().set(ELYTRA_KEY, PersistentDataType.BYTE, (byte) 1);

                    // Add Lore
                    List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
                    lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "Elytra I");
                    meta.setLore(lore);

                    // Add Glider component for 1.21+
                    try {
                        meta.setGlider(true);
                    } catch (NoSuchMethodError ignored) {
                        // In case the server runs an older subversion
                    }

                    result.setItemMeta(meta);

                    event.setResult(result);
                    
                    Bukkit.getScheduler().runTask(ElytraAndArmor.getInstance(), () -> {
                        event.getInventory().setRepairCost(5); // Costs 5 levels
                    });
                }
            }
        }
    }

    private boolean isChestplate(Material mat) {
        String name = mat.name();
        return name.endsWith("_CHESTPLATE");
    }

    public static boolean isSpecialElytra(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        return item.getItemMeta().getPersistentDataContainer().has(ELYTRA_KEY, PersistentDataType.BYTE);
    }
}
