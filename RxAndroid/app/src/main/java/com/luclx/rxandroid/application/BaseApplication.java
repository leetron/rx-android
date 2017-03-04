package com.luclx.rxandroid.application;

import android.app.Application;

import com.luclx.rxandroid.di.component.ApplicationComponent;
import com.luclx.rxandroid.di.component.DaggerApplicationComponent;
import com.luclx.rxandroid.di.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by LucLX on 11/19/16.
 * <p>
 * map()
 * tre ve mot object kieu <T>
 * la mot Operator dung de chuyen doi data trong mapper
 * co the co nhieu map trong 1 Observable
 * trinh tu thuc hien map tu tren xuong duoi
 * VD: PhotoReponsitoryImpl
 * https://viblo.asia/haint.fit12/posts/mrDkMrpzvzL
 * <p>
 * flatMap()
 * thay the duoc cho map() va linh hoat hon map
 * tra ve mot Observable<T>
 * VD: PhotoServiceImple
 */
/**
 * map()
 * tre ve mot object kieu <T>
 * la mot Operator dung de chuyen doi data trong mapper
 * co the co nhieu map trong 1 Observable
 * trinh tu thuc hien map tu tren xuong duoi
 * VD: PhotoReponsitoryImpl
 * https://viblo.asia/haint.fit12/posts/mrDkMrpzvzL
 */

/**
 * flatMap()
 * thay the duoc cho map() va linh hoat hon map
 * tra ve mot Observable<T>
 * VD: PhotoServiceImple
 */

/**
 * concat
 * noi cac item phat ra cua 2 Observable ma khong dan xen
 * VD: {a} phat ra {1,7}, {b} phat ra {9,2}
 * concat(a,b) => phat ra {1,7,9,2}
 * VD: PhotoReponsitoryImpl
 * http://reactivex.io/documentation/operators/concat.html
 */

public class BaseApplication extends Application {

    private ApplicationComponent applicationComponent;


    private static BaseApplication mInstance;

    private synchronized void initInstance() {
        mInstance = this;
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initInstance();
        LeakCanary.install(this);
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(BaseApplication.getInstance()))
                .build();

    }
}
