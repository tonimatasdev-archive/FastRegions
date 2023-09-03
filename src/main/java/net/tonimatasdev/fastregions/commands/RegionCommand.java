package net.tonimatasdev.fastregions.commands;

import net.tonimatasdev.fastregions.api.FastRegionsAPI;
import net.tonimatasdev.fastregions.api.region.Region;
import net.tonimatasdev.fastregions.api.region.RegionManager;
import net.tonimatasdev.fastregions.util.Message;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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

            boolean bo = RegionManager.deleteRegion(args[1]);
            Message.sendMessage(sender, true, "Region \"" + args[1] + "\" has been removed.");
        }

        if (args[0].equalsIgnoreCase("flag")) {
            // TODO: Subcommand to add or remove flags
        }

        if (args[0].equalsIgnoreCase("list")) {
            // TODO: Send the list of regions
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

        }

        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("create")) {
                completions.add("regionName");
            }

            if (args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("flag")) {
                completions.addAll(RegionManager.regions.keySet());
            }
        }

        return completions;
    }
}
