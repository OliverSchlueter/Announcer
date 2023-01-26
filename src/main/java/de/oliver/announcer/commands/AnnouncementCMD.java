package de.oliver.announcer.commands;

import de.oliver.announcer.Announcement;
import de.oliver.announcer.AnnouncementManager;
import de.oliver.announcer.Loopable;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnouncementCMD implements CommandExecutor, TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 1){
            return Arrays.asList("pause", "resume");
        } else if(args.length == 2){
            List<String> suggestions = new ArrayList<>();

            for (Announcement announcement : AnnouncementManager.getAllAnnouncements()) {
                if(announcement instanceof Loopable){
                    suggestions.add(announcement.getName());
                }
            }

            return suggestions;
        }

        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length != 2){
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>/Announcement <pause|resume> <announcement> - pause for resume a announcement</green>"));
            return false;
        }

        String action = args[0];
        String announcementName = args[1];
        Announcement announcement = AnnouncementManager.getAnnouncement(announcementName);

        if(announcement == null || !(announcement instanceof Loopable loopable)){
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Could not find announcement</red>"));
            return false;
        }

        switch (action.toLowerCase()){
            case "pause" -> {
                if(loopable.isPaused()){
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>This announcement is already paused</red>"));
                    return false;
                }

                loopable.pauseLoop();
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Successfully paused the " + announcement.getName() + " announcement</green>"));
            }
            case "resume" -> {
                if(!loopable.isPaused()){
                    sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>This announcement is already running</red>"));
                    return false;
                }

                loopable.continueLoop();
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Successfully resumed the " + announcement.getName() + " announcement</green>"));
            }
            default -> sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Invalid action (use 'pause' or 'resume')</red>"));
        }

        return false;
    }
}
