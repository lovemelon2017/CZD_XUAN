package com.langwing.samocharge._fragment._chargingPile;

import java.io.Serializable;

/**
 * To Change The World
 * 2018-04-08 15:33
 * Created by Mr.Wang
 */
public class Station implements Serializable {
    private static final long serialVersionUID = -6249987211699592834L;

    /**
     * has_park_fee : 0
     * address : 河南省郑州市银河国际酒店
     * area_id : 410104
     * qty_of_charger : 2
     * id : 1
     * available_qty_of_charger : 2
     * charger_type : 智能充电
     * name : 银河国际酒店
     * lat : 34.745939
     * lng : 113.741550
     */

    private int has_park_fee;
    private String address;
    private int area_id;
    private int qty_of_charger;
    private int id;
    private int available_qty_of_charger;
    private String charger_type;
    private String name;
    private String lat;
    private String lng;
    private int is_favorite;
    private float distance;

    public float getDistance() {
        return distance;
    }

    public int getIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(int is_favorite) {
        this.is_favorite = is_favorite;
    }

    public int getHas_park_fee() {
        return has_park_fee;
    }

    public void setHas_park_fee(int has_park_fee) {
        this.has_park_fee = has_park_fee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public int getQty_of_charger() {
        return qty_of_charger;
    }

    public void setQty_of_charger(int qty_of_charger) {
        this.qty_of_charger = qty_of_charger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvailable_qty_of_charger() {
        return available_qty_of_charger;
    }

    public void setAvailable_qty_of_charger(int available_qty_of_charger) {
        this.available_qty_of_charger = available_qty_of_charger;
    }

    public String getCharger_type() {
        return charger_type;
    }

    public void setCharger_type(String charger_type) {
        this.charger_type = charger_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
