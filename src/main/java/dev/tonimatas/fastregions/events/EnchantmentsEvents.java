package dev.tonimatas.fastregions.events;

import dev.tonimatas.fastregions.flag.Flag;
import dev.tonimatas.fastregions.util.EventUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantmentsEvents implements Listener {
    @EventHandler
    private void onEnchantItem(EnchantItemEvent event) {
        if (EventUtils.isRegionNotContainFlag(event.getEnchanter(), Flag.enchant_item, event.getEnchantBlock().getWorld(), event.getEnchantBlock().getLocation())) event.setCancelled(true);
    }
}
