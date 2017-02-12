package com.luclx.rxandroid.mvp.presenter;

import com.luclx.rxandroid.api.ColorApiService;
import com.luclx.rxandroid.mvp.model.ColorResponse;
import com.luclx.rxandroid.mvp.model.MyColor;
import com.luclx.rxandroid.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;

/**
 * Created by LucLX on 2/12/17.
 */

public class MainPresenter extends BasePresenter<MainView> implements Observer<ColorResponse> {
//    @Inject
    ColorApiService apiService;

    @Inject
    public MainPresenter(ColorApiService apiService) {
        this.apiService = apiService;
    }

    public void getColor() {
        getView().onShowDialog("Loading ......");
        Observable<ColorResponse> colorResponseObserver = this.apiService.getMyColorRx();
        subscribe(colorResponseObserver, this);
    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Loading completed!");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Loading Error");
    }

    @Override
    public void onNext(ColorResponse colorResponse) {
        List<MyColor> colors = colorResponse.getmMyColorLst();
        getView().onClearItems();
        getView().onColorLoaded(colors);
    }
}
