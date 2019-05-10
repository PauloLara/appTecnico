package com.example.paulo.apptecnico;

/**
 * Created by Srijith on 08-10-2017.
 */

public class User {

    private int id;
    private String name;
    private String imageUrl;
    private String type;

    public User(int id, String name, String imageUrl, String type) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.imageUrl = user.imageUrl;
        this.type = user.type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getType() {
        return type;
    }
}
