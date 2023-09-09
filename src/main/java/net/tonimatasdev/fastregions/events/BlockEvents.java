package net.tonimatasdev.fastregions.events;

import net.tonimatasdev.fastregions.api.flag.Flag;
import net.tonimatasdev.fastregions.api.region.Region;
import net.tonimatasdev.fastregions.api.region.RegionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockEvents implements Listener {
    @EventHandler
    private void onBlockBreak(BlockBreakEvent event) {
        Region region = RegionManager.getRegion(event.getPlayer().getWorld(), event.getBlock().getLocation());
        if (region != null && region.getFlags().contains(Flag.BLOCK_BREAK)) event.setCancelled(true);
    }
}
