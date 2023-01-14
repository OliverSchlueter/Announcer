package de.oliver.announcer.types;

import de.oliver.announcer.Announcement;
import de.oliver.announcer.AnnouncerLoop;
import de.oliver.announcer.Loopable;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChatAnnouncement extends Announcement implements Loopable {

    private final AnnouncerLoop loop;
    private boolean paused;

    public ChatAnnouncement(String name, Component[] messages) {
        super(name, messages);
        paused = false;
        loop = new AnnouncerLoop(this, this);
    }

    @Override
    public void send() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (Component message : messages) {
                player.sendMessage(message);
            }
        }
    }

    @Override
    public AnnouncerLoop getLoop() {
        return loop;
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
