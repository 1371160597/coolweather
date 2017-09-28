package com.example.jason.coolweather.gson;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/9/27
 */

public class Basic {
    /**
     * city : 浦口
     * cnty : 中国
     * id : CN101190107
     * lat : 32.05839157
     * lon : 118.62530518
     * update : {"loc":"2017-09-28 10:46","utc":"2017-09-28 02:46"}
     */

    public String city;
    public String cnty;
    public String id;
    public String lat;
    public String lon;
    public Update update;

    public static class Update {
        /**
         * loc : 2017-09-28 10:46
         * utc : 2017-09-28 02:46
         */

        public String loc;
        public String utc;
    }

//    @SerializedName("city")
//    public String cityName;
//
//    @SerializedName("id")
//    public int weatherId;
//
//    public Update update;
//
//    public class Update {
//
//        @SerializedName("loc")
//        public String updateTime;
//    }
}
