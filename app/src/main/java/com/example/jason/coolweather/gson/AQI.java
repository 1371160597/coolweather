package com.example.jason.coolweather.gson;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/9/27
 */

public class AQI {
    /**
     * city : {"aqi":"25","pm10":"25","pm25":"11","qlty":"优"}
     */

    public City city;

    public static class City {
        /**
         * aqi : 25
         * pm10 : 25
         * pm25 : 11
         * qlty : 优
         */

        public String aqi;
        public String pm10;
        public String pm25;
        public String qlty;
    }


//    @SerializedName("city")
//    public AQICity aqiCity;
//
//    public class AQICity {
//
//        @SerializedName("api")
//        public String AirQualityIndex;//空气污染指数
//
//        @SerializedName("pm25")
//        public String ParticulateMatter;//细颗粒物
//
//        @SerializedName("qlty")
//        public String Quality;//空气质量
//    }


}
