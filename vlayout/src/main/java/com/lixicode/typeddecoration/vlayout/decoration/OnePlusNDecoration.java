package com.lixicode.typeddecoration.vlayout.decoration;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.lixicode.typedecoration.decoration.GridDecoration;

/**
 * <>
 *
 * @author lixi
 * @date 2019/2/18
 */
public class OnePlusNDecoration extends GridDecoration {


    public OnePlusNDecoration(@Nullable Drawable drawable, int itemCount) {
        super(drawable, itemCount);
    }


    @Override
    protected void drawBottom(Canvas c, RecyclerView parent, int indexOfRange,
                              int left, int top, int right, int bottom,
                              int length, @NonNull Drawable drawable) {
        final boolean excludeLastRow = length > 2 && indexOfRange == 1;
        if (excludeLastRow || isDrawEnd()) {
            drawable.setBounds(left + getMarginStart(), bottom - drawable.getIntrinsicHeight(), right - getMarginEnd(), bottom);
            drawable.draw(c);
        }
    }

}
