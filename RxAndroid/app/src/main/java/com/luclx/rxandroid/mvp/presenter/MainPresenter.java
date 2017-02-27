package com.luclx.rxandroid.mvp.presenter;

import com.luclx.rxandroid.data.net.API;
import com.luclx.rxandroid.data.entity.Color;
import com.luclx.rxandroid.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by LucLX on 2/12/17.
 */

public class MainPresenter extends BasePresenter<MainView> implements Observer<List<Color>> {
    //    @Inject
    API apiService;

    @Inject
    public MainPresenter(API apiService) {
        this.apiService = apiService;
    }

    public void getColor() {
        getView().onShowDialog("Loading ......");
        Observable<List<Color>> colorResponseObserver = this.apiService.getMyColorRx();
        subscribe(colorResponseObserver, this);
    }

    @Override
    public void onComplete() {
        getView().onHideDialog();
        getView().onShowToast("Loading completed!");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Loading Error");
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(List<Color> colors) {
        getView().onClearItems();
        getView().onColorLoaded(colors);
    }
}
