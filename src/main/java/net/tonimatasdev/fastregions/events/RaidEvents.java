package net.tonimatasdev.fastregions.events;

import net.tonimatasdev.fastregions.api.flag.Flag;
import net.tonimatasdev.fastregions.api.region.Region;
import net.tonimatasdev.fastregions.api.region.RegionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidTriggerEvent;

public class RaidEvents implements Listener {
    @EventHandler
    private void onRaidTriggered(RaidTriggerEvent event) {
        Region region = RegionManager.getRegion(event.getWorld(), event.getRaid().getLocation());
        event.setCancelled(region != null && !region.hasFlag(Flag.raid));
    }
}
