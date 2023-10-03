package net.tonimatasdev.fastregions.inventory;

import net.tonimatasdev.fastregions.inventory.type.RegionsInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager implements Listener {
    public static Map<Inventory, FastInventoryType> inventories = new HashMap<>();

    public static void openInventory(Player player, Inventory inventory, FastInventoryType inventoryType) {
        inventories.put(inventory, inventoryType);
        player.openInventory(inventory);
    }

    @EventHandler
    private void onInteract(InventoryClickEvent event) {
        Inventory result = null;

        for (Inventory inventory : inventories.keySet()) {
            if (inventory.equals(event.getInventory())) {
                result = inventory;
                break;
            }
        }

        if (result == null) return;

        if (inventories.get(result) == FastInventoryType.REGIONS) {
            RegionsInventory.result(result, event);
        }
    }

    @EventHandler
    private void onClose(InventoryCloseEvent event) {
        inventories.remove(event.getInventory());
    }
}
