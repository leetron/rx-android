package com.luclx.rxandroid.mvp.model.mapper.impl;

import com.luclx.rxandroid.mvp.model.Color;
import com.luclx.rxandroid.mvp.model.mapper.BaseMapper;
import com.luclx.rxandroid.mvp.model.mapper.ColorMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by lucle on 2/27/17.
 */

public class ColorMapperImpl
        extends BaseMapper
        implements ColorMapper<com.luclx.rxandroid.data.entity.Color, Color> {

    @Inject
    public ColorMapperImpl() {

    }

    @Override
    public Color transform(com.luclx.rxandroid.data.entity.Color color) {
        this.checkNull(color);

        return new Color(color.getmColorName(), color.getmHexColor());
    }

    @Override
    public List<Color> transform(List<com.luclx.rxandroid.data.entity.Color> list) {
        this.checkNull(list);

        List<Color> colorList = new ArrayList<>();
        for (com.luclx.rxandroid.data.entity.Color color : list) {
            colorList.add(transform(color));
        }

        return colorList;
    }
}
