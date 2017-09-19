package com.lixicode.typedecoration.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public final class DecorationUtils {
    /**
     * @param parent the RecyclerView
     * @param view   the item view to draw
     */
    public static int viewTypeOf(RecyclerView parent, View view) {
        int position = parent.getChildAdapterPosition(view);
        return parent.getAdapter().getItemViewType(position);
    }
}
