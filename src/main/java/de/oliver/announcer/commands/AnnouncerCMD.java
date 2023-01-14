package de.oliver.announcer.commands;

import de.oliver.announcer.Announcer;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class AnnouncerCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Announcer plugin version " + Announcer.getInstance().getDescription().getVersion() + " by OliverHD</green>"));

        return false;
    }
}
