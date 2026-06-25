package com.davud.elytraandarmor.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChangeEffectCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Bu komut sadece oyuncular icindir.");
            return true;
        }

        openEffectMenu(player);
        return true;
    }

    private void openEffectMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 36, ChatColor.DARK_PURPLE + "Elytra Efekt Seçimi");

        inv.setItem(0, createGuiItem(Material.BLAZE_POWDER, "Ateş Efekti"));
        inv.setItem(1, createGuiItem(Material.SOUL_LANTERN, "Ruh Ateşi Efekti"));
        inv.setItem(2, createGuiItem(Material.DRAGON_BREATH, "Ejderha Nefesi Efekti"));
        inv.setItem(3, createGuiItem(Material.END_ROD, "End Işıltısı Efekti"));
        inv.setItem(4, createGuiItem(Material.MAGMA_CREAM, "Lav Kıvılcımı Efekti"));
        inv.setItem(5, createGuiItem(Material.RED_TULIP, "Kalp Efekti"));
        inv.setItem(6, createGuiItem(Material.ENCHANTING_TABLE, "Büyü Efekti"));
        inv.setItem(7, createGuiItem(Material.NOTE_BLOCK, "Müzik Efekti"));
        inv.setItem(8, createGuiItem(Material.CAMPFIRE, "Kamp Ateşi Dumanı Efekti"));
        inv.setItem(9, createGuiItem(Material.TOTEM_OF_UNDYING, "Totem Gücü Efekti"));
        inv.setItem(10, createGuiItem(Material.NAUTILUS_SHELL, "Nautilus Halesi Efekti"));
        inv.setItem(11, createGuiItem(Material.GLOW_INK_SAC, "Parlayan Mürekkep Efekti"));
        inv.setItem(12, createGuiItem(Material.CHERRY_LEAVES, "Kiraz Yaprakları Efekti"));
        inv.setItem(13, createGuiItem(Material.SNOWBALL, "Kar Tanesi Efekti"));
        inv.setItem(14, createGuiItem(Material.WATER_BUCKET, "Su Damlaları Efekti"));
        inv.setItem(15, createGuiItem(Material.SLIME_BALL, "Slime Efekti"));
        inv.setItem(16, createGuiItem(Material.EMERALD, "Mutlu Köylü Efekti"));
        inv.setItem(17, createGuiItem(Material.FIRE_CHARGE, "Öfkeli Köylü Efekti"));
        inv.setItem(18, createGuiItem(Material.COBWEB, "Bulut Efekti"));
        inv.setItem(19, createGuiItem(Material.GOLDEN_SWORD, "Büyülü Kritik Efekti"));
        inv.setItem(20, createGuiItem(Material.OBSIDIAN, "Portal Efekti"));
        inv.setItem(21, createGuiItem(Material.SCULK, "Sculk Ruhu Efekti"));
        inv.setItem(22, createGuiItem(Material.BASALT, "Kül Efekti"));
        inv.setItem(23, createGuiItem(Material.CRIMSON_FUNGUS, "Kızıl Spor Efekti"));
        inv.setItem(24, createGuiItem(Material.WARPED_FUNGUS, "Çarpık Spor Efekti"));
        inv.setItem(25, createGuiItem(Material.ECHO_SHARD, "Sonik Patlama Efekti"));
        
        inv.setItem(31, createGuiItem(Material.BARRIER, "Efekti Kapat"));

        player.openInventory(inv);
    }

    private ItemStack createGuiItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.AQUA + name);
            item.setItemMeta(meta);
        }
        return item;
    }
}
