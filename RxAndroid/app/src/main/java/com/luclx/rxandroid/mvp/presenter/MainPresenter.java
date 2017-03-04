package com.luclx.rxandroid.mvp.presenter;

import com.luclx.rxandroid.mvp.model.Color;
import com.luclx.rxandroid.mvp.view.MainView;
import com.luclx.rxandroid.usecase.GetColorList;

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
    GetColorList getColorList;
    Observable<List<Color>> colorResponseObserver;

    @Inject
    public MainPresenter(GetColorList getColorList) {
        this.getColorList = getColorList;
    }

    public void getColor() {
        getView().onShowDialog("Loading ......");
        colorResponseObserver = this.getColorList.getColors();
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

    public void doUnsubscribe() {
        colorResponseObserver.doOnDispose(() -> {
        });
    }
}
