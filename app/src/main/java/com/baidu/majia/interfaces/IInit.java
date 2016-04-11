package com.baidu.majia.interfaces;


public interface IInit {
    /**
     * get layout resource id
     * @return layout resource id
     */
    int getLayoutId();

    /**
     * initialize
     */
    void init();

    /**
     * get data from server successfully
     * @param url interface url
     * @param data data
     */
    void onDataOK(String url, String data);

    /**
     * network unavailable
     * @param url
     */
    void onNetworkUnavailable(String url);
}
