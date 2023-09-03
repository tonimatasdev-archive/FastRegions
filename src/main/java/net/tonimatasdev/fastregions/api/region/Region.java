package net.tonimatasdev.fastregions.api.region;

import com.google.common.reflect.TypeToken;
import com.google.gson.JsonObject;
import net.tonimatasdev.fastregions.FastRegions;
import net.tonimatasdev.fastregions.api.FastRegionsAPI;
import net.tonimatasdev.fastregions.api.flag.Flag;
import net.tonimatasdev.fastregions.util.Json;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Region {
    private String name;
    private final World world;
    private final Location firstPosition;
    private final Location secondPosition;
    private List<Flag> flags;

    public Region(String name, World world, Player player) {
        Location firstPosition = FastRegionsAPI.pos1.get(player);
        Location secondPosition = FastRegionsAPI.pos2.get(player);

        this.name = name;
        this.world = world;
        this.firstPosition = new Location(world, firstPosition.getBlockX(), firstPosition.getBlockY(), firstPosition.getBlockZ());
        this.secondPosition = new Location(world, secondPosition.getBlockX(), secondPosition.getBlockY(), secondPosition.getBlockZ());
        this.flags = new ArrayList<>();
        FastRegionsAPI.pos1.remove(player);
        FastRegionsAPI.pos2.remove(player);
        RegionManager.regions.put(name, this);
        save();
    }

    public Region(String name, World world, Location location1, Location location2, List<Flag> flags) {
        this.name = name;
        this.world = world;
        this.firstPosition = new Location(world, location1.getBlockX(), location1.getBlockY(), location1.getBlockZ());
        this.secondPosition = new Location(world, location2.getBlockX(), location2.getBlockY(), location2.getBlockZ());
        this.flags = flags;
        RegionManager.regions.put(name, this);
    }

    public static void load(File file) {
        JsonObject jsonObject = null;

        try (FileReader reader = new FileReader(file)) {
            jsonObject = FastRegionsAPI.gson.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            FastRegions.getInstance().getLogger().severe("Error on load region " + file.getName().replaceAll(".json", ""));
        }

        if (jsonObject == null) return;

        World world = Bukkit.getWorld(jsonObject.get("world").getAsString());

        if (world == null) return;

        String name = jsonObject.get("name").getAsString();
        Location firstPosition = Json.getLocation("firstPosition", world, jsonObject);
        Location secondPosition = Json.getLocation("secondPosition", world, jsonObject);
        List<String> flagNames = FastRegionsAPI.gson.fromJson(jsonObject.get("flags"), new TypeToken<List<String>>(){}.getType());
        List<Flag> flags = flagNames.stream().map(Flag::valueOf).toList();

        new Region(name, world, firstPosition, secondPosition, flags);
    }

    public void save() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("world", world.getName());
        jsonObject.addProperty("firstPosition", firstPosition.getBlockX() + "," + firstPosition.getBlockY() + "," + firstPosition.getBlockZ());
        jsonObject.addProperty("secondPosition", secondPosition.getBlockX() + "," + secondPosition.getBlockY() + "," + secondPosition.getBlockZ());
        jsonObject.add("flags", FastRegionsAPI.gson.toJsonTree(flags.stream().map(Flag::name).collect(Collectors.toList())));

        String json = FastRegionsAPI.gson.toJson(jsonObject);

        try (FileWriter writer = new FileWriter(FastRegions.getInstance().getDataFolder() + "\\regions\\" + name + ".json")) {
            writer.write(json);
        } catch (IOException e) {
            FastRegions.getInstance().getLogger().severe("Error on save region " + name);
        }
    }

    public boolean isInRegion(Location location) {
        int minX = Math.min(firstPosition.getBlockX(), secondPosition.getBlockX());
        int minY = Math.min(firstPosition.getBlockY(), secondPosition.getBlockY());
        int minZ = Math.min(firstPosition.getBlockZ(), secondPosition.getBlockZ());

        int maxX = Math.max(firstPosition.getBlockX(), secondPosition.getBlockX());
        int maxY = Math.max(firstPosition.getBlockY(), secondPosition.getBlockY());
        int maxZ = Math.max(firstPosition.getBlockZ(), secondPosition.getBlockZ());

        int targetX = location.getBlockX();
        int targetY = location.getBlockY();
        int targetZ = location.getBlockZ();

        return (targetX >= minX && targetX <= maxX) && (targetY >= minY && targetY <= maxY) && (targetZ >= minZ && targetZ <= maxZ);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public World getWorld() {
        return world;
    }

    public Location getFirstPosition() {
        return firstPosition;
    }

    public Location getSecondPosition() {
        return secondPosition;
    }

    public List<Flag> getFlags() {
        return flags;
    }

    public void addFlag(Flag flag) {
        List<Flag> newFlagList = new ArrayList<>(flags);
        newFlagList.add(flag);
        flags = newFlagList;
    }

    public boolean hasFlag(Flag flag) {
        return flags.contains(flag);
    }

    public void deleteFlag(Flag flag) {
        List<Flag> newFlagList = new ArrayList<>(flags);
        newFlagList.remove(flag);
        flags = newFlagList;
    }
}
