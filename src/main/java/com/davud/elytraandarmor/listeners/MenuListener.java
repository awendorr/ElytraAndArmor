package com.davud.elytraandarmor.listeners;

import com.davud.elytraandarmor.ElytraAndArmor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        String title = ChatColor.stripColor(event.getView().getTitle());
        
        if (title.contains("Elytra Efekt Seçimi")) {
            event.setCancelled(true);
            
            if (!(event.getWhoClicked() instanceof Player player)) return;
            
            if (event.getCurrentItem() == null) return;
            
            Material type = event.getCurrentItem().getType();
            Particle particle = null;
            String msg = "";

            switch (type) {
                case BLAZE_POWDER: particle = Particle.FLAME; msg = "Ateş Efekti seçildi!"; break;
                case SOUL_LANTERN: particle = Particle.SOUL_FIRE_FLAME; msg = "Ruh Ateşi Efekti seçildi!"; break;
                case DRAGON_BREATH: particle = Particle.DRAGON_BREATH; msg = "Ejderha Nefesi Efekti seçildi!"; break;
                case END_ROD: particle = Particle.END_ROD; msg = "End Işıltısı Efekti seçildi!"; break;
                case MAGMA_CREAM: particle = Particle.LAVA; msg = "Lav Kıvılcımı Efekti seçildi!"; break;
                case RED_TULIP: particle = Particle.HEART; msg = "Kalp Efekti seçildi!"; break;
                case ENCHANTING_TABLE: particle = Particle.ENCHANT; msg = "Büyü Efekti seçildi!"; break;
                case NOTE_BLOCK: particle = Particle.NOTE; msg = "Müzik Efekti seçildi!"; break;
                case CAMPFIRE: particle = Particle.CAMPFIRE_COSY_SMOKE; msg = "Kamp Ateşi Dumanı Efekti seçildi!"; break;
                case TOTEM_OF_UNDYING: particle = Particle.TOTEM_OF_UNDYING; msg = "Totem Gücü Efekti seçildi!"; break;
                case NAUTILUS_SHELL: particle = Particle.NAUTILUS; msg = "Nautilus Halesi Efekti seçildi!"; break;
                case GLOW_INK_SAC: particle = Particle.GLOW_SQUID_INK; msg = "Parlayan Mürekkep Efekti seçildi!"; break;
                case CHERRY_LEAVES: particle = Particle.CHERRY_LEAVES; msg = "Kiraz Yaprakları Efekti seçildi!"; break;
                case SNOWBALL: particle = Particle.SNOWFLAKE; msg = "Kar Tanesi Efekti seçildi!"; break;
                case WATER_BUCKET: particle = Particle.SPLASH; msg = "Su Damlaları Efekti seçildi!"; break;
                case SLIME_BALL: particle = Particle.SNEEZE; msg = "Slime Efekti seçildi!"; break;
                case EMERALD: particle = Particle.HAPPY_VILLAGER; msg = "Mutlu Köylü Efekti seçildi!"; break;
                case FIRE_CHARGE: particle = Particle.ANGRY_VILLAGER; msg = "Öfkeli Köylü Efekti seçildi!"; break;
                case COBWEB: particle = Particle.CLOUD; msg = "Bulut Efekti seçildi!"; break;
                case GOLDEN_SWORD: particle = Particle.ENCHANTED_HIT; msg = "Büyülü Kritik Efekti seçildi!"; break;
                case OBSIDIAN: particle = Particle.PORTAL; msg = "Portal Efekti seçildi!"; break;
                case SCULK: particle = Particle.SCULK_SOUL; msg = "Sculk Ruhu Efekti seçildi!"; break;
                case BASALT: particle = Particle.ASH; msg = "Kül Efekti seçildi!"; break;
                case CRIMSON_FUNGUS: particle = Particle.CRIMSON_SPORE; msg = "Kızıl Spor Efekti seçildi!"; break;
                case WARPED_FUNGUS: particle = Particle.WARPED_SPORE; msg = "Çarpık Spor Efekti seçildi!"; break;
                case ECHO_SHARD: particle = Particle.SONIC_BOOM; msg = "Sonik Patlama Efekti seçildi!"; break;
                case BARRIER: particle = null; msg = "Efekt kapatıldı!"; break;
                default: return;
            }

            ElytraAndArmor.getInstance().setPlayerEffect(player.getUniqueId(), particle);
            player.sendMessage(ChatColor.GREEN + msg);
            player.closeInventory();
        }
    }
}
