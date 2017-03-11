package com.luclx.rxandroid.mvp.view.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.luclx.rxandroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LucLX on 1/12/17.
 */

public abstract class AbstractActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    //for child override
    protected abstract int getContentView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ViewGroup replaceLayout = (ViewGroup) findViewById(R.id.content);
        getLayoutInflater().inflate(getContentView(), replaceLayout, true);
        ButterKnife.bind(this);
        initSpecialView();
        onViewReady();
    }

    @CallSuper
    protected void onViewReady() {
        initDaggerDependency();
    }

    protected void initSpecialView() {

    }

    //for child override
    protected void initDaggerDependency() {
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }
}
