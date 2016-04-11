package com.baidu.majia.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.baidu.majia.http.HttpManager;
import com.baidu.majia.interfaces.IDownloadListener;
import com.baidu.majia.interfaces.IInit;
import com.baidu.majia.utils.NetworkUtil;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


public abstract class BaseActivity extends AppCompatActivity implements IInit {

    private ProgressDialog progressDialog;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        int layout = getLayoutId();
        if (layout == 0) {
            throw new IllegalStateException("Please specify root layout resource id for " + getClass().getSimpleName());
        } else {
            setContentView(layout);
            ButterKnife.bind(this);
            init();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                progressDialog=new ProgressDialog(this,android.R.style.Theme_Material_Light_Dialog_Alert);
            }else {
                progressDialog=new ProgressDialog(this,ProgressDialog.THEME_HOLO_LIGHT);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onDataOK(String url, String data) {

    }

    @Override
    public void onNetworkUnavailable(String url) {

    }

    public void showProgress(){
        if (progressDialog!=null&&!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    public void closeProgress(){
        if (progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
    /**
     * GET请求
     *
     * @param url
     */
    public void get(final String url) {
        if (!NetworkUtil.getInstance().checkNetworkAvailable()) {
            onNetworkUnavailable(url);
            return;
        }
        HttpManager.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (statusCode == 200) {
                    onDataOK(url, responseString);
                }
            }
        });
    }

    public void downloadImage(String url, final String file, final IDownloadListener listener) {
        listener.onStart();
        //using https may cause exceptions,convert to http
        if (url.startsWith("https")){
            url=url.replace("https","http");
        }
        HttpManager.downloadFile(url, new BinaryHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                        if (statusCode == 200 && binaryData != null && binaryData.length > 0) {
                            boolean success = saveImg(binaryData, file);
                            if (success) {
                                listener.onFinish();
                            } else {
                                listener.onFail();
                            }
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                        listener.onFail();
                    }
                }

        );
    }

    private boolean saveImg(byte[] binaryData, String savePath) {
        Bitmap bmp = BitmapFactory.decodeByteArray(binaryData, 0,
                binaryData.length);
        File file = new File(savePath);
        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
        int quality = 100;
        try {
            if (file.createNewFile()) {
                OutputStream stream = new FileOutputStream(file);
                bmp.compress(format, quality, stream);
                stream.close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    protected <T extends BaseFragment> T replaceFragment(Class<T> cls, String tag) {
        BaseFragment fragment = null;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BaseFragment page = (BaseFragment) fm.findFragmentByTag(tag);
        if (page != null) {
            fragment = page;
        } else {
            try {
                fragment = cls.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (fragment == null) {
                return null;
            }
        }
        ft.replace(getContentId(), fragment, tag);
        // ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
        return (T) fragment;
    }

    protected abstract int getContentId();

}
