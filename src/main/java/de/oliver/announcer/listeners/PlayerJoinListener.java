package de.oliver.announcer.listeners;

import de.oliver.announcer.AnnouncementManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        if(AnnouncementManager.getJoinAnnouncement() != null){
            AnnouncementManager.getJoinAnnouncement().send(event.getPlayer());
        }

    }

}
