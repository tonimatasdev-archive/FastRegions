package net.tonimatasdev.fastregions.events;

import net.tonimatasdev.fastregions.flag.Flag;
import net.tonimatasdev.fastregions.util.EventUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;

public class WeatherEvents implements Listener {
    @EventHandler
    private void onLightningStrike(LightningStrikeEvent event) {
        if (EventUtils.isRegionNotContainFlag(null, Flag.lightning_strike, event.getWorld(), event.getLightning().getLocation())) event.setCancelled(true);
    }
}
