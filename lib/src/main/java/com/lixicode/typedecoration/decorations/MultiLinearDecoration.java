package com.lixicode.typedecoration.decorations;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;

import com.lixicode.typedecoration.Decoration;
import com.lixicode.typedecoration.TypeDecoration;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public class MultiLinearDecoration extends AbstractDecoration {

    private final SparseArrayCompat<Drawable> arrays = new SparseArrayCompat<>();

    @Override
    public void registerTypeDraw(int typeIndex, Drawable drawable) {
        arrays.put(typeIndex, drawable);
    }

    @Override
    public void registerDecoration(int typeIndex, Decoration decoration) {
        if (null != decoration) {
            arrays.put(typeIndex, decoration.getDrawable(typeIndex));
        }
    }

    @Override
    public Drawable getDrawable(int typeIndex) {
        return arrays.get(typeIndex);
    }

    @Override
    public void draw(@NonNull Canvas canvas, int typeIndex, int left, int top, int right, int bottom) {
        Drawable drawable = arrays.get(typeIndex);
        if (null != drawable) {
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
        }
    }

    @Override
    public void boundsOut(@NonNull Rect outRect, int direction, int typeIndex) {
        Drawable drawable = arrays.get(typeIndex);
        if (null != drawable) {
            if (direction == TypeDecoration.VERTICAL) {
                outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
            }
        } else {
            outRect.setEmpty();
        }
    }

    @Override
    public int getIntrinsicHeight(int typeIndex) {
        Drawable drawable = arrays.get(typeIndex);
        if (null != drawable) {
            return drawable.getIntrinsicHeight();
        }
        return 0;
    }

}
