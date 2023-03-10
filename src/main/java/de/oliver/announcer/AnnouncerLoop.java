package de.oliver.announcer;

import java.util.LinkedList;
import java.util.Queue;

public class AnnouncerLoop implements Runnable {

    private final Queue<Announcement> announcements;

    public AnnouncerLoop() {
        this.announcements = new LinkedList<>();
    }

    @Override
    public void run() {
        if(announcements.isEmpty()){
            return;
        }

        Announcement announcement = announcements.poll();
        announcements.offer(announcement);

        if(!(announcement instanceof Loopable loopable)){
            return;
        }

        if(loopable.isPaused()){
            run();
            return;
        } else {
            announcement.send();
        }

    }

    public Queue<Announcement> getAnnouncements() {
        return announcements;
    }
}
