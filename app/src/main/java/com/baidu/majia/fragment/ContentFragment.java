package com.baidu.majia.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baidu.majia.R;
import com.baidu.majia.base.LazyFragment;
import com.baidu.majia.http.ApiContent;
import com.baidu.majia.model.ContentData;
import com.baidu.majia.model.ContentDataList;
import com.baidu.majia.utils.JsonUtil;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xuwt on 2016/4/11.
 */
public class ContentFragment extends LazyFragment {

    public static final String CHANNEL_POS = "channel_pos"; // position
    public static final String CHANNEL_TAG = "channel_tag";
    public static final String CHANNEL_INDEX = "channel_index";

    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    @Bind(R.id.listview)
    ListView mListView;

    //
    private String tag;
    private int start = 1;
    public static final int count = 10;

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

        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }

        // 加载数据
        refreshData();
    }

    public void refreshData() {
        get(String.format(ApiContent.RECOMMEND_URL, start, count));
    }

    @Override
    public void onDataOK(String url, String data) {
         // = JsonUtil.getEntity(data, ContentDataList.class);
        ContentDataList contentList = JsonUtil.getEntity(data , ContentDataList.class);
        // ContentDataList contentList = new Gson().fromJson(data, new TypeToken<List<ContentData>>() {}.getType());
        if (contentList != null) {
            mListView.setAdapter(new CommonAdapter<ContentData>(mContext , R.layout.recommend_content_list_item, contentList.getVideos()) {
                @Override
                public void convert(ViewHolder holder, ContentData contentData) {

                }
            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
