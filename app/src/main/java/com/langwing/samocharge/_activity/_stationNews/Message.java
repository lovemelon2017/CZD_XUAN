package com.langwing.samocharge._activity._stationNews;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = -1247715068100487728L;

    private String message;

    private String type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
