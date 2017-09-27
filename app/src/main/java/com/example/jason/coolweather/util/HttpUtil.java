package com.example.jason.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 创建人：jason.jiang
 * 创建日期：2017/9/27
 */

public class HttpUtil {

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
