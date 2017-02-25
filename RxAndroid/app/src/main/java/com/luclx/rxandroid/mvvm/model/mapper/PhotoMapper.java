package com.luclx.rxandroid.mvvm.model.mapper;

import java.util.List;

/**
 * Created by LucLX on 2/25/17.
 */

public interface PhotoMapper<F, T> {
    T transform(F f);

    List<T> transform(List<F> list);
}
