package com.luclx.rxandroid.mvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import javax.annotation.Generated;

/**
 * Created by LucLX on 11/19/16.
 */

/**
 * Generate is optional, please read more
 */

@Generated("org.jsonschema2pojo")
public class ColorResponse {
    @SerializedName("colorsArray")
    private ArrayList<MyColor> mMyColorLst;

    public ArrayList<MyColor> getmMyColorLst() {
        return mMyColorLst;
    }
}
