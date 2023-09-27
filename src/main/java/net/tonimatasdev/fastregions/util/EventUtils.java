package net.tonimatasdev.fastregions.util;

import net.tonimatasdev.fastregions.api.flag.Flag;
import net.tonimatasdev.fastregions.api.region.Region;
import net.tonimatasdev.fastregions.api.region.RegionManager;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.vehicle.VehicleEvent;

public class EventUtils {

    public static boolean isBlockEventCancelled(BlockEvent event, Flag flag) {
        Region region = RegionManager.getRegion(event.getBlock().getWorld(), event.getBlock().getLocation());
        return region != null && !region.hasFlag(flag);
    }

    public static boolean isVehicleEventCancelled(VehicleEvent event, Flag flag) {
        Region region = RegionManager.getRegion(event.getVehicle().getWorld(), event.getVehicle().getLocation());
        return region != null && !region.hasFlag(flag);
    }

    public static boolean isPlayerEventCancelled(PlayerEvent event, Flag flag) {
        Region region = RegionManager.getRegion(event.getPlayer().getWorld(), event.getPlayer().getLocation());
        return region != null && !region.hasFlag(flag);
    }
}
