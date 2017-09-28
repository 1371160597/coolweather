package com.example.jason.coolweather.gson;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/9/28
 * weathers 实体类可以使用，正在使用的类型
 */

public class Weathers {

    public Aqi aqi;
    public Basic basic;
    public Now now;
    public String status;
    public Suggestion suggestion;
    public List<DailyForecast> daily_forecast;
    public List<HourlyForecast> hourly_forecast;

    public static class Aqi {
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
    }

    public static class Basic {
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
    }

    public static class Now {
        /**
         * cond : {"code":"104","txt":"阴"}
         * fl : 21
         * hum : 50
         * pcpn : 0
         * pres : 1017
         * tmp : 22
         * vis : 5
         * wind : {"deg":"48","dir":"东北风","sc":"微风","spd":"10"}
         */

        public Cond cond;
        public String fl;
        public String hum;
        public String pcpn;
        public String pres;
        public String tmp;
        public String vis;
        public Wind wind;

        public static class Cond {
            /**
             * code : 104
             * txt : 阴
             */

            public String code;
            public String txt;
        }

        public static class Wind {
            /**
             * deg : 48
             * dir : 东北风
             * sc : 微风
             * spd : 10
             */

            public String deg;
            public String dir;
            public String sc;
            public String spd;
        }
    }

    public static class Suggestion {
        /**
         * air : {"brf":"良","txt":"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。"}
         * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drsg : {"brf":"舒适","txt":"建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"}
         * sport : {"brf":"较适宜","txt":"天气较好，但因风力稍强，户外可选择对风力要求不高的运动，推荐您进行室内运动。"}
         * trav : {"brf":"适宜","txt":"天气较好，温度适宜，但风稍微有点大。这样的天气适宜旅游，您可以尽情地享受大自然的无限风光。"}
         * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
         */

        public Air air;
        public Comf comf;
        public Cw cw;
        public Drsg drsg;
        public Flu flu;
        public Sport sport;
        public Trav trav;
        public Uv uv;

        public static class Air {
            /**
             * brf : 良
             * txt : 气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。
             */

            public String brf;
            public String txt;
        }

        public static class Comf {
            /**
             * brf : 舒适
             * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
             */

            public String brf;
            public String txt;
        }

        public static class Cw {
            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */

            public String brf;
            public String txt;
        }

        public static class Drsg {
            /**
             * brf : 舒适
             * txt : 建议着长袖T恤、衬衫加单裤等服装。年老体弱者宜着针织长袖衬衫、马甲和长裤。
             */

            public String brf;
            public String txt;
        }

        public static class Flu {
            /**
             * brf : 少发
             * txt : 各项气象条件适宜，无明显降温过程，发生感冒机率较低。
             */

            public String brf;
            public String txt;
        }

        public static class Sport {
            /**
             * brf : 较适宜
             * txt : 天气较好，但因风力稍强，户外可选择对风力要求不高的运动，推荐您进行室内运动。
             */

            public String brf;
            public String txt;
        }

        public static class Trav {
            /**
             * brf : 适宜
             * txt : 天气较好，温度适宜，但风稍微有点大。这样的天气适宜旅游，您可以尽情地享受大自然的无限风光。
             */

            public String brf;
            public String txt;
        }

        public static class Uv {
            /**
             * brf : 弱
             * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
             */

            public String brf;
            public String txt;
        }
    }

    public static class DailyForecast {
        /**
         * astro : {"mr":"12:54","ms":"23:31","sr":"05:58","ss":"17:55"}
         * cond : {"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"}
         * date : 2017-09-28
         * hum : 60
         * pcpn : 4.3
         * pop : 98
         * pres : 1016
         * tmp : {"max":"25","min":"17"}
         * uv : 7
         * vis : 19
         * wind : {"deg":"57","dir":"东北风","sc":"微风","spd":"11"}
         */

        public Astro astro;
        public CondX cond;
        public String date;
        public String hum;
        public String pcpn;
        public String pop;
        public String pres;
        public Tmp tmp;
        public String uv;
        public String vis;
        public WindX wind;

        public static class Astro {
            /**
             * mr : 12:54
             * ms : 23:31
             * sr : 05:58
             * ss : 17:55
             */

            public String mr;
            public String ms;
            public String sr;
            public String ss;
        }

        public static class CondX {
            /**
             * code_d : 101
             * code_n : 104
             * txt_d : 多云
             * txt_n : 阴
             */

            public String code_d;
            public String code_n;
            public String txt_d;
            public String txt_n;
        }

        public static class Tmp {
            /**
             * max : 25
             * min : 17
             */

            public String max;
            public String min;
        }

        public static class WindX {
            /**
             * deg : 57
             * dir : 东北风
             * sc : 微风
             * spd : 11
             */

            public String deg;
            public String dir;
            public String sc;
            public String spd;
        }
    }

    public static class HourlyForecast {
        /**
         * cond : {"code":"103","txt":"晴间多云"}
         * date : 2017-09-28 13:00
         * hum : 45
         * pop : 0
         * pres : 1016
         * tmp : 23
         * wind : {"deg":"56","dir":"东北风","sc":"3-4","spd":"17"}
         */

        public CondXX cond;
        public String date;
        public String hum;
        public String pop;
        public String pres;
        public String tmp;
        public WindXX wind;

        public static class CondXX {
            /**
             * code : 103
             * txt : 晴间多云
             */

            public String code;
            public String txt;
        }

        public static class WindXX {
            /**
             * deg : 56
             * dir : 东北风
             * sc : 3-4
             * spd : 17
             */

            public String deg;
            public String dir;
            public String sc;
            public String spd;
        }
    }
}
