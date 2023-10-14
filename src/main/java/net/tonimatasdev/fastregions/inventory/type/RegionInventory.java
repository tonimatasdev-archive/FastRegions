package net.tonimatasdev.fastregions.inventory.type;

import net.tonimatasdev.fastregions.inventory.FastInventory;
import net.tonimatasdev.fastregions.util.InventoryUtils;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class RegionInventory implements FastInventory {
    @Override
    public Inventory getInventory() {
        Inventory inventory = InventoryUtils.create(6, "Regions");

        InventoryUtils.addGlassPanes(inventory, 0, 1, 7, 8, 9, 17, 27, 35, 36, 37, 43, 44, 45, 53);

        return inventory;
    }

    @Override
    public void onAction(InventoryClickEvent event, Inventory inventory) {

    }
}
