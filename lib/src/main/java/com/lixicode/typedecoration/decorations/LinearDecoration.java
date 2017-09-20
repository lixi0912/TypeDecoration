package com.lixicode.typedecoration.decorations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lixicode.typedecoration.Decoration;
import com.lixicode.typedecoration.Decorator;
import com.lixicode.typedecoration.utils.DecorationUtils;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */
public class LinearDecoration extends AbstractDecoration {

    @Nullable
    private Drawable drawable;

    /**
     * a drawable for recycler noDecoration
     *
     * @see android.R.attr#listDivider
     */
    public LinearDecoration(Context context) {
        this(DecorationUtils.listDivider(context));
    }

    public LinearDecoration(@Nullable Drawable drawable) {
        this.drawable = drawable;
    }

    @Override
    public Decoration searchDecoration(int typeIndex) {
        return this;
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
    public Decoration registerDrawable(int typeIndex, Drawable drawable) {
        this.drawable = drawable;
        return this;
    }

    /**
     * @param isSameType true if hasConsistItemType or has same type with next item
     */
    @Override
    public void draw(@NonNull Canvas canvas, boolean isSameType, int typeIndex,
                     int left, int top, int right, int bottom, int parentRight) {
        Drawable drawable = this.drawable;
        if (null == drawable) {
            return;
        }

        final int marginStart;
        final int marginEnd;
//        if (isSameType) {
        // TODO can`t handle grid decoration span size, waiting for fix
        marginStart = getMarginStart();
        marginEnd = getMarginEnd();
//        } else {
//            marginStart = 0;
//            marginEnd = 0;
//        }
        if (getOrientation() == VERTICAL) {
            drawable.setBounds(left + marginStart, top, right - marginEnd, bottom);
        } else {
            drawable.setBounds(left, top + marginStart, right, bottom - marginEnd);
        }


        drawable.draw(canvas);
    }

    @Override
    public void boundsOut(@NonNull Rect outRect, int typeIndex) {
        Drawable drawable = this.drawable;
        if (null == drawable) {
            return;
        }

        if (getOrientation() == Decorator.VERTICAL) {
            outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }

    @Override
    public int getIntrinsicWidth(int typeIndex) {
        Drawable drawable = this.drawable;
        return null == drawable ? 0 : drawable.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight(int typeIndex) {
        Drawable drawable = this.drawable;
        return null == drawable ? 0 : drawable.getIntrinsicHeight();
    }
}
