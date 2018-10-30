package com.langwing.samocharge._activity._chargingStationDetails;

import java.io.Serializable;
import java.util.List;

/**
 * To Change The World
 * 2018-04-09 21:51
 * Created by Mr.Wang
 */
class StationDetails implements Serializable {
    private static final long serialVersionUID = 3658795001210724808L;

    /**
     * station : {"id":32,"name":"山顶福都时代广场","lng":"113.867907","lat":"34.561610","address":"郑州市中牟县巡航路与凌空街交叉口","qty_of_charger":16,"has_park_fee":0,"charger_type":"智能充电","area_id":410122,"available_qty_of_charger":5,"price_kwh":1.5}
     * chargers : [{"id":768,"is_connected":0,"type":"智能充电","code":"03710320081","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":769,"is_connected":0,"type":"智能充电","code":"03710320091","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":770,"is_connected":0,"type":"智能充电","code":"03710320101","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":771,"is_connected":0,"type":"智能充电","code":"03710320111","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":772,"is_connected":0,"type":"智能充电","code":"03710320121","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":773,"is_connected":0,"type":"智能充电","code":"03710320131","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":774,"is_connected":0,"type":"智能充电","code":"03710320141","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":775,"is_connected":0,"type":"智能充电","code":"03710320151","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":776,"is_connected":0,"type":"智能充电","code":"03710320161","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":760,"is_connected":0,"type":"智能充电","code":"03710320001","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":761,"is_connected":0,"type":"智能充电","code":"03710320011","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":762,"is_connected":0,"type":"智能充电","code":"03710320021","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":763,"is_connected":0,"type":"智能充电","code":"03710320031","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":764,"is_connected":0,"type":"智能充电","code":"03710320041","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":765,"is_connected":0,"type":"智能充电","code":"03710320051","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":766,"is_connected":0,"type":"智能充电","code":"03710320061","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"},{"id":767,"is_connected":0,"type":"智能充电","code":"03710320071","local_code":0,"status":0,"is_online":0,"charger_status":"离线","connected_status":"未插枪"}]
     */

    private StationBean station;
    private List<ChargersBean> chargers;

    public StationBean getStation() {
        return station;
    }

    public void setStation(StationBean station) {
        this.station = station;
    }

    public List<ChargersBean> getChargers() {
        return chargers;
    }

    public void setChargers(List<ChargersBean> chargers) {
        this.chargers = chargers;
    }

    public static class StationBean implements Serializable {
        private static final long serialVersionUID = -7358907821186895081L;
        /**
         * id : 32
         * name : 山顶福都时代广场
         * lng : 113.867907
         * lat : 34.561610
         * address : 郑州市中牟县巡航路与凌空街交叉口
         * qty_of_charger : 16
         * has_park_fee : 0
         * charger_type : 智能充电
         * area_id : 410122
         * available_qty_of_charger : 5
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

    public static class ChargersBean implements Serializable {
        private static final long serialVersionUID = -3386342401439461690L;
        /**
         * id : 768
         * is_connected : 0
         * type : 智能充电
         * code : 03710320081
         * local_code : 0
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
