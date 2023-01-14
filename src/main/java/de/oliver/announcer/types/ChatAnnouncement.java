package de.oliver.announcer.types;

import de.oliver.announcer.Announcement;
import de.oliver.announcer.Loopable;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class ChatAnnouncement extends Announcement implements Loopable {

    private boolean paused;

    public ChatAnnouncement(String name, Component[] messages) {
        super(name, messages);
        paused = false;
    }

    @Override
    public void send() {
        for (Component message : messages) {
            Bukkit.broadcast(message);
        }
    }

    @Override
    public void pauseLoop() {
        paused = true;
    }

    @Override
    public void continueLoop() {
        paused = false;
    }

    @Override
    public boolean isPaused() {
        return paused;
    }
}
