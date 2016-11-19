package com.luclx.rxandroid;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LucLX on 11/19/16.
 */

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
