package com.lixicode.typedecoration.demo;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public class DelegateAdapterCompat {

    public static int encodeViewType(long viewType, long adapterIndex) {
        return (int) ((viewType + adapterIndex) * (viewType + adapterIndex + 1) / 2 + adapterIndex);
    }

}
