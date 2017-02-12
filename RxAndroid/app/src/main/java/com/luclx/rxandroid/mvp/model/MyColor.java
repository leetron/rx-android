package com.luclx.rxandroid.mvp.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by LucLX on 11/19/16.
 */

@Generated("org.jsonschema2pojo")
public class MyColor {
    @SerializedName("colorName")
    private String mColorName;
    @SerializedName("hexValue")
    private String mHexColor;

    public String getmColorName() {
        return mColorName;
    }

    public String getmHexColor() {
        return mHexColor;
    }
}
