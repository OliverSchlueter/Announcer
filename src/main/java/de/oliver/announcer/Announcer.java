package de.oliver.announcer;

import de.oliver.announcer.commands.AnnouncerCMD;
import de.oliver.announcer.types.ActionBarAnnouncement;
import de.oliver.announcer.types.ChatAnnouncement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Announcer extends JavaPlugin {

    private static Announcer instance;

    private final AnnouncerLoop loop;

    public Announcer() {
        instance = this;
        loop = new AnnouncerLoop();
    }

    @Override
    public void onEnable() {
        getCommand("Announcer").setExecutor(new AnnouncerCMD());

        AnnouncementManager.loadAnnouncements();

        Bukkit.getScheduler().runTaskTimer(instance, loop, AnnouncementManager.getInterval(), AnnouncementManager.getInterval());
    }

    @Override
    public void onDisable() {

    }

    public static Announcer getInstance() {
        return instance;
    }

    public AnnouncerLoop getLoop() {
        return loop;
    }
}
