package dev.tonimatas.fastregions.util;

import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

public class Format {

    public static String format(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String toFormattedText(Location location) {
        return location.getX() + " " + location.getY() + " " + location.getZ();
    }

    public static Location toJson(String name, World world, JsonObject jsonObject) {
        String[] rawLocation = jsonObject.get(name).getAsString().split(",");
        return new Location(world, Integer.parseInt(rawLocation[0]), Integer.parseInt(rawLocation[1]), Integer.parseInt(rawLocation[2]));
    }
}
