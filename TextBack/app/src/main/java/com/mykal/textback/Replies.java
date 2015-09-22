package com.mykal.textback;

/**
 * Created by mykal on 8/3/15.
 */
public class Replies {

    long id;
    String name, message;

    Replies() {

    }

    Replies(long id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    Replies(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
