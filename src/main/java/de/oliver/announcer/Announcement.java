package de.oliver.announcer;

import net.kyori.adventure.text.Component;

public abstract class Announcement {

    protected final String name;
    protected Component[] messages;

    public Announcement(String name, Component[] messages) {
        this.name = name;
        this.messages = messages;
    }

    public abstract void send();

    public String getName() {
        return name;
    }

    public Component[] getMessages() {
        return messages;
    }

    public void setMessages(Component[] messages) {
        this.messages = messages;
    }
}
