package com.luclx.rxandroid.my.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.luclx.rxandroid.R;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;

/**
 * Created by LucLX on 12/25/16.
 * RxJava1 Example
 */

public class RXActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        onRXFrom();
        onRXJust();
        onRXCreate();
        onRXDefer();
        testGC();
    }

    private void testGC() {
        Something strong = new Something();
        Something anotherStrong = strong;
        strong = null;
        anotherStrong = null;
        System.gc();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("Mọi thứ kết thúc");
            }
        }, 3000);
    }

    private void onRXDefer() {
        Observer<String> mObserver = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e("LUC", "defer().onCompleted()");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("LUC", "defer().onNext() " + s);
            }
        };

        final String myName = "Nguyen Van A";
        Observable<String> stringObservable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just(myName);
            }
        });

        stringObservable.subscribe(mObserver);
    }

    private void onRXFrom() {
        Observer mSubscriber = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.e("LUC", "from().onCompleted()");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e("LUC", "from().onNext() " + integer.intValue());
            }
        };
        List<Integer> list = Arrays.asList(1, 2, 4, 5);
        Observable<Integer> listObservable = Observable.from(list);
        listObservable.subscribe(mSubscriber);

        // wrong because Observer not is Subscriber
//        listObservable.unsafeSubscribe(mSubscriber);
    }

    private void onRXJust() {
        Subscriber mSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e("LUC", "just().onCompleted() ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String str) {
                Log.e("LUC", "just().onNext() " + str);
            }
        };

//        no need
//        List<String> listStr = Arrays.asList("AAA", "BBB", "CCC");
        Observable<String> listStringObservable = Observable.just("AAA", "BBB", "CCC");
        listStringObservable.subscribe(mSubscriber);
        listStringObservable.unsafeSubscribe(mSubscriber);
    }

    private void onRXCreate() {
        Subscriber mSubscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.e("LUC", "create().onCompleted() ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("LUC", "create().onError() " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("LUC", "create().onNext() " + integer.intValue());
            }
        };

        final List<Integer> list = Arrays.asList(1, 2, 4, 5);
        Observable<Integer> liIntegerObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(list.get(1));
                subscriber.onNext(16);
                subscriber.onNext(22);
//                subscriber.onError(new Throwable());
                subscriber.onCompleted();
            }
        });

        liIntegerObservable.subscribe(mSubscriber);
    }

    public static class Something {
        protected void finalize() {
            System.out.println("Đây là lời của tôi trước khi vĩnh biệt");
        }
    }
}
