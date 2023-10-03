package net.tonimatasdev.fastregions.util;

import net.tonimatasdev.fastregions.flag.Flag;
import net.tonimatasdev.fastregions.region.Region;
import net.tonimatasdev.fastregions.region.RegionManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.vehicle.VehicleEvent;

public class EventUtils {
    public static boolean isRegionNotContainFlag(Player player, Flag flag, World world, Location location) {
        Region region = RegionManager.getRegion(world, location);

        if (region == null) return false;
        if (player == null) return !region.hasFlag(flag);

        // Region bypass permissions.
        return !region.hasFlag(flag);
    }

    public static boolean isBlockEventCancelled(BlockEvent event, Player player, Flag flag) {
        return isRegionNotContainFlag(player, flag, event.getBlock().getWorld(), event.getBlock().getLocation());
    }

    public static boolean isVehicleEventCancelled(VehicleEvent event, Flag flag) {
        return isRegionNotContainFlag(null, flag, event.getVehicle().getWorld(), event.getVehicle().getLocation());
    }

    public static boolean isPlayerEventCancelled(PlayerEvent event, Flag flag) {
        Region region = RegionManager.getRegion(event.getPlayer().getWorld(), event.getPlayer().getLocation());
        return region != null && !region.hasFlag(flag);
    }
}
