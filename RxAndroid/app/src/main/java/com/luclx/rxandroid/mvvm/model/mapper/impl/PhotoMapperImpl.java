package com.luclx.rxandroid.mvvm.model.mapper.impl;

import com.luclx.rxandroid.mvvm.model.Photo;
import com.luclx.rxandroid.mvvm.model.mapper.PhotoMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LucLX on 2/25/17.
 */

public class PhotoMapperImpl
        extends BaseMapper
        implements PhotoMapper<com.luclx.rxandroid.mvvm.data.entities.Photo, Photo> {
    @Override
    public Photo transform(com.luclx.rxandroid.mvvm.data.entities.Photo photo) {
        checkNull(photo);

        return new Photo(photo.getId(),
                photo.getColor(),
                photo.getLikes(),
                photo.getUrls().getRegular());
    }

    @Override
    public List<Photo> transform(List<com.luclx.rxandroid.mvvm.data.entities.Photo> photos) {
        checkNull(photos);

        List<Photo> photoList = new ArrayList<>();
        for (com.luclx.rxandroid.mvvm.data.entities.Photo photo : photos) {
            photoList.add(transform(photo));
        }

        return photoList;
    }
}
