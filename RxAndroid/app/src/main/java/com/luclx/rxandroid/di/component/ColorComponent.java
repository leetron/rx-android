package com.luclx.rxandroid.di.component;

import com.luclx.rxandroid.di.module.ColorModule;
import com.luclx.rxandroid.di.scope.ActivityScope;
import com.luclx.rxandroid.mvp.view.fragment.FragmentMain;

import dagger.Component;

/**
 * Created by LucLX on 11/19/16.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class
        , modules = ColorModule.class)
public interface ColorComponent {
    void inject(FragmentMain fragmentMain);
}
