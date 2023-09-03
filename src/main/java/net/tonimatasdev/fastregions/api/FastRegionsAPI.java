package net.tonimatasdev.fastregions.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.tonimatasdev.fastregions.FastRegions;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FastRegionsAPI {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static File regionsFolder = new File(FastRegions.getInstance().getDataFolder().getPath() + "\\regions");

    public static Map<Player, Location> pos1 = new HashMap<>();
    public static Map<Player, Location> pos2 = new HashMap<>();
}
