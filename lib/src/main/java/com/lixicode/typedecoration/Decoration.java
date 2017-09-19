package com.lixicode.typedecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public interface Decoration {

    /**
     * @param typeIndex the index of registered type array
     */
    void registerDecoration(int typeIndex, @Nullable Decoration decoration);


    void registerDrawable(int typeIndex, @Nullable Drawable drawable);

    /**
     * @param typeIndex the index of registered type array
     */
    void draw(@NonNull Canvas canvas, int typeIndex, int left, int top, int right, int bottom);


    /**
     * @param typeIndex the index of registered type array
     */
    void boundsOut(@NonNull Rect outRect, int direction, int typeIndex);

    /**
     * @param typeIndex the index of registered type array
     */
    int getIntrinsicHeight(int typeIndex);

    void draw(@NonNull Decorator decoration, @NonNull Canvas c,
              @NonNull RecyclerView parent, @NonNull RecyclerView.State state);


    /**
     * @param typeIndex the index of registered type array
     */
    @Nullable
    Drawable getDrawable(int typeIndex);

    void setMarginStart(int marginStart);

    void setMarginEnd(int marginEnd);

    int getMarginStart();

    int getMarginEnd();

    void setOrientation(int orientation);

    int getOrientation();

}
