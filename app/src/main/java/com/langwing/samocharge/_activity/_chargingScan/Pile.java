package com.langwing.samocharge._activity._chargingScan;

import java.io.Serializable;

/**
 * To Change The World
 * 2018-04-10 15:06
 * Created by Mr.Wang
 */
public class Pile implements Serializable {
    private static final long serialVersionUID = 2868583788321408244L;


    /**
     * type : charger
     * info : {"station":{"id":1,"name":"银河国际酒店","lng":"113.741550","lat":"34.745939","address":"郑州市管城回族区商都路与七里河南路交汇处","qty_of_charger":64,"has_park_fee":0,"charger_type":"智能充电","area_id":410104,"available_qty_of_charger":64,"price_kwh":1.5},"charger":{"id":1,"is_connected":0,"type":"智能充电","code":"03710010001","local_code":0,"token":"2DFA1AD7F906A6CA","status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"}}
     */

    private String type;
    private InfoBean info;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean implements Serializable{
        private static final long serialVersionUID = 4377860977377817177L;
        /**
         * station : {"id":1,"name":"银河国际酒店","lng":"113.741550","lat":"34.745939","address":"郑州市管城回族区商都路与七里河南路交汇处","qty_of_charger":64,"has_park_fee":0,"charger_type":"智能充电","area_id":410104,"available_qty_of_charger":64,"price_kwh":1.5}
         * charger : {"id":1,"is_connected":0,"type":"智能充电","code":"03710010001","local_code":0,"token":"2DFA1AD7F906A6CA","status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"}
         */

        private StationBean station;
        private ChargerBean charger;

        public StationBean getStation() {
            return station;
        }

        public void setStation(StationBean station) {
            this.station = station;
        }

        public ChargerBean getCharger() {
            return charger;
        }

        public void setCharger(ChargerBean charger) {
            this.charger = charger;
        }

        public static class StationBean implements Serializable{
            private static final long serialVersionUID = 2089409628650410162L;
            /**
             * id : 1
             * name : 银河国际酒店
             * lng : 113.741550
             * lat : 34.745939
             * address : 郑州市管城回族区商都路与七里河南路交汇处
             * qty_of_charger : 64
             * has_park_fee : 0
             * charger_type : 智能充电
             * area_id : 410104
             * available_qty_of_charger : 64
             * price_kwh : 1.5
             */

            private int id;
            private String name;
            private String lng;
            private String lat;
            private String address;
            private int qty_of_charger;
            private int has_park_fee;
            private String charger_type;
            private int area_id;
            private int available_qty_of_charger;
            private double price_kwh;

            public int getId() {
                return id;
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

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getQty_of_charger() {
                return qty_of_charger;
            }

            public void setQty_of_charger(int qty_of_charger) {
                this.qty_of_charger = qty_of_charger;
            }

            public int getHas_park_fee() {
                return has_park_fee;
            }

            public void setHas_park_fee(int has_park_fee) {
                this.has_park_fee = has_park_fee;
            }

            public String getCharger_type() {
                return charger_type;
            }

            public void setCharger_type(String charger_type) {
                this.charger_type = charger_type;
            }

            public int getArea_id() {
                return area_id;
            }

            public void setArea_id(int area_id) {
                this.area_id = area_id;
            }

            public int getAvailable_qty_of_charger() {
                return available_qty_of_charger;
            }

            public void setAvailable_qty_of_charger(int available_qty_of_charger) {
                this.available_qty_of_charger = available_qty_of_charger;
            }

            public double getPrice_kwh() {
                return price_kwh;
            }

            public void setPrice_kwh(double price_kwh) {
                this.price_kwh = price_kwh;
            }
        }

        public static class ChargerBean implements Serializable{
            private static final long serialVersionUID = 5671857320429857825L;
            /**
             * id : 1
             * is_connected : 0
             * type : 智能充电
             * code : 03710010001
             * local_code : 0
             * token : 2DFA1AD7F906A6CA
             * status : 0
             * is_online : 0
             * charger_status : 离线
             * connected_status : 未插枪
             */

            private int id;
            private int is_connected;
            private String type;
            private String code;
            private int local_code;
            private String token;
            private int status;
            private int is_online;
            private String charger_status;
            private String connected_status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIs_connected() {
                return is_connected;
            }

            public void setIs_connected(int is_connected) {
                this.is_connected = is_connected;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getLocal_code() {
                return local_code;
            }

            public void setLocal_code(int local_code) {
                this.local_code = local_code;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getIs_online() {
                return is_online;
            }

            public void setIs_online(int is_online) {
                this.is_online = is_online;
            }

            public String getCharger_status() {
                return charger_status;
            }

            public void setCharger_status(String charger_status) {
                this.charger_status = charger_status;
            }

            public String getConnected_status() {
                return connected_status;
            }

            public void setConnected_status(String connected_status) {
                this.connected_status = connected_status;
            }
        }
    }
}
