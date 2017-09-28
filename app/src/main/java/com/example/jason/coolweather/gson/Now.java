package com.example.jason.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/9/27
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    public Cond cond;

    public class Cond{

        @SerializedName("txt")
        public String info;
    }
}
