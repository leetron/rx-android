package com.luclx.rxandroid.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by LucLX on 11/19/16.
 */

@Generated("org.jsonschema2pojo")
public class Color {
    @SerializedName("colorName")
    @Expose
    private String mColorName;
    @SerializedName("hexValue")
    @Expose
    private String mHexColor;

    public String getmColorName() {
        return mColorName;
    }

    public String getmHexColor() {
        return mHexColor;
    }
}
