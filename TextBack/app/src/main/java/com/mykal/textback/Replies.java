package com.mykal.textback;

public class Replies {

    int id;
    String name, message;

    Replies() {

    }

    Replies(int id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    Replies(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public int getId() {
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
