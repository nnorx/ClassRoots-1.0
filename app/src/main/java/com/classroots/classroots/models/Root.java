package com.classroots.classroots.models;

public class Root {

    private String name;
    private String user_id;
    private String root_id;
    private String color;


    public Root() {

    }

    public Root(String name, String user_id, String root_id, int color) {
        this.name = name;
        this.user_id = user_id;
        this.root_id = root_id;
        this.color = this.color;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRoot_id() { return root_id; }
    public void setRoot_id(String root_id) {
        this.root_id = root_id;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) { this.color = color; }

    @Override
    public String toString() {
        return "Photo{" +
                ", name='" + name + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}