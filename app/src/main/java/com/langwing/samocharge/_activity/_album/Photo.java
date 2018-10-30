package com.langwing.samocharge._activity._album;

import java.io.Serializable;

public class Photo implements Serializable {
    private static final long serialVersionUID = -3338636989909201918L;

    private String path;
    private String name;
    private boolean isChecked;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
