package com.classroots.classroots.models;

public class UserAccountSettings {

    private String display_name;
    private String profile_photo;
    private String username;
    private String university;
    private String current_root;
    private String current_root_id;

    public UserAccountSettings(String display_name, String profile_photo, String username, String university, String current_root, String current_root_id) {
        this.display_name = display_name;
        this.profile_photo = profile_photo;
        this.username = username;
        this.university = university;
        this.current_root = current_root;
        this.current_root_id = current_root_id;
    }
    public UserAccountSettings() {

    }

    public String getDisplay_name() {
        return display_name;
    }
    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getProfile_photo() {
        return profile_photo;
    }
    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCurrent_root() {
        return current_root;
    }
    public void setCurrent_root(String current_root) {
        this.current_root = current_root;
    }

    public String getCurrent_root_id() {
        return current_root_id;
    }
    public void setCurrent_root_id(String current_root_id) { this.current_root_id = current_root_id; }


    @Override
    public String toString() {
        return "UserAccountSettings{" +
                ", display_name='" + display_name + '\'' +
                ", profile_photo='" + profile_photo + '\'' +
                ", username='" + username + '\'' +
                ", university='" + university + '\'' +
                '}';
    }
}