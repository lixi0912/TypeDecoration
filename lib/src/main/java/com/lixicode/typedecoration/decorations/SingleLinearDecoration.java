package com.lixicode.typedecoration.decorations;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lixicode.typedecoration.Decoration;
import com.lixicode.typedecoration.TypeDecoration;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */
public class SingleLinearDecoration extends AbstractDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    @Nullable
    private Drawable drawable;

    /**
     * a drawable for recycler decoration
     *
     * @see android.R.attr#listDivider
     */
    public SingleLinearDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        this.drawable = a.getDrawable(0);
        a.recycle();
    }

    public SingleLinearDecoration(@Nullable Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public void registerDecoration(int typeIndex, @Nullable Decoration decoration) {
        if (null != decoration) {
            this.drawable = decoration.getDrawable(typeIndex);
        }
    }

    @Override
    @Nullable
    public Drawable getDrawable(int typeIndex) {
        return drawable;
    }

    @Override
    public void registerTypeDraw(int typeIndex, Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public void draw(@NonNull Canvas canvas, int typeIndex, int left, int top, int right, int bottom) {
        Drawable drawable = this.drawable;
        if (null == drawable) {
            return;
        }
        drawable.setBounds(left, top, right, bottom);
        drawable.draw(canvas);
    }

    @Override
    public void boundsOut(@NonNull Rect outRect, int direction, int typeIndex) {
        Drawable drawable = this.drawable;
        if (null == drawable) {
            return;
        }

        if (direction == TypeDecoration.VERTICAL) {
            outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }

    @Override
    public int getIntrinsicHeight(int typeIndex) {
        Drawable drawable = this.drawable;
        return null == drawable ? 0 : drawable.getIntrinsicHeight();
    }
}
