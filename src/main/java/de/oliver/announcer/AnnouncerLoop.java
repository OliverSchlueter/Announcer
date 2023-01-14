package de.oliver.announcer;

public class AnnouncerLoop implements Runnable {

    private final Announcement announcement;
    private final Loopable loopable;

    public AnnouncerLoop(Announcement announcement, Loopable loopable) {
        this.announcement = announcement;
        this.loopable = loopable;
    }

    @Override
    public void run() {
        if(!loopable.isPaused()) {
            announcement.send();
        }
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public Loopable getLoopable() {
        return loopable;
    }
}
