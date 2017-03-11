package com.luclx.rxandroid.mvp.view.interfaces;

import com.luclx.rxandroid.mvp.model.Color;
import com.luclx.rxandroid.mvp.view.base.BaseView;

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
