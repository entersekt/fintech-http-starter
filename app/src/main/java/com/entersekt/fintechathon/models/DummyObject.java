package com.entersekt.fintechathon.models;

public class DummyObject {

    private final String title;
    private final String body;

    public DummyObject(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "DummyObject{" +
                "title='" + title + '\'' +
                ", body='" + body + "...\'" +
                '}';
    }
}
