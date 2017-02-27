package com.luclx.rxandroid.mvp.model.mapper;

/**
 * Created by lucle on 2/27/17.
 */

public abstract class BaseMapper {
    protected <T> T checkNull(T t) {
        if (t == null) {
            throw new NullPointerException("Object must not null");
        }
        return t;
    }
}
