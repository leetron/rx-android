package com.luclx.rxandroid.data.service;

import com.luclx.rxandroid.data.entity.Color;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by lucle on 2/27/17.
 */

public interface ColorService {
    Observable<List<Color>> getColorList();
}
