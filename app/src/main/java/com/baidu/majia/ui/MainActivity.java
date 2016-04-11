package com.baidu.majia.ui;


import android.support.design.widget.TabLayout;
import android.widget.FrameLayout;

import com.baidu.majia.R;
import com.baidu.majia.base.BaseActivity;
import com.baidu.majia.fragment.RecommendFragment;
import com.baidu.majia.fragment.DiscoverFragment;
import com.baidu.majia.fragment.FocusFragment;
import com.baidu.majia.fragment.MineFragment;
import com.baidu.majia.widgets.TabItemView;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tab_layout)
    TabLayout tableLayout;
    @Bind(R.id.container)
    FrameLayout frameLayout;

    private TabLayout.Tab tab;
    private TabItemView tabItemView;

    public static final int RECOMMEND_TAG = 0;
    public static final int FOCUS_TAG = 1;
    public static final int DISCOVER_TAG = 2;
    public static final int MINE_TAG = 3;

    public static final String RECOMMEND_FRAGMENT_TAG = "recommend";
    public static final String FOCUS_FRAGMENT_TAG = "focus";
    public static final String DISCOVER_FRAGMENT_TAG = "discover";
    public static final String MINE_FRAGMENT_TAG = "mine";

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getContentId() {
        return R.id.container;
    }

    @Override
    public void init() {
        setUpTab();
        focusView(0);
        setFragment(0);
    }

    private void setUpTab() {
        tabItemView = new TabItemView(mContext);
        tabItemView.setContext(R.drawable.home_icon_recommend_normal, R.string.home_recommend_text);
        tab = tableLayout.newTab().setCustomView(tabItemView);
        tab.setTag(RECOMMEND_TAG);
        tableLayout.addTab(tab);

        tabItemView = new TabItemView(mContext);
        tabItemView.setContext(R.drawable.home_icon_focus_normal, R.string.home_focus_text);
        tab = tableLayout.newTab().setCustomView(tabItemView);
        tab.setTag(FOCUS_TAG);
        tableLayout.addTab(tab);

        tabItemView = new TabItemView(mContext);
        tabItemView.setContext(R.drawable.home_icon_discover_normal, R.string.home_discover_text);
        tab = tableLayout.newTab().setCustomView(tabItemView);
        tab.setTag(DISCOVER_TAG);
        tableLayout.addTab(tab);

        tabItemView = new TabItemView(mContext);
        tabItemView.setContext(R.drawable.home_icon_mine_normal, R.string.home_mine_text);
        tab = tableLayout.newTab().setCustomView(tabItemView);
        tab.setTag(MINE_TAG);
        tableLayout.addTab(tab);

        // 设置Tab的选择监听
        tableLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // focusView(tab);
                // TextToast.shortShow("select");
                int index = (int) tab.getTag();
                for (int i = 0; i < tableLayout.getTabCount(); i++) {
                    if (i == index) {
                        focusView(i);
                        setFragment(i);
                    } else {
                        unfocusView(i);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // unfocusView(tab);
                // TextToast.shortShow("unselect");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // TextToast.shortShow("reselect");
            }
        });
    }

    private void focusView(int index) {
        switch (index) {
            case RECOMMEND_TAG:
                ((TabItemView) tableLayout.getTabAt(index).getCustomView()).setImage(R.drawable.home_icon_recommend_checked);
                break;
            case FOCUS_TAG:
                ((TabItemView) tableLayout.getTabAt(index).getCustomView()).setImage(R.drawable.home_icon_focus_checked);
                break;
            case DISCOVER_TAG:
                ((TabItemView) tableLayout.getTabAt(index).getCustomView()).setImage(R.drawable.home_icon_discover_checked);
                break;
            case MINE_TAG:
                ((TabItemView) tableLayout.getTabAt(index).getCustomView()).setImage(R.drawable.home_icon_mine_checked);
                break;
            default:
                break;
        }

    }

    private void unfocusView(int index) {
        switch (index) {
            case RECOMMEND_TAG:
                ((TabItemView) tableLayout.getTabAt(index).getCustomView()).setImage(R.drawable.home_icon_recommend_normal);
                break;
            case FOCUS_TAG:
                ((TabItemView) tableLayout.getTabAt(index).getCustomView()).setImage(R.drawable.home_icon_focus_normal);
                break;
            case DISCOVER_TAG:
                ((TabItemView) tableLayout.getTabAt(index).getCustomView()).setImage(R.drawable.home_icon_discover_normal);
                break;
            case MINE_TAG:
                ((TabItemView) tableLayout.getTabAt(index).getCustomView()).setImage(R.drawable.home_icon_mine_normal);
                break;
            default:
                break;
        }

    }

    private void setFragment(int index) {
        switch (index) {
            case RECOMMEND_TAG:
                replaceFragment(RecommendFragment.class, RECOMMEND_FRAGMENT_TAG);
                break;
            case FOCUS_TAG:
                replaceFragment(FocusFragment.class, FOCUS_FRAGMENT_TAG);
                break;
            case DISCOVER_TAG:
                replaceFragment(DiscoverFragment.class, DISCOVER_FRAGMENT_TAG);
                break;
            case MINE_TAG:
                replaceFragment(MineFragment.class, MINE_FRAGMENT_TAG);
                break;
            default:
                break;
        }
    }

}
