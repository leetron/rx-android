package com.luclx.rxandroid.testRx2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.luclx.rxandroid.R;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LucLX on 02/14/17.
 * RxJava2 Example
 * Please read more about reactive standard: http://reactivex.io/documentation/operators.html
 * Slide Jake Wharton in Gotocon : https://gotocon.com/dl/goto-cph-2016/slides/JakeWharton_ExploringRxJava2ForAndroid.pdf
 * https://github.com/ReactiveX/RxJava
 * https://realm.io/news/gotocph-jake-wharton-exploring-rxjava2-android/
 */

public class RX2Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
//        RXjust();
//        RXfrom();
//        RXcreate();
//        RXdefer();
        testThreading();
    }

    private void RXjust() {
        Observable.just("AAA", "BBB", "CCC")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String value) {
                        Log.e("LUC", "just().onNext() " + value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e("LUC", "just().onCompleted() ");
                    }
                });

    }

    private void RXfrom() {
        int[] array = {1, 2, 3, 4};
        Observable.fromArray(array).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<int[]>() {
                    @Override
                    public void onNext(int[] values) {
                        for (int i = 0; i < values.length; i++) {
                            Log.e("LUC", "from().onNext() " + values[i]);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e("LUC", "from().onCompleted() ");
                    }
                });
    }

    private void RXcreate() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("HIHI");
                emitter.onComplete();
            }
        }).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("LUC", "create().onSubscribe() " + d);
            }

            @Override
            public void onNext(String value) {
                Log.e("LUC", "create().onNext() " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e("LUC", "create().onComplete()");
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void RXdefer() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("LUC", "defer().onSubscribe() " + d);
            }

            @Override
            public void onNext(String s) {
                Log.e("LUC", "defer().onNext() " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e("LUC", "defer().onCompleted()");
            }
        };

        Observable observable = Observable.defer(() -> Observable.just("KAKA"));

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }

    private void testThreading() {
        Observable.defer(() -> {
                        Log.e("Observable thread", Thread.currentThread().getName());
                        return Observable.just(1);
                    })
//                .subscribeOn(Schedulers.newThread())
                .map(integer -> {
                    Log.e("Map thread", Thread.currentThread().getName());
                    return String.valueOf(integer);
                        }
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
//                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        Log.e("Observer thread", Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void testOperator(){
        // map return Object <T>
        // flatMap return Observable<T>
        // filter
        // toList
    }
}
