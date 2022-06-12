package com.example.dailynews;

public class News {
    int id;
    String url;
    String description;
    String heading;

    public News(int id, String head, String des, String url){
        this.id = id;
        this.heading= head;
        this.description = des;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
