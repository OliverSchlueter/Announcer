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

        ChatAnnouncement chatAnnouncement1 = new ChatAnnouncement("store", new Component[]{
                MiniMessage.miniMessage().deserialize("<rainbow>Hello world!</rainbow>"),
                MiniMessage.miniMessage().deserialize("<rainbow>Test 1 2 3</rainbow>"),
        });

        ChatAnnouncement chatAnnouncement2 = new ChatAnnouncement("store2", new Component[]{
                MiniMessage.miniMessage().deserialize("<rainbow>moin moin</rainbow>"),
                MiniMessage.miniMessage().deserialize("<rainbow>kekw</rainbow>"),
        });


        ActionBarAnnouncement actionBarAnnouncement1 = new ActionBarAnnouncement("pog", new Component[]{
           MiniMessage.miniMessage().deserialize("<red>Hello world")
        });

        AnnouncementManager.addAnnouncement(chatAnnouncement1);
        AnnouncementManager.addAnnouncement(chatAnnouncement2);
        AnnouncementManager.addAnnouncement(actionBarAnnouncement1);

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
