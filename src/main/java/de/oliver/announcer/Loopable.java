package de.oliver.announcer;

public interface Loopable {

    AnnouncerLoop getLoop();

    boolean isPaused();
    void pauseLoop();
    void continueLoop();

}
