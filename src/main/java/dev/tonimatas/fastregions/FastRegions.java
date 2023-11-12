package dev.tonimatas.fastregions;

import dev.tonimatas.fastregions.api.FastRegionsAPI;
import dev.tonimatas.fastregions.commands.FastRegionsCommand;
import dev.tonimatas.fastregions.commands.RegionCommand;
import dev.tonimatas.fastregions.events.*;
import dev.tonimatas.fastregions.inventory.InventoryManager;
import dev.tonimatas.fastregions.region.RegionManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class FastRegions extends JavaPlugin {
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;

        if (new File(FastRegionsAPI.regionsFolder).mkdirs()) {
            getLogger().info("Region folder has been created");
        }

        Objects.requireNonNull(getCommand("region")).setExecutor(new RegionCommand());
        Objects.requireNonNull(getCommand("fastregions")).setExecutor(new FastRegionsCommand());

        getServer().getPluginManager().registerEvents(new BlockEvents(), this);
        getServer().getPluginManager().registerEvents(new EnchantmentsEvents(), this);
        //getServer().getPluginManager().registerEvents(new EntityEvents(), this);
        //getServer().getPluginManager().registerEvents(new HangingEvents(), this);
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getServer().getPluginManager().registerEvents(new RaidEvents(), this);
        getServer().getPluginManager().registerEvents(new VehicleEvents(), this);
        getServer().getPluginManager().registerEvents(new WeatherEvents(), this);

        getServer().getPluginManager().registerEvents(new InventoryManager(), this);

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
