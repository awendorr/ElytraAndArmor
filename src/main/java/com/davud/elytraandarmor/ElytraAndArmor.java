package com.davud.elytraandarmor;

import com.davud.elytraandarmor.commands.ChangeEffectCommand;
import com.davud.elytraandarmor.listeners.AnvilListener;
import com.davud.elytraandarmor.listeners.DurabilityListener;
import com.davud.elytraandarmor.listeners.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ElytraAndArmor extends JavaPlugin {

    private static ElytraAndArmor instance;
    private final Map<UUID, Particle> activeEffects = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        loadEffects();

        getServer().getPluginManager().registerEvents(new AnvilListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new DurabilityListener(), this);
        
        getCommand("changeeffect").setExecutor(new ChangeEffectCommand());

        startParticleTask();
        
        getLogger().info("ElytraAndArmor plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        saveEffects();
        getLogger().info("ElytraAndArmor plugin has been disabled!");
    }

    public static ElytraAndArmor getInstance() {
        return instance;
    }

    public void setPlayerEffect(UUID uuid, Particle particle) {
        if (particle == null) {
            activeEffects.remove(uuid);
            getConfig().set("players." + uuid.toString(), "NONE");
        } else {
            activeEffects.put(uuid, particle);
            getConfig().set("players." + uuid.toString(), particle.name());
        }
        saveConfig();
    }

    public Particle getPlayerEffect(UUID uuid) {
        return activeEffects.get(uuid);
    }

    private void loadEffects() {
        FileConfiguration config = getConfig();
        if (config.contains("players")) {
            for (String uuidStr : config.getConfigurationSection("players").getKeys(false)) {
                String particleName = config.getString("players." + uuidStr);
                if (particleName != null && !particleName.equals("NONE")) {
                    try {
                        Particle particle = Particle.valueOf(particleName);
                        activeEffects.put(UUID.fromString(uuidStr), particle);
                    } catch (IllegalArgumentException e) {
                        getLogger().warning("Invalid particle name in config: " + particleName);
                    }
                }
            }
        }
    }

    private void saveEffects() {
        saveConfig();
    }

    private void startParticleTask() {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.isGliding()) {
                    ItemStack chestplate = player.getInventory().getChestplate();
                    if (AnvilListener.isSpecialElytra(chestplate)) {
                        Particle effect = activeEffects.get(player.getUniqueId());
                        if (effect != null) {
                            // Spawn particle behind the player
                            player.getWorld().spawnParticle(effect, player.getLocation().clone().add(0, 1, 0), 2, 0.2, 0.2, 0.2, 0.05);
                        }
                    }
                }
            }
        }, 0L, 2L); // every 2 ticks (10 times a second)
    }
}
