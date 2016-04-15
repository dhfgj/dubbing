package com.baidu.majia.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.majia.http.HttpManager;
import com.baidu.majia.interfaces.IInit;
import com.baidu.majia.utils.NetworkUtil;
import com.loopj.android.http.TextHttpResponseHandler;

import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


public abstract class BaseFragment extends Fragment implements IInit {

    protected Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        if (layoutId == 0) {
            throw new IllegalStateException("Please specify root layout resource id for " + getClass().getSimpleName());
        } else {
            View parentView = inflater.inflate(layoutId, null);
            ButterKnife.bind(this, parentView);
            init();
            return parentView;
        }
    }

    @Override
    public void onNetworkUnavailable(String url) {

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
                String error = throwable.toString();
                String response=responseString;
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (statusCode == 200) {
                    onDataOK(url, responseString);
                }
            }
        });
    }

    @Override
    public void onDataOK(String url, String data) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
    }
}
