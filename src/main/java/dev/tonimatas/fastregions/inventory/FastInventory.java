package dev.tonimatas.fastregions.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public interface FastInventory {
    Inventory getInventory();

    void onAction(InventoryClickEvent event, Inventory inventory);

    String name();
}
