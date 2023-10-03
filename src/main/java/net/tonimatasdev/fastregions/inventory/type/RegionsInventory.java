package net.tonimatasdev.fastregions.inventory.type;

import net.tonimatasdev.fastregions.region.Region;
import net.tonimatasdev.fastregions.region.RegionManager;
import net.tonimatasdev.fastregions.util.Format;
import net.tonimatasdev.fastregions.util.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class RegionsInventory {
    public static Inventory create() {
        Inventory inventory = Bukkit.createInventory(null, 54, "Regions");

        InventoryUtils.addGlassPanes(inventory, 0, 1, 7, 8, 9, 17, 27, 35, 36, 37, 43, 44);

        for (Region region : RegionManager.regions.values()) {
            ItemStack itemStack = new ItemStack(Material.PAPER);
            ItemMeta itemMeta = itemStack.getItemMeta();

            if (itemMeta != null) {
                itemMeta.setDisplayName(region.getName());
                List<String> lore = new ArrayList<>();
                lore.add(Format.format("&6&lPos1 &b" + Format.locationFormat(region.getFirstPosition())));
                lore.add(Format.format("&6&lPos2 &b" + Format.locationFormat(region.getSecondPosition())));
                lore.add(" ");
                lore.add(Format.format("&e&lLeft-Click &7&oOpen region options"));


                itemMeta.setLore(lore);
            }

            itemStack.setItemMeta(itemMeta);

            for (int slot : InventoryUtils.integers) {
                if (inventory.getItem(slot) != null) continue;
                inventory.setItem(slot, itemStack);
                break;
            }
        }

        inventory.setItem(52, new ItemStack(Material.LIME_WOOL));

        inventory.setItem(50, new ItemStack(Material.ARROW));
        inventory.setItem(48, new ItemStack(Material.ARROW));



        return inventory;
    }

    public static void result(Inventory inventory, InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
