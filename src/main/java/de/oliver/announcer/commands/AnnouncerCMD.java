package de.oliver.announcer.commands;

import de.oliver.announcer.AnnouncementManager;
import de.oliver.announcer.Announcer;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class AnnouncerCMD implements CommandExecutor, TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 1){
            return Arrays.asList("reload");
        }

        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 0) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Announcer plugin version " + Announcer.getInstance().getDescription().getVersion() + " by OliverHD</green>"));
        } else if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
            AnnouncementManager.loadAnnouncements();
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Reloaded all announcements!</green>"));
        }
        return false;
    }
}
