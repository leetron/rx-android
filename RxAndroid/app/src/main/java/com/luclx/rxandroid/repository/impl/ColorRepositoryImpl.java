package com.luclx.rxandroid.repository.impl;

import com.luclx.rxandroid.data.service.ColorService;
import com.luclx.rxandroid.mvp.model.Color;
import com.luclx.rxandroid.mvp.model.mapper.ColorMapper;
import com.luclx.rxandroid.repository.ColorRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by lucle on 2/27/17.
 */

public class ColorRepositoryImpl
        implements ColorRepository {

    private final ColorService mService;
    private final ColorMapper<com.luclx.rxandroid.data.entity.Color, Color> mMapper;
    private List<Color> mColorList;

    @Inject
    public ColorRepositoryImpl(ColorService mService, ColorMapper mMapper) {
        this.mService = mService;
        this.mMapper = mMapper;
    }

    @Override
    public Observable<List<Color>> getColorList() {
        return Observable.concat(mColorList != null ? Observable.just(mColorList) : Observable.empty(),
                mService.getColorList()
                        .map(mMapper::transform)
                        .doOnNext(colors -> mColorList = colors));
    }
}
