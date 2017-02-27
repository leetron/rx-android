package com.luclx.rxandroid.repository;


import com.luclx.rxandroid.mvp.model.Color;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lucle on 2/27/17.
 */

public interface ColorRepository {
    Observable<List<Color>> getColorList();
}
