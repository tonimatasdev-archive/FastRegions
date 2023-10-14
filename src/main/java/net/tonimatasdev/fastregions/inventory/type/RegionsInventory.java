package net.tonimatasdev.fastregions.inventory.type;

import net.tonimatasdev.fastregions.inventory.FastInventory;
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

public class RegionsInventory implements FastInventory {
    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, 54, "Regions");

        InventoryUtils.addGlassPanes(inventory, 0, 1, 7, 8, 9, 17, 27, 35, 36, 37, 43, 44, 45, 53);

        for (Region region : RegionManager.regions.values()) {
            ItemStack itemStack = new ItemStack(Material.PAPER);
            ItemMeta itemMeta = itemStack.getItemMeta();

            if (itemMeta != null) {
                itemMeta.setDisplayName(region.getName());
                List<String> lore = new ArrayList<>();
                lore.add(Format.format("&6&lPos1 &b" + Format.toFormattedText(region.getFirstPosition())));
                lore.add(Format.format("&6&lPos2 &b" + Format.toFormattedText(region.getSecondPosition())));
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

        inventory.setItem(46, new ItemStack(Material.RED_WOOL));
        inventory.setItem(52, new ItemStack(Material.LIME_WOOL));

        inventory.setItem(50, new ItemStack(Material.ARROW));
        inventory.setItem(48, new ItemStack(Material.ARROW));



        return inventory;
    }

    @Override
    public void onAction(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
