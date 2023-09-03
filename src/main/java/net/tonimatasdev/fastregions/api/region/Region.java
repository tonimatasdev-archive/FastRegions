package net.tonimatasdev.fastregions.api.region;

import net.tonimatasdev.fastregions.api.flag.Flag;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

public record Region(String name, World world, Location location1, Location location2, List<Flag> flags) {
    public boolean isInRegion(Location location) {
        double minX = Math.min(location1.getX(), location2.getX());
        double minY = Math.min(location1.getY(), location2.getY());
        double minZ = Math.min(location1.getZ(), location2.getZ());

        double maxX = Math.max(location1.getX(), location2.getX());
        double maxY = Math.max(location1.getY(), location2.getY());
        double maxZ = Math.max(location1.getZ(), location2.getZ());

        double targetX = location.getX();
        double targetY = location.getY();
        double targetZ = location.getZ();

        return (targetX >= minX && targetX <= maxX) && (targetY >= minY && targetY <= maxY) && (targetZ >= minZ && targetZ <= maxZ);
    }

    public boolean hasFlag(Flag flag) {
        return flags.contains(flag);
    }

    public void markRegion() {
        // TODO: Create logic
    }
}
