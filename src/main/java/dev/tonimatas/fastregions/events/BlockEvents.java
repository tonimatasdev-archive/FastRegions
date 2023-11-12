package dev.tonimatas.fastregions.events;

import dev.tonimatas.fastregions.flag.Flag;
import dev.tonimatas.fastregions.util.EventUtils;
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
        if (EventUtils.isBlockEventCancelled(event, event.getPlayer(), Flag.block_break)) event.setCancelled(true);
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent event) {
        if (EventUtils.isBlockEventCancelled(event, event.getPlayer(), Flag.block_place)) event.setCancelled(true);
    }

    @EventHandler
    private void onMultiPlace(BlockMultiPlaceEvent event) {
        if (EventUtils.isBlockEventCancelled(event, event.getPlayer(), Flag.block_place)) event.setCancelled(true);
    }

    @EventHandler
    private void onTNTPrime(TNTPrimeEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.tnt_prime)) event.setCancelled(true);
    }

    @EventHandler
    private void onNotePlay(NotePlayEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.note_play)) event.setCancelled(true);
    }

    @EventHandler
    private void onBurn(BlockBurnEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.block_burn)) event.setCancelled(true);
    }

    @EventHandler
    private void onSpread(BlockSpreadEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.block_spread)) event.setCancelled(true);
    }

    @EventHandler
    private void onLeavesDecay(LeavesDecayEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.leaves_decay)) event.setCancelled(true);
    }

    @EventHandler
    private void onSpongeAbsorb(SpongeAbsorbEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.sponge_absorb)) event.setCancelled(true);
    }

    @EventHandler
    private void onGrow(BlockGrowEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.block_grow)) event.setCancelled(true);
    }

    @EventHandler
    private void onFertilize(BlockFertilizeEvent event) {
        if (EventUtils.isBlockEventCancelled(event, event.getPlayer(), Flag.block_fertilize)) event.setCancelled(true);
    }

    @EventHandler
    private void onIgnite(BlockIgniteEvent event) {
        if (EventUtils.isBlockEventCancelled(event, event.getPlayer(), Flag.block_ignite)) event.setCancelled(true);
    }

    @EventHandler
    private void onSignChange(SignChangeEvent event) {
        if (EventUtils.isBlockEventCancelled(event, event.getPlayer(), Flag.sign_change)) event.setCancelled(true);
    }

    @EventHandler
    private void onPistonExtend(BlockPistonExtendEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.pistons)) event.setCancelled(true);
    }

    @EventHandler
    private void onPistonRetract(BlockPistonRetractEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.pistons)) event.setCancelled(true);
    }

    @EventHandler
    private void onCauldronLevelChange(CauldronLevelChangeEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.cauldron_interact)) event.setCancelled(true);
    }

    @EventHandler
    private void onEntityBlockForm(EntityBlockFormEvent event) {
        if (event.getEntity() instanceof Snowman) {
            if (EventUtils.isBlockEventCancelled(event, null, Flag.snowman_snow)) event.setCancelled(true);
        }

        if (event.getEntity() instanceof Player player) {
            if (EventUtils.isBlockEventCancelled(event, player, Flag.frost_walker)) event.setCancelled(true);
        }
    }

    @EventHandler
    private void onExplode(BlockExplodeEvent event) {
        if (event.getBlock().getBlockData() instanceof RespawnAnchor) {
            if (EventUtils.isBlockEventCancelled(event, null, Flag.respawn_anchor_explode)) event.setCancelled(true);
        }

        if (event.getBlock().getBlockData() instanceof Bed) {
            if (EventUtils.isBlockEventCancelled(event, null, Flag.bed_explode)) event.setCancelled(true);
        }
    }

    @EventHandler
    private void onBellRing(BellRingEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (EventUtils.isBlockEventCancelled(event, player, Flag.bell_ring)) event.setCancelled(true);
        }
    }

    @EventHandler
    private void onBellRing(BlockDropItemEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.block_drop)) event.setCancelled(true);
    }

    @EventHandler
    private void onReceiveGame(BlockReceiveGameEvent event) {
        if (EventUtils.isBlockEventCancelled(event, null, Flag.sensor)) event.setCancelled(true);
    }
}
