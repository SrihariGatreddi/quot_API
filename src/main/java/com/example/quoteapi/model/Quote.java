package com.example.quoteapi.model;

public class Quote {
    private int id;
    private String text;
    private int counter;

    public Quote(int id, String text) {
        this.id = id;
        this.text = text;
        this.counter = 0;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        this.counter++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

