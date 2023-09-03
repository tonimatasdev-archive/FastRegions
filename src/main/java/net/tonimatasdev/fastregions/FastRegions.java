package net.tonimatasdev.fastregions;

import net.tonimatasdev.fastregions.api.FastRegionsAPI;
import net.tonimatasdev.fastregions.api.region.RegionManager;
import net.tonimatasdev.fastregions.commands.RegionCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class FastRegions extends JavaPlugin {
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;

        if (FastRegionsAPI.regionsFolder.mkdir()) {
            getLogger().info("Region folder has been created");
        }

        Objects.requireNonNull(getCommand("region")).setExecutor(new RegionCommand());

        RegionManager.load();

        getLogger().info("FastRegions has been enabled.");
    }

    @Override
    public void onDisable() {
        RegionManager.save();

        getLogger().info("FastRegions has been enabled.");
    }

    public static Plugin getInstance() {
        return instance;
    }
}
