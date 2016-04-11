package com.baidu.majia.common;

import android.app.Application;

import com.baidu.majia.http.HttpManager;
import com.baidu.majia.utils.FileManager;
import com.baidu.majia.utils.ImageUtil;
import com.baidu.majia.utils.NetworkUtil;
import com.baidu.majia.utils.TextToast;

import org.litepal.LitePalApplication;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpManager.init(this);
        ImageUtil.init(this, FileManager.getHomeDir());
        TextToast.init(this);
        NetworkUtil.init(this);
        // CrashHandler.getInstance().init(this);

        // 初始化数据库
        LitePalApplication.initialize(this);
    }
}
