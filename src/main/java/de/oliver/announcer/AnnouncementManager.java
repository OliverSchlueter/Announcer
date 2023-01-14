package de.oliver.announcer;

import de.oliver.announcer.types.ActionBarAnnouncement;
import de.oliver.announcer.types.ChatAnnouncement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class AnnouncementManager {

    private static int interval = 10; // in seconds
    private static final Map<String, Announcement> announcements = new HashMap<>();

    public static void addAnnouncement(Announcement announcement){
        announcements.put(announcement.getName().toLowerCase(), announcement);
        if(announcement instanceof Loopable){
            Announcer.getInstance().getLoop().getAnnouncements().offer(announcement);
        }
    }

    public static void removeAnnouncement(String name){
        announcements.remove(name);
    }

    public static  Announcement getAnnouncement(String name){
        if(announcements.containsKey(name.toLowerCase())){
            return announcements.get(name.toLowerCase());
        }

        return null;
    }

    public static Collection<Announcement> getAllAnnouncements(){
        return announcements.values();
    }

    /**
     * @return interval in ticks
     */
    public static int getInterval() {
        return interval * 20;
    }

    public static void loadAnnouncements(){
        Announcer.getInstance().reloadConfig();
        FileConfiguration config = Announcer.getInstance().getConfig();

        announcements.clear();
        Announcer.getInstance().getLoop().getAnnouncements().clear();

        if(!config.isSet("interval")){
            interval = 60*3;
            config.set("interval", interval);
        } else {
            interval = config.getInt("interval");
        }

        if(config.isConfigurationSection("announcements")){
            for (String name : config.getConfigurationSection("announcements").getKeys(false)) {
                try {
                    String type = config.getString("announcements." + name + ".type");
                    List<String> messages = config.getStringList("announcements." + name + ".messages");

                    Component[] messagesParsed = new Component[messages.size()];

                    for (int i = 0; i < messages.size(); i++) {
                        messagesParsed[i] = MiniMessage.miniMessage().deserialize(messages.get(i));
                    }

                    Announcement announcement;

                    switch (type.toLowerCase()) {
                        case "chat" -> announcement = new ChatAnnouncement(name, messagesParsed);
                        case "actionbar" -> announcement = new ActionBarAnnouncement(name, messagesParsed[0]);
                        default -> throw new IllegalStateException("Invalid announcer-type provided for " + name);
                    }

                    addAnnouncement(announcement);
                } catch (Exception e){
                    Announcer.getInstance().getLogger().warning("Could not load announcement: " + name);
                    e.printStackTrace();
                }
            }

        } else {
            config.set("announcements.help.type", "chat");
            config.set("announcements.help.messages", new String[]{
                    "<dark_red>---------------------------------------------------</dark_red>",
                    "<red>You need help or want to see all features? - /help</red>",
                    "<dark_red>---------------------------------------------------</dark_red>",
            });

            config.set("announcements.store.type", "actionbar");
            config.set("announcements.store.messages", new String[]{
                    "<dark_red>Visit our store at store.placeholder.com</dark_red>"
            });
        }

        Announcer.getInstance().saveConfig();
    }

}
