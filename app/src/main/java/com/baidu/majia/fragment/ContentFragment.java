package com.baidu.majia.fragment;

import android.os.Bundle;

import com.baidu.majia.R;
import com.baidu.majia.base.LazyFragment;

/**
 * Created by xuwt on 2016/4/11.
 */
public class ContentFragment extends LazyFragment{

    public static final String CHANNEL_POS = "channel_pos"; // position
    public static final String CHANNEL_TAG = "channel_tag";
    public static final String CHANNEL_INDEX = "channel_index";

    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    public static ContentFragment getInstance(Bundle bundle) {
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.content_fragment;
    }

    @Override
    public void init() {

        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }

       // 加载数据
    }

    public void refreshData() {

    }
}
