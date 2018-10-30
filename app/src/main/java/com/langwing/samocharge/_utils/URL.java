package com.langwing.samocharge._utils;

/**
 * To Change The World
 * 2017-01-10 15:52
 * Created by Mr.Wang
 */

public class URL {
    public static final String API = "https://samo-app-api.woa-cloud.com";//测试
//    public static final String API = "http://app-api.chinasamo.com";//正式

    public static final String me = API + "/me";
    public static final String GET_AUTH_CODE = API + "/auth/mobile-verify";


    //    ---------------------------------------------------------------------待删除
    public static final String REGISTER = API + "/auth/register";
    public static final String LOGIN = API + "/auth/login";
    public static final String GET_STATION_LIST = API + "/station";
    public static final String APPLY_PARTNER_PARAMETERS = API + "/business-partner/apply/parameters";
    public static final String PARTNER_PROTOCOL = API + "/business-partner/get-agreement";
    public static final String LINE_CHAT = API + "/page/bp-lines";
}
