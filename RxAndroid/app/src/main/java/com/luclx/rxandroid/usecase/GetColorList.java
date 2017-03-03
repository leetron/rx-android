package com.luclx.rxandroid.usecase;

import com.luclx.rxandroid.mvp.model.Color;
import com.luclx.rxandroid.repository.ColorRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by lucle on 2/27/17.
 */

public class GetColorList {

    private final ColorRepository colorRepository;

    @Inject
    public GetColorList(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public Observable<List<Color>> getColors() {
        return this.colorRepository.getColorList();
    }
}
