package com.luclx.rxandroid.mvp.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.luclx.rxandroid.R;
import com.luclx.rxandroid.application.BaseApplication;
import com.luclx.rxandroid.di.component.DaggerColorComponent;
import com.luclx.rxandroid.di.module.ColorModule;
import com.luclx.rxandroid.mvp.model.Color;
import com.luclx.rxandroid.mvp.presenter.MainPresenter;
import com.luclx.rxandroid.mvp.view.adapter.ColorAdapter;
import com.luclx.rxandroid.mvp.view.base.AbstractFragment;
import com.luclx.rxandroid.mvp.view.interfaces.MainView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by LucLX on 3/6/17.
 */

public class FragmentMain extends AbstractFragment implements MainView {
    @Inject
    MainPresenter mainPresenter;

    @Inject
    SharedPreferences sharedPreferences;

    @Bind(R.id.color_list)
    RecyclerView mColorList;

    ColorAdapter colorAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view, Bundle bundle) {
        mColorList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mColorList.setHasFixedSize(true);
        colorAdapter = new ColorAdapter();
        mColorList.setAdapter(colorAdapter);
    }

    @Override
    public void initComponent() {
        DaggerColorComponent
                .builder()
                .applicationComponent(BaseApplication.getInstance().getApplicationComponent())
                .colorModule(new ColorModule(FragmentMain.this))
                .build().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadColors();
        sharedPreferences.edit().putString("text1", "Hello world!").commit();
        Toast.makeText(getActivity(), sharedPreferences.getString("text1", "NO EXIST"), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
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
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClearItems() {
        colorAdapter.clearColors();
    }

    private void loadColors() {
        mainPresenter.getColor();
    }
}
