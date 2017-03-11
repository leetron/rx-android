package com.luclx.rxandroid.mvp.view.interfaces;

import android.os.Bundle;
import android.view.View;

/**
 * Created by LucLX on 3/6/17.
 */

public interface FragmentLifeCycle {
    int getLayoutId();
    void initView(View view, Bundle bundle);
    void initComponent();
}
