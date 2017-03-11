package com.luclx.rxandroid.mvp.view.activity;

import com.luclx.rxandroid.R;
import com.luclx.rxandroid.mvp.view.base.AbstractActivity;
import com.luclx.rxandroid.mvp.view.fragment.FragmentMain;

public class MainActivity extends AbstractActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        mToolbar.setTitle("Ahihi");
        FragmentMain fragmentMain = new FragmentMain();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contain_layout, fragmentMain)
                .commit();
    }

    @Override
    protected void initDaggerDependency() {
    }

    @Override
    protected void initSpecialView() {
        super.initSpecialView();
    }
}
