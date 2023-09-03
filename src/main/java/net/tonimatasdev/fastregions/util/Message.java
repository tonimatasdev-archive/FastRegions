package net.tonimatasdev.fastregions.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Message {
    public static void sendMessage(CommandSender sender, boolean positive, String message) {
        if (positive) {
            sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.GREEN + "+" + ChatColor.WHITE + "] FastRegions: " + message);
        } else {
            sender.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "-" + ChatColor.WHITE + "] FastRegions: " + message);
        }
    }

    public static void argumentsError(CommandSender sender) {
        sendMessage(sender, false, "You have to add more arguments.");
    }
}
