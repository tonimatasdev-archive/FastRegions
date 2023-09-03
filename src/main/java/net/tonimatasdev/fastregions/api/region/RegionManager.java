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
        long time = System.currentTimeMillis(); // TODO: Delete this line in the future.
        Region result = null;

        for (Region region : regions.values()) {
            if (region.getWorld() != world) continue;
            if (region.isInRegion(location)) {
                result = region;
                break;
            }
        }

        System.out.println("Time to calculate: " + (System.currentTimeMillis() - time)); // TODO: Delete this line in the future.
        return result;
    }

    public static boolean exist(String name) {
        return regions.get(name) != null;
    }

    public static boolean deleteRegion(String name) {
        regions.remove(name);
        return new File(FastRegionsAPI.regionsFolder + "\\" + name + ".json").delete() || !regions.containsKey(name);
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
