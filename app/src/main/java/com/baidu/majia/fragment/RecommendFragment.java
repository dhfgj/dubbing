package com.baidu.majia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.majia.R;
import com.baidu.majia.base.BaseFragment;
import com.baidu.majia.model.ChannelEntity;
import com.baidu.majia.widgets.PagerSlidingTabStrip;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by xuwt on 2016/4/11.
 */
public class RecommendFragment extends BaseFragment{

    @Bind(R.id.index_viewpager)
    ViewPager mViewPager;
    @Bind(R.id.index_tabs)
    PagerSlidingTabStrip mPagerSlidingTabStrip;

    private RecommendPagerAdapter mPagerAdapter;
    private List<ChannelEntity> mChannels;
    private ArrayList<ContentFragment> mFragments;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void init() {
        // 本地数据库查询
        mChannels = DataSupport.findAll(ChannelEntity.class);
        if (mChannels == null) {
            mChannels = new ArrayList<ChannelEntity>();
        }
        if (mChannels.size() == 0) {
            // 自己初始化一份
            mChannels.add(new ChannelEntity("hot" , "热门"));
            mChannels.add(new ChannelEntity("top" , "头条"));
            mChannels.add(new ChannelEntity("egao" , "恶搞"));
            mChannels.add(new ChannelEntity("yuanchuagn" , "原创"));
            mChannels.add(new ChannelEntity("mofang" , "模仿"));
            mChannels.add(new ChannelEntity("life" , "生活"));
            mChannels.add(new ChannelEntity("db" , "逗比"));
        }

        initFragment();

    }

    private void initFragment() {


        mPagerSlidingTabStrip.setVisibility(View.VISIBLE);
        mFragments = new ArrayList<ContentFragment>();
        int count = mChannels.size();

        for (int i = 0; i < count; i++) {
            Bundle data = new Bundle();
            data.putString(ContentFragment.CHANNEL_POS, mChannels.get(i).getName());
            data.putString(ContentFragment.CHANNEL_TAG, mChannels.get(i).getTag());
            data.putInt(ContentFragment.CHANNEL_INDEX, i);
            mFragments.add(ContentFragment.getInstance(data));
        }

        mPagerAdapter = new RecommendPagerAdapter(getFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mPagerSlidingTabStrip.setOnTabClickListener(new PagerSlidingTabStrip.OnTabClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (mViewPager.getCurrentItem() == position) {
                    mFragments.get(position).refreshData();
                }

            }

        });

    }


    private class RecommendPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<ContentFragment> mFragments;
        private FragmentManager fm;

        public RecommendPagerAdapter(FragmentManager fm, ArrayList<ContentFragment> fragments) {
            super(fm);
            this.fm = fm;
            this.mFragments = fragments;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return mChannels.get(position).getName();
        }
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            Object obj = super.instantiateItem(container, position);
            return obj;
        }
    }

}
