package com.example.sodr.appstudio;

/**
 * Created by Sodré on 10/12/2017.
 */

public class Vote {
    private int id;
    private String name;
    private String email;
    private String date;
    private int photo_id;

    public Vote(int id, String name, String email, int photo_id) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photo_id = photo_id;
    }

    public int getId() {
        return id;
    }
}
