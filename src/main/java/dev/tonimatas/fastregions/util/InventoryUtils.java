package dev.tonimatas.fastregions.util;

import dev.tonimatas.fastregions.inventory.FastInventory;
import dev.tonimatas.fastregions.inventory.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class InventoryUtils {
    public static List<Integer> USABLE_SLOTS = Arrays.asList(10, 11, 12, 13, 14, 15, 16, 20, 21 ,22 ,23, 24, 25, 26, 30 ,31 ,32, 33, 34, 35, 36);

    public static Inventory create(int rows, String name) {
        return Bukkit.createInventory(null, rows*9, name);
    }

    public static void openOtherInventory(Player player, FastInventory fastInventory) {
        player.closeInventory();
        InventoryManager.openInventory(player, fastInventory);
    }

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
