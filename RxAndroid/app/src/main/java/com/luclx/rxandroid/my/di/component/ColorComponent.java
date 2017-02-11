package com.luclx.rxandroid.my.di.component;

import com.luclx.rxandroid.my.view.MainActivity;
import com.luclx.rxandroid.my.di.module.ColorModule;
import com.luclx.rxandroid.my.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by LucLX on 11/19/16.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = ColorModule.class)
public interface ColorComponent {
    void inject(MainActivity activity);
}
