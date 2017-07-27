package com.nabwera.retrofit_send_objects.api.model;

/**
 * Created by nabwera on 27/07/2017.
 */

public class User {

    private Integer id;
    private String name;
    private String email;
    private int age;
    private String[] topics;

    // Create constructor without id bcoz as we pass it to the server it doesn't have an id yet.
    public User(String name, String email, int age, String[] topics) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.topics = topics;
    }

    // Getter for the ID
    public Integer getId() {
        return id;
    }
}
