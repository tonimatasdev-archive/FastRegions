package net.tonimatasdev.fastregions.events;

import net.tonimatasdev.fastregions.api.flag.Flag;
import net.tonimatasdev.fastregions.util.EventUtils;
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
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.block_break));
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.block_place));
    }

    @EventHandler
    private void onMultiPlace(BlockMultiPlaceEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.block_place));
    }

    @EventHandler
    private void onTNTPrime(TNTPrimeEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.tnt_prime));
    }

    @EventHandler
    private void onNotePlay(NotePlayEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.note_play));
    }

    @EventHandler
    private void onBurn(BlockBurnEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.block_burn));
    }

    @EventHandler
    private void onSpread(BlockSpreadEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.block_spread));
    }

    @EventHandler
    private void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.leaves_decay));
    }

    @EventHandler
    private void onSpongeAbsorb(SpongeAbsorbEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.sponge_absorb));
    }

    @EventHandler
    private void onGrow(BlockGrowEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.block_grow));
    }

    @EventHandler
    private void onFertilize(BlockFertilizeEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.block_fertilize));
    }

    @EventHandler
    private void onIgnite(BlockIgniteEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.block_ignite));
    }

    @EventHandler
    private void onSignChange(SignChangeEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.sign_change));
    }

    @EventHandler
    private void onRedstone(BlockRedstoneEvent event) {
        if (EventUtils.isBlockEventCancelled(event, Flag.redstone)) event.setNewCurrent(0);
    }

    @EventHandler
    private void onPistonExtend(BlockPistonExtendEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.piston_extend));
    }

    @EventHandler
    private void onPistonRetract(BlockPistonRetractEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.piston_retract));
    }

    @EventHandler
    private void onCauldronLevelChange(CauldronLevelChangeEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.cauldron_interact));
    }

    @EventHandler
    private void onEntityBlockForm(EntityBlockFormEvent event) {
        if (event.getEntity() instanceof Snowman) {
            event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.snowman_snow));
        }

        if (event.getEntity() instanceof Player) {
            event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.frost_walker));
        }
    }

    @EventHandler
    private void onExplode(BlockExplodeEvent event) {
        if (event.getBlock().getBlockData() instanceof RespawnAnchor) {
            event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.respawn_anchor_explode));
        }

        if (event.getBlock().getBlockData() instanceof Bed) {
            event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.bed_explode));
        }
    }

    @EventHandler
    private void onBellRing(BellRingEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.bell_ring));
    }

    @EventHandler
    private void onBellRing(BlockDropItemEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.block_drop));
    }

    @EventHandler
    private void onReceiveGame(BlockReceiveGameEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.sensor));
    }

    @EventHandler
    private void onShearEntity(BlockShearEntityEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.block_shears));
    }

    @EventHandler
    private void onDispense(BlockDispenseEvent event) {
        event.setCancelled(EventUtils.isBlockEventCancelled(event, Flag.block_dispense));
    }

    @EventHandler
    private void onExp(BlockExpEvent event) {
        if (EventUtils.isBlockEventCancelled(event, Flag.block_dispense)) event.setExpToDrop(0);
    }
}
