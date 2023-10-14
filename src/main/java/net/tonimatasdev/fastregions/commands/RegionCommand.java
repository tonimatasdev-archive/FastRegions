package net.tonimatasdev.fastregions.commands;

import net.tonimatasdev.fastregions.api.FastRegionsAPI;
import net.tonimatasdev.fastregions.flag.Flag;
import net.tonimatasdev.fastregions.inventory.InventoryManager;
import net.tonimatasdev.fastregions.inventory.type.RegionsInventory;
import net.tonimatasdev.fastregions.region.Region;
import net.tonimatasdev.fastregions.region.RegionManager;
import net.tonimatasdev.fastregions.util.Message;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SuppressWarnings("NullableProblems")
public class RegionCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();

            if (args.length == 0) {
                Message.argumentsError(sender);
                return false;
            }

            if (player == null) return false;

            if (args[0].equalsIgnoreCase("pos1")) {
                Location location = player.getLocation();

                FastRegionsAPI.pos1.put(player, location);
                Message.sendMessage(sender, true, "The position 1 has been set on " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + ".");
            }

            if (args[0].equalsIgnoreCase("pos2")) {
                Location location = player.getLocation();

                FastRegionsAPI.pos2.put(player, location);
                Message.sendMessage(sender, true, "The position 2 has been set on " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + ".");
            }

            if (args[0].equalsIgnoreCase("create")) {
                if (args.length < 2) {
                    Message.sendMessage(sender, false, "Please set the region name.");
                    return false;
                }

                if (!FastRegionsAPI.isPositionsSet(player)) return false;

                if (RegionManager.exist(args[1])) {
                    Message.sendMessage(sender, false, "The region already exists, try with another name.");
                    return false;
                }

                new Region(args[1], player.getWorld(), player);
                Message.sendMessage(sender, true, "Region \"" + args[1] + "\" has been created.");
            }
        }

        if (args[0].equalsIgnoreCase("remove")) {
            if (RegionManager.regions.get(args[1]) == null) Message.sendMessage(sender, false, "The region \"" + args[1] + "\" not exist.");

            RegionManager.deleteRegion(args[1]);
            Message.sendMessage(sender, true, "Region \"" + args[1] + "\" has been removed.");
        }

        if (args[0].equalsIgnoreCase("flag")) {
            if (args.length < 2) {
                Message.sendMessage(sender, false, "Please set the region name.");
                return false;
            }

            String regionName = args[1];

            if (!RegionManager.exist(regionName)) {
                Message.sendMessage(sender, false, "The region \"" + args[1] + "\" not exist.");
                return false;
            }

            Region region = RegionManager.regions.get(regionName);

            if (args.length < 3) {
                Message.sendMessage(sender, false, "Please provide more arguments.");
                return false;
            }

            if (args.length < 4) {
                Message.sendMessage(sender, false, "Please set the flag.");
                return false;
            }

            if (args[2].equalsIgnoreCase("add")) {
                region.addFlag(Flag.valueOf(args[3]));
                Message.sendMessage(sender, true, "You have added the flag " + args[3] + " in the region " + args[1]);

            }

            if (args[2].equalsIgnoreCase("remove")) {
                region.deleteFlag(Flag.valueOf(args[3]));
                Message.sendMessage(sender, true, "You have removed the flag " + args[3] + " in the region " + args[1]);
            }
        }

        if (args[0].equalsIgnoreCase("tp")) {
            // TODO: TP to the region
        }

        if (args[0].equalsIgnoreCase("list")) {
            // TODO: Send the list of regions
        }

        if (args[0].equalsIgnoreCase("menu")) {
            if (sender instanceof Player player) {
                InventoryManager.openInventory(player, new RegionsInventory());
            }
        }

        return true;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("remove");
            completions.add("list");
            completions.add("flag");
            completions.add("pos1");
            completions.add("pos2");
            completions.add("create");
            completions.add("menu");

        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("create")) {
                completions.add("regionName");
            }

            if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("flag")) {
                Set<String> regions = RegionManager.regions.keySet();

                if (regions.isEmpty()) {
                    Message.sendMessage(sender, false, "You don't have regions.");
                } else {
                    completions.addAll(regions);
                }
            }
        }

        if (args.length == 3 && args[0].equalsIgnoreCase("flag")) {
            completions.add("add");
            completions.add("remove");
        }

        if (args.length == 4) {
            if (args[2].equalsIgnoreCase("add")) {
                completions.addAll(Arrays.stream(Flag.values()).map(Flag::name).toList());
            }


            if (args[2].equalsIgnoreCase("remove")) {
                Region region = RegionManager.regions.get(args[1]);

                if (region != null) completions.addAll(region.getFlags().stream().map(Flag::name).toList());
            }
        }

        return completions;
    }
}
