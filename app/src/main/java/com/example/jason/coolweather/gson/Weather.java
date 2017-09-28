package com.example.jason.coolweather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/9/27
 * weather实体类无法使用，由于参数的原因，赋值时会报错
 */

public class Weather {

    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggesstion suggesstion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;
}
