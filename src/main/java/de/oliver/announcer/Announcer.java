package de.oliver.announcer;

import de.oliver.announcer.commands.AnnouncerCMD;
import de.oliver.announcer.types.ChatAnnouncement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Announcer extends JavaPlugin {

    private static Announcer instance;

    public Announcer() {
        instance = this;
    }

    @Override
    public void onEnable() {
        getCommand("Announcer").setExecutor(new AnnouncerCMD());

        ChatAnnouncement chatAnnouncement1 = new ChatAnnouncement("store", new Component[]{
                MiniMessage.miniMessage().deserialize("<rainbow>Hello world!</rainbow>"),
                MiniMessage.miniMessage().deserialize("<rainbow>Test 1 2 3</rainbow>"),
        });

        AnnouncementManager.addAnnouncement(chatAnnouncement1);

        Bukkit.getScheduler().runTaskTimer(instance, chatAnnouncement1.getLoop(), AnnouncementManager.getInterval(), AnnouncementManager.getInterval());

    }

    @Override
    public void onDisable() {

    }

    public static Announcer getInstance() {
        return instance;
    }
}
