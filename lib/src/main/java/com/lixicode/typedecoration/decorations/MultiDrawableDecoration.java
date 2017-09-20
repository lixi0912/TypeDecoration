package com.lixicode.typedecoration.decorations;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;

import com.lixicode.typedecoration.Decoration;
import com.lixicode.typedecoration.Decorator;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public class MultiDrawableDecoration extends AbstractDecoration {

    private final SparseArrayCompat<Drawable> arrays = new SparseArrayCompat<>();

    @Override
    public Decoration registerDrawable(int typeIndex, Drawable drawable) {
        arrays.put(typeIndex, drawable);
        return this;
    }

    @Override
    public Decoration searchDecoration(int typeIndex) {
        return this;
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
    public void draw(@NonNull Canvas canvas, boolean isSameType, int typeIndex, int left, int top, int right, int bottom, int parentRight) {
        Drawable drawable = arrays.get(parentRight);
        if (null != drawable) {

            final int marginStart;
            final int marginEnd;
            if (isSameType) {
                marginStart = getMarginStart();
                marginEnd = getMarginEnd();
            } else {
                marginStart = 0;
                marginEnd = 0;
            }
            if (getOrientation() == VERTICAL) {
                drawable.setBounds(left + marginStart, top, right - marginEnd, bottom);
            } else {
                drawable.setBounds(left, top + marginStart, right, bottom - marginEnd);
            }

            drawable.draw(canvas);
        }
    }

    @Override
    public void boundsOut(@NonNull Rect outRect, int typeIndex) {
        Drawable drawable = arrays.get(typeIndex);
        if (null != drawable) {
            if (getOrientation() == Decorator.VERTICAL) {
                outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
            }
        } else {
            outRect.setEmpty();
        }
    }

    @Override
    public int getIntrinsicWidth(int typeIndex) {
        Drawable drawable = arrays.get(typeIndex);
        if (null != drawable) {
            return drawable.getIntrinsicWidth();
        }
        return 0;
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
