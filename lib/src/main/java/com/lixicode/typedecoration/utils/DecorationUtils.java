package com.lixicode.typedecoration.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public final class DecorationUtils {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    /**
     * @param parent the RecyclerView
     * @param view   the item view to draw
     */
    public static int viewTypeOf(RecyclerView parent, View view) {
        int position = parent.getChildAdapterPosition(view);
        return parent.getAdapter().getItemViewType(position);
    }


    public static Drawable listDivider(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        Drawable drawable = a.getDrawable(0);
        a.recycle();
        return drawable;
    }
}
