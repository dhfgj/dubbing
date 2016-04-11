package com.baidu.majia.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.majia.R;

/**
 * Created by xuwt on 2016/4/11.
 */
public class TabItemView extends LinearLayout {

    private Context mContext;
    private LayoutInflater mInflater;

    private ImageView mIcon;
    private TextView mText;
    private View mView;


    public TabItemView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public TabItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.tab_item_view , this);
        mIcon = (ImageView) mView.findViewById(R.id.icon);
        mText = (TextView) mView.findViewById(R.id.text);
    }

    public void setContext (int resId , int text) {
        mIcon.setImageResource(resId);
        mText.setText(mContext.getText(text));
    }

    public void setImage(int resId) {
        mIcon.setImageResource(resId);
    }


}
