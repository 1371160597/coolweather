package com.example.jason.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/9/27
 */

public class Suggesstion {

    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;

    public class Comfort{

        @SerializedName("txt")
        public String info;
    }

    public class CarWash{

        @SerializedName("txt")
        public String info;
    }

    public class Sport{

        @SerializedName("txt")
        public String info;
    }
}
