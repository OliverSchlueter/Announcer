package de.oliver.announcer;

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
        // TODO: not implemented
    }

}
