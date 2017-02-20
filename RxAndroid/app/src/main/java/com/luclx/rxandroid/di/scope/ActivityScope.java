package com.luclx.rxandroid.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


/**
 * Created by LucLX on 2/12/17.
 *
 * @version 1.0.0
 */
@Scope
//@Retention(RetentionPolicy.RUNTIME)
//Annotations are to be recorded in the class file by the compiler and retained
//        by the VM at run time, so they may be read reflectively.
public @interface ActivityScope {
}
