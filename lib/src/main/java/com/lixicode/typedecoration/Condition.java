package com.lixicode.typedecoration;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/5
 */

public interface Condition {

    void registerType(int[] types);


    /**
     * @param viewType the item view type of ViewHolder
     * @return the index of registeredTypeArray
     */
    int typeIndexOf(int viewType);

    /**
     * @param parent the RecyclerView
     * @param child  the view to draw divider
     * @return true if hasConsistItemType or has same type with next item
     */
    boolean isSameType(RecyclerView parent, View child, int index);



}