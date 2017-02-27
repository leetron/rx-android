package com.luclx.rxandroid.mvp.view;

import com.luclx.rxandroid.data.entity.Color;

import java.util.List;

/**
 * Created by LucLX on 2/12/17.
 */

public interface MainView extends BaseView {
    void onColorLoaded(List<Color> colors);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearItems();
}
