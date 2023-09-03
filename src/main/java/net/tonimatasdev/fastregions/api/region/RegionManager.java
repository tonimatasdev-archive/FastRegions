package net.tonimatasdev.fastregions.api.region;

import net.tonimatasdev.fastregions.api.FastRegionsAPI;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

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

        RegionManager.regions.put(name, region);
        FastRegionsAPI.pos1.remove(player);
        FastRegionsAPI.pos2.remove(player);
    }

    public static void deleteRegion(String name) {
        RegionManager.regions.remove(name);
        // TODO: Delete the region files.
    }

    public static void save() {
        // TODO: Create the logic.
    }

    public static void load() {
        // TODO: Create the logic.
    }
}
