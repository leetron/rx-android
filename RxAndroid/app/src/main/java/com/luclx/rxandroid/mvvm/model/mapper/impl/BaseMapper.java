package com.luclx.rxandroid.mvvm.model.mapper.impl;

/**
 * Created by LucLX on 2/25/17.
 */

public abstract class BaseMapper {
    <C> C checkNull(C c) {
        if (c == null) {
            throw new IllegalArgumentException("Object must not null");
        }
        return c;
    }
}
