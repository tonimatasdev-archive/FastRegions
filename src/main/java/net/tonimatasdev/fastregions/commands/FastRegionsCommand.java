package net.tonimatasdev.fastregions.commands;

import net.tonimatasdev.fastregions.FastRegions;
import net.tonimatasdev.fastregions.util.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class FastRegionsCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("version")) {
                MessageUtil.sendMessage(sender, true, "This server have the version " + FastRegions.getInstance().getDescription().getVersion());
            }

            if (args[0].equalsIgnoreCase("reload")) {
                // TODO: Create the reload logic.
            }
        } else {
            MessageUtil.argumentsError(sender);
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 0) {
            completions.add("reload");
            completions.add("version");
        }

        return completions;
    }
}
