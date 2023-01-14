package de.oliver.announcer.types;

import de.oliver.announcer.Announcement;
import de.oliver.announcer.Loopable;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBarAnnouncement extends Announcement implements Loopable {

    private boolean paused;

    public ActionBarAnnouncement(String name, Component[] messages) {
        super(name, messages);
    }

    @Override
    public void send() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (Component message : messages) {
                player.sendActionBar(message);
            }
        }
    }

    @Override
    public boolean isPaused() {
        return paused;
    }

    @Override
    public void pauseLoop() {
        paused = true;
    }

    @Override
    public void continueLoop() {
        paused = false;
    }
}
