package com.langwing.samocharge._fragment._dynamic.bean;

import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = -4131787076405662608L;
    private String id;
    private String name;
    private String headUrl;

    public User(String id, String name, String headUrl) {
        this.id = id;
        this.name = name;
        this.headUrl = headUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    @Override
    public String toString() {
        return "id = " + id
                + "; name = " + name
                + "; headUrl = " + headUrl;
    }
}
