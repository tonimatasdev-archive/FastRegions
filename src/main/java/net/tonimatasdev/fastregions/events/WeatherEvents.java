package net.tonimatasdev.fastregions.events;

import net.tonimatasdev.fastregions.api.flag.Flag;
import net.tonimatasdev.fastregions.api.region.Region;
import net.tonimatasdev.fastregions.api.region.RegionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;

public class WeatherEvents implements Listener {
    @EventHandler
    private void onLightningStrike(LightningStrikeEvent event) {
        Region region = RegionManager.getRegion(event.getWorld(), event.getLightning().getLocation());
        event.setCancelled(region != null && !region.hasFlag(Flag.lightning_strike));
    }
}
