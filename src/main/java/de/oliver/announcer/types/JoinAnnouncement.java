package de.oliver.announcer.types;

import de.oliver.announcer.Announcement;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class JoinAnnouncement extends Announcement {

    public JoinAnnouncement(String name, Component[] messages) {
        super(name, messages);
    }

    public void send(Player player){
        for (Component message : messages) {
            player.sendMessage(message);
        }
    }

    @Override
    public void send() { }
}
