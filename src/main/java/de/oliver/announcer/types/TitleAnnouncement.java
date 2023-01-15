package de.oliver.announcer.types;

import de.oliver.announcer.Announcement;
import de.oliver.announcer.Loopable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Duration;

public class TitleAnnouncement extends Announcement implements Loopable {

    private boolean paused;

    public TitleAnnouncement(String name, Component title, Component subtitle) {
        super(name, new Component[]{ title, subtitle });
        paused = false;
    }

    @Override
    public void send() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.showTitle(Title.title(messages[0], messages[1], Title.Times.times(Duration.ofSeconds(1), Duration.ofSeconds(2), Duration.ofSeconds(1))));
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
