package net.tonimatasdev.fastregions.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.tonimatasdev.fastregions.FastRegions;
import net.tonimatasdev.fastregions.util.Message;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class FastRegionsAPI {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static String regionsFolder = FastRegions.getInstance().getDataFolder().getPath() + "\\regions";

    public static Map<Player, Location> pos1 = new HashMap<>();
    public static Map<Player, Location> pos2 = new HashMap<>();

    public static boolean isPositionsSet(Player player) {
        boolean x = true;

        if (FastRegionsAPI.pos1.get(player) == null) {
            Message.sendMessage(player, false, "Please set the position 1 with \"/region pos1\".");
            x = false;
        }

        if (FastRegionsAPI.pos2.get(player) == null) {
            Message.sendMessage(player, false, "Please set the position 2 with \"/region pos2\".");
            x = false;
        }

        return x;
    }
}
