package com.langwing.samocharge._activity._applyPartner;

import java.io.Serializable;
import java.util.List;

public class ApplyPartnerParameters implements Serializable {

    private static final long serialVersionUID = -1022262718367453369L;
    private List<CivilStateBean> civil_state;
    private List<EduLevelBean> edu_level;
    private List<CareerTypeBean> career_type;
    private List<CareerCategoryBean> career_category;
    private List<ResponsibilityBean> responsibility;

    public List<CivilStateBean> getCivil_state() {
        return civil_state;
    }

    public void setCivil_state(List<CivilStateBean> civil_state) {
        this.civil_state = civil_state;
    }

    public List<EduLevelBean> getEdu_level() {
        return edu_level;
    }

    public void setEdu_level(List<EduLevelBean> edu_level) {
        this.edu_level = edu_level;
    }

    public List<CareerTypeBean> getCareer_type() {
        return career_type;
    }

    public void setCareer_type(List<CareerTypeBean> career_type) {
        this.career_type = career_type;
    }

    public List<CareerCategoryBean> getCareer_category() {
        return career_category;
    }

    public void setCareer_category(List<CareerCategoryBean> career_category) {
        this.career_category = career_category;
    }

    public List<ResponsibilityBean> getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(List<ResponsibilityBean> responsibility) {
        this.responsibility = responsibility;
    }

    public static class CivilStateBean implements Serializable {
        private static final long serialVersionUID = 8069117488850642362L;
        /**
         * id : 0
         * name : 未婚
         */

        private int id;
        private String name;

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
    }

    public static class EduLevelBean implements Serializable {
        private static final long serialVersionUID = -2334107355245631624L;
        /**
         * id : 0
         * name : 小学
         */

        private int id;
        private String name;

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
    }

    public static class CareerTypeBean implements Serializable {
        private static final long serialVersionUID = 5027035131576093882L;
        /**
         * id : 0
         * name : 企业工作人员
         */

        private int id;
        private String name;

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
    }

    public static class CareerCategoryBean implements Serializable {
        private static final long serialVersionUID = 5501046748694305486L;
        /**
         * id : 0
         * name : 教育相关行业
         */

        private int id;
        private String name;

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
    }

    public static class ResponsibilityBean implements Serializable {
        private static final long serialVersionUID = 4354053481274390690L;
        /**
         * id : 0
         * name : 办事人员
         */

        private int id;
        private String name;

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
    }
}
