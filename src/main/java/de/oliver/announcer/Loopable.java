package de.oliver.announcer;

public interface Loopable {

    boolean isPaused();
    void pauseLoop();
    void continueLoop();

}
