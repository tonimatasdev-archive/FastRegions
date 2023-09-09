package net.tonimatasdev.fastregions.api.region;

import net.tonimatasdev.fastregions.api.FastRegionsAPI;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RegionManager {
    public static Map<String, Region> regions = new HashMap<>();

    public static Region getRegion(World world, Location location) {
        Region result = null;

        for (Region region : regions.values()) {
            if (region.getWorld() != world) continue;
            if (region.isInRegion(location)) {
                result = region;
                break;
            }
        }

        return result;
    }

    public static boolean exist(String name) {
        return regions.get(name) != null;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void deleteRegion(String name) {
        regions.remove(name);
        new File(FastRegionsAPI.regionsFolder + "\\" + name + ".json").delete();
    }

    public static void save() {
        for (Region region : regions.values()) {
            region.save();
        }
    }

    public static void load() {
        File[] files = new File(FastRegionsAPI.regionsFolder).listFiles();
        if (files == null) return;

        for (File file : files) {
            Region.load(file);
        }
    }
}
