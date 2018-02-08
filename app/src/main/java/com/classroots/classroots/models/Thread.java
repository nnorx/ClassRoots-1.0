package com.classroots.classroots.models;

public class Thread {

    private String title;
    private String subtitle;
    private String thread_id;
    private String poster;
    private int reply_number;
    private int like_number;
    private String date;



    public Thread() {

    }

    public Thread(String title, String subtitle, String thread_id, String poster, int reply_number, int like_number, String date){
            this.title = title;
            this.subtitle = subtitle;
            this.thread_id = thread_id;
            this.poster = poster;
            this.reply_number = reply_number;
            this.like_number = like_number;
            this.date = date;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) { this.title = title; }

    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public String getThread_id() {
        return thread_id;
    }
    public void setThread_id(String thread_id) { this.thread_id = thread_id; }

    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }

    public int getReply_number() { return reply_number; }
    public void setReply_number(int reply_number) { this.reply_number = reply_number; }

    public int getLike_number() { return like_number; }
    public void setLike_number(int like_number) { this.like_number = like_number; }

    public String getDate() {
        return date;
    }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return "Thread{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", thread_id='" + thread_id + '\'' +
                ", poster='" + poster + '\'' +
                ", reply_number=" + reply_number +
                ", date='" + date + '\'' +
                '}';
    }
}