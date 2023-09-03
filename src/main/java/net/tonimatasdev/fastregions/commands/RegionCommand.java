package net.tonimatasdev.fastregions.commands;

import net.tonimatasdev.fastregions.api.FastRegionsAPI;
import net.tonimatasdev.fastregions.api.region.RegionManager;
import net.tonimatasdev.fastregions.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegionCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();

            if (args.length == 0) MessageUtil.argumentsError(sender);
            if (player == null) return false;

            if (args[0].equalsIgnoreCase("pos1")) {
                FastRegionsAPI.pos1.put(player, player.getLocation());
                MessageUtil.sendMessage(sender, true, "The position 1 has been set on " + player.getLocation() + ".");
            }

            if (args[0].equalsIgnoreCase("pos2")) {
                FastRegionsAPI.pos2.put(player, player.getLocation());
                MessageUtil.sendMessage(sender, true, "The position 2 has been set on " + player.getLocation() + ".");
            }

            if (args[0].equalsIgnoreCase("create")) {
                if (args.length == 1) MessageUtil.sendMessage(sender, false, "Please set the region name.");

                if (FastRegionsAPI.pos1.get(player) == null) MessageUtil.sendMessage(sender, false, "Please set the position 1 with \"/region pos1\".");
                if (FastRegionsAPI.pos2.get(player) == null) MessageUtil.sendMessage(sender, false, "Please set the position 2 with \"/region pos2\".");

                if (RegionManager.regions.get(args[1]) != null) MessageUtil.sendMessage(sender, false, "The region already exists, try with another name.");

                RegionManager.createRegion(args[1], player);
                MessageUtil.sendMessage(sender, true, "Region \"" + args[1] + "\" has been created.");
            }
        }

        if (args[1].equalsIgnoreCase("remove")) {
            if (RegionManager.regions.get(args[1]) == null) MessageUtil.sendMessage(sender, false, "The region \"" + args[1] + "\" not exist.");

            RegionManager.deleteRegion(args[1]);
            MessageUtil.sendMessage(sender, true, "Region \"" + args[1] + "\" has been removed.");
        }

        if (args[1].equalsIgnoreCase("flag")) {
            // TODO: Subcommand to add or remove flags
        }

        if (args[1].equalsIgnoreCase("list")) {
            // TODO: Send the list of regions
        }

        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args[0].equalsIgnoreCase("region")) {
            completions.addAll(Arrays.asList("pos1", "pos2", "create", "remove", "list", "flag"));
        }

        if (args[1].equalsIgnoreCase("create")) {
            completions.add("regionName");
        }

        if (args[1].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("flag")) {
            completions.addAll(RegionManager.regions.keySet());
        }

        return completions;
    }
}
