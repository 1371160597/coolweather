package com.example.jason.coolweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jason.coolweather.gson.Weathers;
import com.example.jason.coolweather.service.AutoUpdateService;
import com.example.jason.coolweather.util.HttpUtil;
import com.example.jason.coolweather.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private TextView titleCity;
    private TextView titleUpdateTime;
    private TextView weatherInfo;
    private TextView degree;
    private LinearLayout forecastLayout;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView qualityText;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;
    private ScrollView weatherLayout;
    private ImageView bingPicImage;
    public SwipeRefreshLayout swipeRefresh;
    private String weatherId;
    private Button navButton;
    public DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);

        bingPicImage = (ImageView) findViewById(R.id.bing_pic_image);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        navButton = (Button) findViewById(R.id.nav_button);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdateTime = (TextView) findViewById(R.id.title_update_time);
        weatherInfo = (TextView) findViewById(R.id.weather_info_text);
        degree = (TextView) findViewById(R.id.degree_text);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        aqiText = (TextView) findViewById(R.id.aqi_text);
        pm25Text = (TextView) findViewById(R.id.pm25_text);
        qualityText = (TextView) findViewById(R.id.quality_text);
        comfortText = (TextView) findViewById(R.id.comfort_text);
        carWashText = (TextView) findViewById(R.id.car_wash_text);
        sportText = (TextView) findViewById(R.id.sport_text);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        //有缓存的时候直接解析天气数据
        if (weatherString != null) {
            Weathers weather = Utility.handleWeatherResponse(weatherString);
            weatherId = weather.basic.id;
            showWeatherInfo(weather);
        } else {
            //无缓存时候去查询服务器天气
            weatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }

//        String weatherId = getIntent().getStringExtra("weather_id");
//        weatherLayout.setVisibility(View.INVISIBLE);
//        requestWeather(weatherId);

        //加载图片
        String bingPic = prefs.getString("bing_pic", null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(bingPicImage);
        } else {
            loadBingPic();
        }

        //加载下拉刷新事件
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(weatherId);
            }
        });

        //滑动菜单
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void loadBingPic() {
        String requestBingPicUrl = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPicUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImage);
                    }
                });
            }
        });
    }

    //根据天气id查询城市天气情况
    public void requestWeather(final String weathersId) {
//        http://guolin.tech/api/weather?cityid=CN101190105&key=2f35fe464bfa4655a592f684f99f250b
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weathersId + "&key=2f35fe464bfa4655a592f684f99f250b";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
//                Looper.prepare();
                runOnUiThread(new Runnable() {
                    @Override

                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String responseText = response.body().string();
                final Weathers weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && weather.status.equals("ok")) {
                            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            edit.putString("weather", responseText);
//                            edit.putString("weather", null);
                            edit.apply();
                            weatherId = weather.basic.id;
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });

        loadBingPic();
    }

    //处理并展示Weather实体类中的数据
    private void showWeatherInfo(Weathers weather) {
//        String cityName = weather.basic.cityName;
//        String updateTime = weather.basic.update.updateTime.split(" ")[1];
//        String degrees = weather.now.temperature + "℃";
//        String weatherInfos = weather.now.cond.info;

        String cityName = weather.basic.city;
        String updateTime = weather.basic.update.loc.split(" ")[1];
        String degrees = weather.now.tmp + "℃";
        String weatherInfos = weather.now.cond.txt;

        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degree.setText(degrees);
        weatherInfo.setText(weatherInfos);

        forecastLayout.removeAllViews();
        for (Weathers.DailyForecast forecast : weather.daily_forecast) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView dataText = (TextView) view.findViewById(R.id.data_text);
            TextView weatherText = (TextView) view.findViewById(R.id.weather_text);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            TextView minText = (TextView) view.findViewById(R.id.min_text);
//            dataText.setText(forecast.date);
//            weatherText.setText(forecast.cond.info);
//            maxText.setText(forecast.temperature.max);
//            minText.setText(forecast.temperature.min);
            dataText.setText(forecast.date);
            weatherText.setText(forecast.cond.txt_d);
            maxText.setText(forecast.tmp.max);
            minText.setText(forecast.tmp.min);
            forecastLayout.addView(view);
        }
        if (weather.aqi != null) {
//            aqiText.setText(weather.aqi.aqiCity.AirQualityIndex);
//            pm25Text.setText(weather.aqi.aqiCity.ParticulateMatter);
//            qualityText.setText(weather.aqi.aqiCity.Quality);
            aqiText.setText(weather.aqi.city.aqi);
            pm25Text.setText(weather.aqi.city.pm25);
            qualityText.setText(weather.aqi.city.qlty);
        }
//        String comfort = "舒适度：" + weather.suggesstion.comfort.info;
//        String carWash = "洗车指数：" + weather.suggesstion.carWash.info;
//        String sportTip = "运动建议：" + weather.suggesstion.sport.info;
        String comfort = "舒适度：" + weather.suggestion.comf.txt;
        String carWash = "洗车指数：" + weather.suggestion.cw.txt;
        String sportTip = "运动建议：" + weather.suggestion.sport.txt;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sportTip);
        weatherLayout.setVisibility(View.VISIBLE);

        Intent intent = new Intent(this, AutoUpdateService.class);
        startService(intent);
    }
}
