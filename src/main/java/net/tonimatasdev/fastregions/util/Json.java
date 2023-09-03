package net.tonimatasdev.fastregions.util;

import com.google.gson.JsonObject;
import org.bukkit.Location;
import org.bukkit.World;

public class Json {
    public static Location getLocation(String name, World world, JsonObject jsonObject) {
        String[] rawLocation = jsonObject.get(name).getAsString().split(",");
        return new Location(world, Integer.parseInt(rawLocation[0]), Integer.parseInt(rawLocation[1]), Integer.parseInt(rawLocation[2]));
    }
}
