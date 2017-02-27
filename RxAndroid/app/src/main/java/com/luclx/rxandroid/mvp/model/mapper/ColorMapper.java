package com.luclx.rxandroid.mvp.model.mapper;

import java.util.List;

/**
 * Created by lucle on 2/27/17.
 */

public interface ColorMapper<F, T> {
    T transform(F f);

    List<T> transform(List<F> list);
}
