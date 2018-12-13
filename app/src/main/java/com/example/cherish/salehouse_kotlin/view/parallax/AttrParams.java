package com.example.cherish.salehouse_kotlin.view.parallax;

import com.example.cherish.salehouse_kotlin.R;

/**
 * 属性参数
 * Created by cherish
 */

public class AttrParams {
    public float outTranslateX;
    public float outTranslateY;
    public float inTranslateX;
    public float inTranslateY;
    public float outScaleX;
    public float outScale;
    public float outAlpha;
    public float inAlpha;
    public float outRotate;
    public float inRotate;

    @Override
    public String toString() {
        return "AttrParams{" + "outTranslateX=" + outTranslateX + ", outTranslateY=" +
                outTranslateY + ", inTranslateX=" + inTranslateX + ", inTranslateY=" +
                inTranslateY + ", outScaleX=" + outScaleX + ", outScale=" + outScale + ", " +
                "outAlpha=" + outAlpha + ", inAlpha=" + inAlpha + ", outRotate=" + outRotate + "," +
                " inRotate=" + inRotate + '}';
    }
}
