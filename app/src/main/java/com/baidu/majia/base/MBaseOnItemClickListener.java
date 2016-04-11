package com.baidu.majia.base;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class MBaseOnItemClickListener<T> implements OnClickListener {

    private T data;

    public MBaseOnItemClickListener(T data) {
        this.data = data;
    }

    @Override
    public void onClick(View v) {
        onClick(v, data);
    }

    public abstract void onClick(View view, T data);
}
