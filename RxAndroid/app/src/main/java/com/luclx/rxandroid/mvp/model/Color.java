package com.luclx.rxandroid.mvp.model;

/**
 * Created by lucle on 2/27/17.
 */

public class Color {
    private String name;
    private String value;

    public Color(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
