package net.tonimatasdev.fastregions.events;

import net.tonimatasdev.fastregions.api.flag.Flag;
import net.tonimatasdev.fastregions.util.EventUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;

public class VehicleEvents implements Listener {
    @EventHandler
    private void onCreate(VehicleCreateEvent event) {
        event.setCancelled(EventUtils.isVehicleEventCancelled(event, Flag.vehicle_create));
    }

    @EventHandler
    private void onDamage(VehicleDamageEvent event) {
        event.setCancelled(EventUtils.isVehicleEventCancelled(event, Flag.vehicle_damage));
    }

    @EventHandler
    private void onDestroy(VehicleDestroyEvent event) {
        event.setCancelled(EventUtils.isVehicleEventCancelled(event, Flag.vehicle_destroy));
    }

    @EventHandler
    private void onEnter(VehicleEnterEvent event) {
        event.setCancelled(EventUtils.isVehicleEventCancelled(event, Flag.vehicle_enter));
    }
}
