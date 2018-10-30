package com.langwing.samocharge._activity._selectCity;

import java.io.Serializable;
import java.util.List;

public class City implements Serializable{

    private static final long serialVersionUID = 1939693140597170880L;
    /**
     * id : 410000
     * name : 河南省
     * cities : [{"city_name":"郑州市","city_id":410100},{"city_name":"洛阳市","city_id":410300},{"city_name":"巩义市","city_id":410100},{"city_name":"焦作市","city_id":410800}]
     */
    private int id;
    private String name;
    private List<CitiesBean> cities;

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

    public List<CitiesBean> getCities() {
        return cities;
    }

    public void setCities(List<CitiesBean> cities) {
        this.cities = cities;
    }

    public static class CitiesBean implements Serializable{
        private static final long serialVersionUID = -8361313720722134677L;
        /**
         * city_name : 郑州市
         * city_id : 410100
         */

        private String city_name;
        private int city_id;

        private boolean isFirtCityInProvince;
        private String provinceName;
        private int provinceId;

        public boolean isFirtCityInProvince() {
            return isFirtCityInProvince;
        }

        public void setFirtCityInProvince(boolean firtCityInProvince) {
            isFirtCityInProvince = firtCityInProvince;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }
    }
}
