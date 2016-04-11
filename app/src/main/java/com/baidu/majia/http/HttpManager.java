package com.baidu.majia.http;

import android.content.Context;
import android.os.Build;

import com.baidu.majia.utils.SysUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;


public class HttpManager {

    private static Context context;
    private static AsyncHttpClient asyncHttpClient;

    public static void init(Context ctx) {
        context = ctx;
        asyncHttpClient = new AsyncHttpClient();
        //below are not required
        asyncHttpClient.addHeader("x-api-version", "3");
        asyncHttpClient.addHeader("x-app-version", "2.5.4");
        asyncHttpClient.addHeader("x-device", SysUtil.getPhoneModel());
        asyncHttpClient.addHeader("x-os", "Android " + Build.VERSION.RELEASE);
        asyncHttpClient.addHeader("za", "OS=Android " + Build.VERSION.RELEASE + "&Platform=" + SysUtil.getPhoneModel());
    }

    /**
     * GET request
     *
     * @param url                     server interface
     * @param textHttpResponseHandler
     */
    public static void get(String url, TextHttpResponseHandler textHttpResponseHandler) {
        //        asyncHttpClient.addHeader("Authorization","");
        asyncHttpClient.get(context, url, textHttpResponseHandler);
    }

    /**
     * POST request
     *
     * @param url                     server interface
     * @param param                   param in json format
     * @param textHttpResponseHandler
     */
    public static void postJson(String url, String param, TextHttpResponseHandler textHttpResponseHandler) {
        try {
            asyncHttpClient.post(context, url, new StringEntity(param), "application/json; charset=UTF-8", textHttpResponseHandler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * download file
     *
     * @param url
     * @param handler
     */
    public static void downloadFile(String url, BinaryHttpResponseHandler handler) {
        asyncHttpClient.get(context, url, handler);
    }
}
