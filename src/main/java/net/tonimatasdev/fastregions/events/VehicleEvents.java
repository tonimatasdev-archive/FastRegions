package net.tonimatasdev.fastregions.events;

import net.tonimatasdev.fastregions.api.flag.Flag;
import net.tonimatasdev.fastregions.api.region.Region;
import net.tonimatasdev.fastregions.api.region.RegionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.*;

public class VehicleEvents implements Listener {
    @EventHandler
    private void onCreate(VehicleCreateEvent event) {
        event.setCancelled(isCancelled(event, Flag.vehicle_create));
    }

    @EventHandler
    private void onDamage(VehicleDamageEvent event) {
        event.setCancelled(isCancelled(event, Flag.vehicle_damage));
    }

    @EventHandler
    private void onDestroy(VehicleDestroyEvent event) {
        event.setCancelled(isCancelled(event, Flag.vehicle_destroy));
    }

    @EventHandler
    private void onEnter(VehicleEnterEvent event) {
        event.setCancelled(isCancelled(event, Flag.vehicle_enter));
    }

    public boolean isCancelled(VehicleEvent event, Flag flag) {
        Region region = RegionManager.getRegion(event.getVehicle().getWorld(), event.getVehicle().getLocation());
        return region != null && region.hasFlag(flag);
    }
}
