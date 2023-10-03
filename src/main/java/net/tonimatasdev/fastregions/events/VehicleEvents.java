package net.tonimatasdev.fastregions.events;

import net.tonimatasdev.fastregions.flag.Flag;
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
        if (EventUtils.isVehicleEventCancelled(event, Flag.vehicle_create)) event.setCancelled(true);
    }

    @EventHandler
    private void onDamage(VehicleDamageEvent event) {
        if (EventUtils.isVehicleEventCancelled(event, Flag.vehicle_damage)) event.setCancelled(true);
    }

    @EventHandler
    private void onDestroy(VehicleDestroyEvent event) {
        if (EventUtils.isVehicleEventCancelled(event, Flag.vehicle_destroy)) event.setCancelled(true);
    }

    @EventHandler
    private void onEnter(VehicleEnterEvent event) {
        if (EventUtils.isVehicleEventCancelled(event, Flag.vehicle_enter)) event.setCancelled(true);
    }
}
