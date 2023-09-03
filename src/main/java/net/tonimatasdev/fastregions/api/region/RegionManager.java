package net.tonimatasdev.fastregions.api.region;

import net.tonimatasdev.fastregions.FastRegions;
import net.tonimatasdev.fastregions.api.FastRegionsAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegionManager {
    public static Map<String, Region> regions = new HashMap<>();

    public static Region getRegion(World world, Location location) {
        long time = System.currentTimeMillis(); // TODO: Delete this line in the future.
        Region result = null;

        for (Region region : regions.values()) {
            if (region.world() != world) continue;
            if (region.isInRegion(location)) {
                result = region;
                break;
            }
        }

        System.out.println("Time to calculate: " + (System.currentTimeMillis() - time)); // TODO: Delete this line in the future.
        return result;
    }

    public static void createRegion(String name, Player player) {
        Region region = new Region(name, player.getWorld(), FastRegionsAPI.pos1.get(player), FastRegionsAPI.pos2.get(player), new ArrayList<>());

        region.save();
        regions.put(name, region);
        FastRegionsAPI.pos1.remove(player);
        FastRegionsAPI.pos2.remove(player);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void deleteRegion(String name) {
        regions.remove(name);
        new File(FastRegionsAPI.regionsFolder.getPath() + "\\" + name + ".json").delete();
    }

    public static void save() {
        for (Region region : regions.values()) {
            region.save();
        }
    }

    public static void load() {
        File[] files = FastRegionsAPI.regionsFolder.listFiles();

        if (files == null) return;

        for (File file : files) {
            try (FileReader reader = new FileReader(file)) {
                Region region = FastRegionsAPI.gson.fromJson(reader, Region.class);
                regions.put(region.name(), region);
            } catch (IOException e) {
                FastRegions.getInstance().getLogger().severe("Error on load region " + file.getName().replaceAll(".json", ""));
            }
        }
    }
}
