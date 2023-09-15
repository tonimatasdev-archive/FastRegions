package net.tonimatasdev.fastregions.events;

import net.tonimatasdev.fastregions.api.flag.Flag;
import net.tonimatasdev.fastregions.api.region.Region;
import net.tonimatasdev.fastregions.api.region.RegionManager;
import org.bukkit.block.data.type.Bed;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;

public class BlockEvents implements Listener {
    @EventHandler
    private void onBreak(BlockBreakEvent event) {
        event.setCancelled(isCancelled(event, Flag.block_break));
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent event) {
        event.setCancelled(isCancelled(event, Flag.block_place));
    }

    @EventHandler
    private void onMultiPlace(BlockMultiPlaceEvent event) {
        event.setCancelled(isCancelled(event, Flag.block_place));
    }

    @EventHandler
    private void onTNTPrime(TNTPrimeEvent event) {
        event.setCancelled(isCancelled(event, Flag.tnt_prime));
    }

    @EventHandler
    private void onNotePlay(NotePlayEvent event) {
        event.setCancelled(isCancelled(event, Flag.note_play));
    }

    @EventHandler
    private void onBurn(BlockBurnEvent event) {
        event.setCancelled(isCancelled(event, Flag.block_burn));
    }

    @EventHandler
    private void onSpread(BlockSpreadEvent event) {
        event.setCancelled(isCancelled(event, Flag.block_spread));
    }

    @EventHandler
    private void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(isCancelled(event, Flag.leaves_decay));
    }

    @EventHandler
    private void onSpongeAbsorb(SpongeAbsorbEvent event) {
        event.setCancelled(isCancelled(event, Flag.sponge_absorb));
    }

    @EventHandler
    private void onGrow(BlockGrowEvent event) {
        event.setCancelled(isCancelled(event, Flag.block_grow));
    }

    @EventHandler
    private void onFertilize(BlockFertilizeEvent event) {
        event.setCancelled(isCancelled(event, Flag.block_fertilize));
    }

    @EventHandler
    private void onIgnite(BlockIgniteEvent event) {
        event.setCancelled(isCancelled(event, Flag.block_ignite));
    }

    @EventHandler
    private void onSignChange(SignChangeEvent event) {
        event.setCancelled(isCancelled(event, Flag.sign_change));
    }

    @EventHandler
    private void onRedstone(BlockRedstoneEvent event) {
        if (isCancelled(event, Flag.redstone)) event.setNewCurrent(0);
    }

    @EventHandler
    private void onPiston(BlockPistonEvent event) {
        event.setCancelled(isCancelled(event, Flag.piston));
    }

    @EventHandler
    private void onCauldronLevelChange(CauldronLevelChangeEvent event) {
        event.setCancelled(isCancelled(event, Flag.cauldron_interact));
    }

    @EventHandler
    private void onEntityBlockForm(EntityBlockFormEvent event) {
        if (event.getEntity() instanceof Snowman) {
            event.setCancelled(isCancelled(event, Flag.snowman_snow));
        }

        if (event.getEntity() instanceof Player) {
            event.setCancelled(isCancelled(event, Flag.frost_walker));
        }
    }

    @EventHandler
    private void onExplode(BlockExplodeEvent event) {
        if (event.getBlock().getBlockData() instanceof RespawnAnchor) {
            event.setCancelled(isCancelled(event, Flag.respawn_anchor_explode));
        }

        if (event.getBlock().getBlockData() instanceof Bed) {
            event.setCancelled(isCancelled(event, Flag.bed_explode));
        }
    }

    @EventHandler
    private void onBellRing(BellRingEvent event) {
        event.setCancelled(isCancelled(event, Flag.bell_ring));
    }

    @EventHandler
    private void onBellRing(BlockDropItemEvent event) {
        event.setCancelled(isCancelled(event, Flag.block_drop));
    }

    @EventHandler
    private void onReceiveGame(BlockReceiveGameEvent event) {
        event.setCancelled(isCancelled(event, Flag.sensor));
    }

    @EventHandler
    private void onShearEntity(BlockShearEntityEvent event) {
        event.setCancelled(isCancelled(event, Flag.block_shears));
    }

    @EventHandler
    private void onDispense(BlockDispenseEvent event) {
        event.setCancelled(isCancelled(event, Flag.block_dispense));
    }

    @EventHandler
    private void onExp(BlockExpEvent event) {
        if (isCancelled(event, Flag.block_dispense)) event.setExpToDrop(0);
    }

    public boolean isCancelled(BlockEvent event, Flag flag) {
        Region region = RegionManager.getRegion(event.getBlock().getWorld(), event.getBlock().getLocation());
        return region != null && region.hasFlag(flag);
    }
}
