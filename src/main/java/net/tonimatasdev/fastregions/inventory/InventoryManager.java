package net.tonimatasdev.fastregions.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager implements Listener {
    public static Map<Inventory, FastInventory> inventories = new HashMap<>();

    public static void openInventory(Player player, FastInventory fastInventory) {
        Inventory inventory = fastInventory.getInventory();

        inventories.put(inventory, fastInventory);
        player.openInventory(inventory);
    }

    @EventHandler
    private void onInteract(InventoryClickEvent event) {
        Inventory result = null;

        for (Inventory inventory : inventories.keySet()) {
            if (inventory.equals(event.getClickedInventory())) {
                result = inventory;
                break;
            }
        }

        if (result == null) return;

        System.out.println(event.getSlot());

        inventories.get(result).onAction(event, result);
    }

    @EventHandler
    private void onClose(InventoryCloseEvent event) {
        inventories.remove(event.getInventory());
    }
}
