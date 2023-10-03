package net.tonimatasdev.fastregions.util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class InventoryUtils {
    public static List<Integer> integers = Arrays.asList(10, 11, 12, 13, 14, 15, 16, 20, 21 ,22 ,23, 24, 25, 26, 30 ,31 ,32, 33, 34, 35, 36);

    public static void addGlassPanes(Inventory inventory, int ... slots) {
        ItemStack itemStack = new ItemStack(Material.GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta != null) itemMeta.setDisplayName(" ");
        itemStack.setItemMeta(itemMeta);

        for (int slot : slots) {
            inventory.setItem(slot, itemStack);
        }
    }
}
