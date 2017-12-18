package com.example.sodr.appstudio;

/**
 * Created by Sodré on 10/12/2017.
 */

public class Photo {

    private int id;
    private String author;
    private String title;
    private String local;
    private int votes;

    public Photo(int id, String author, String title, String local, int votes) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.local = local;
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getLocal() {
        return local;
    }

    public int getVotes() {
        return votes;
    }
}
