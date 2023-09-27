package net.tonimatasdev.fastregions.events;

import net.tonimatasdev.fastregions.api.flag.Flag;
import net.tonimatasdev.fastregions.api.region.Region;
import net.tonimatasdev.fastregions.api.region.RegionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantmentsEvents implements Listener {
    @EventHandler
    private void onEnchantItem(EnchantItemEvent event) {
        Region region = RegionManager.getRegion(event.getEnchanter().getWorld(), event.getEnchanter().getLocation());
        event.setCancelled(region != null && !region.hasFlag(Flag.enchant_item));
    }
}
