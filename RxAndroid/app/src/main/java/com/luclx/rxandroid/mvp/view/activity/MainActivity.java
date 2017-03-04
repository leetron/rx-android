package com.luclx.rxandroid.mvp.view.activity;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.luclx.rxandroid.R;
import com.luclx.rxandroid.application.BaseApplication;
import com.luclx.rxandroid.di.component.DaggerColorComponent;
import com.luclx.rxandroid.di.module.ColorModule;
import com.luclx.rxandroid.mvp.model.Color;
import com.luclx.rxandroid.mvp.presenter.MainPresenter;
import com.luclx.rxandroid.mvp.view.MainView;
import com.luclx.rxandroid.mvp.view.adapter.ColorAdapter;
import com.luclx.rxandroid.mvp.view.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @Inject
    SharedPreferences sharedPreferences;

    @Bind(R.id.color_list)
    RecyclerView mColorList;

    ColorAdapter colorAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady() {
        super.onViewReady();
        loadColors();
        sharedPreferences.edit().putString("text1", "Hello world!").commit();
        Toast.makeText(MainActivity.this, sharedPreferences.getString("text1", "NO EXIST"), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void initDaggerDependency() {
        DaggerColorComponent
                .builder()
                .applicationComponent(BaseApplication.getInstance().getApplicationComponent())
                .colorModule(new ColorModule(MainActivity.this))
                .build()
                .inject(MainActivity.this);
    }

    @Override
    protected void initSpecialView() {
        super.initSpecialView();
        mColorList.setLayoutManager(new LinearLayoutManager(this));
        mColorList.setHasFixedSize(true);
        colorAdapter = new ColorAdapter();
        mColorList.setAdapter(colorAdapter);
    }

    private void loadColors() {
        mainPresenter.getColor();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.doUnsubscribe();
    }

    @Override
    public void onColorLoaded(List<Color> colors) {
        colorAdapter.setMyColorList(colors);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {
        colorAdapter.clearColors();
    }
}
