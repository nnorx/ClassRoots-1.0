package com.classroots.classroots.models;

/**
 * Created by Nick on 12/12/2017.
 */

public class ForumListItem {
    private Thread thread;
    private UserAccountSettings settings;

    public ForumListItem() {

    }

    public ForumListItem(Thread thread, UserAccountSettings settings) {
        this.thread = thread;
        this.settings = settings;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public UserAccountSettings getSettings() {
        return settings;
    }

    public void setSettings(UserAccountSettings settings) {
        this.settings = settings;
    }

    @Override
    public String toString() {
        return "ForumListItem{" +
                "thread=" + thread +
                ", settings=" + settings +
                '}';
    }
}
