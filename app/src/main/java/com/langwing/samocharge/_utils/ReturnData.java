package com.langwing.samocharge._utils;

import java.io.Serializable;

/**
 * To Change The World
 * 2018/4/18 15:41:36
 * Created by Mr.Wang
 */
public class ReturnData implements Serializable {

    private static final long serialVersionUID = -5575675117252571091L;
    public String data;
    public String message;
    public int code;
    public boolean status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
