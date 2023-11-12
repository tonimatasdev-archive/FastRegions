package dev.tonimatas.fastregions.events;

import dev.tonimatas.fastregions.flag.Flag;
import dev.tonimatas.fastregions.util.EventUtils;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.Gate;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerEvents implements Listener {
    @EventHandler
    public void onInventoryOpen(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null) return;

        Flag flag;

        flag = switch (block.getType()) {
            case DISPENSER -> Flag.interact_dispenser;
            case DROPPER -> Flag.interact_dropper;
            case FURNACE -> Flag.interact_furnace;
            case CRAFTING_TABLE -> Flag.interact_crafting_table;
            case ENCHANTING_TABLE -> Flag.interact_enchanting;
            case BREWING_STAND -> Flag.interact_brewing;
            case ENDER_CHEST -> Flag.interact_enderchest;
            case CHEST -> Flag.interact_chest;
            case ANVIL, DAMAGED_ANVIL, CHIPPED_ANVIL -> Flag.interact_anvil;
            case SMITHING_TABLE -> Flag.interact_smithing;
            case BEACON -> Flag.interact_beacon;
            case HOPPER -> Flag.interact_hopper;
            case SHULKER_BOX -> Flag.interact_shulker_box;
            case BARREL -> Flag.interact_barrel;
            case BLAST_FURNACE -> Flag.interact_blast_furnace;
            case LECTERN -> Flag.interact_lectern;
            case SMOKER -> Flag.interact_smoker;
            case LOOM -> Flag.interact_loom;
            case CARTOGRAPHY_TABLE -> Flag.interact_cartography;
            case GRINDSTONE -> Flag.interact_gridstone;
            case STONECUTTER -> Flag.interact_stonecutter;
            case COMPOSTER -> Flag.interact_composter;
            case CHISELED_BOOKSHELF -> Flag.interact_chiseled_bookshelf;
            case JUKEBOX -> Flag.interact_jukebox;
            case BELL -> Flag.interact_belt;
            case REDSTONE -> Flag.interact_redstone_dust;
            case NOTE_BLOCK -> Flag.interact_note_block;
            case END_PORTAL -> Flag.interact_end_portal_frame;
            case CAMPFIRE, SOUL_CAMPFIRE -> Flag.interact_campfire;
            default -> null;
        };

        BlockData blockData = block.getBlockData();
        if (blockData instanceof Door) flag = Flag.interact_door;
        if (blockData instanceof TrapDoor) flag = Flag.interact_trapdoor;
        if (blockData instanceof Gate) flag = Flag.interact_gate;

        if (flag == null) return;
        if (EventUtils.isPlayerEventCancelled(event, flag)) event.setCancelled(true);
    }
}
