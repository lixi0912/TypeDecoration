package com.lixicode.typedecoration.decorations;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixicode.typedecoration.Condition;
import com.lixicode.typedecoration.Decoration;
import com.lixicode.typedecoration.Decorator;
import com.lixicode.typedecoration.utils.DecorationUtils;

import static com.lixicode.typedecoration.Decorator.HORIZONTAL;
import static com.lixicode.typedecoration.Decorator.VERTICAL;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

abstract class AbstractDecoration implements Decoration {
    private int marginStart;
    private int marginEnd;
    private int orientation;


    @Override
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public int getOrientation() {
        return orientation;
    }

    @Override
    public final void setMarginStart(int marginStart) {
        this.marginStart = marginStart;
    }

    @Override
    public final void setMarginEnd(int marginEnd) {
        this.marginEnd = marginEnd;
    }

    @Override
    public final int getMarginStart() {
        return marginStart;
    }

    @Override
    public final int getMarginEnd() {
        return marginEnd;
    }

    private void drawHorizontal(@NonNull Decorator decorator,
                                @NonNull Canvas canvas, @NonNull RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        final Condition condition = decorator.getCondition();
        final int marginStart = decorator.getMarginStart() + getMarginStart();
        final int marginEnd = decorator.getMarginEnd() + getMarginEnd();
        final Rect bounds = decorator.getBounds();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int viewType = DecorationUtils.viewTypeOf(parent, child);
            int typeIndex = condition.typeIndexOf(viewType);
            if (typeIndex >= 0
                    && condition.isSameType(decorator, parent, child, i)) {
                parent.getDecoratedBoundsWithMargins(child, bounds);
                final int bottom = bounds.bottom + Math.round(child.getTranslationY());
                final int top = bottom - getIntrinsicHeight(typeIndex);
                draw(canvas, typeIndex, left, top + marginStart, right, bottom - marginEnd);
            }

        }
        canvas.restore();
    }

    private void drawVertical(@NonNull Decorator decorator,
                              @NonNull Canvas canvas, @NonNull RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        final Condition condition = decorator.getCondition();
        final Rect bounds = decorator.getBounds();
        final int marginStart = decorator.getMarginStart() + getMarginStart();
        final int marginEnd = decorator.getMarginEnd() + getMarginEnd();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int viewType = DecorationUtils.viewTypeOf(parent, child);
            int typeIndex = condition.typeIndexOf(viewType);
            if (typeIndex >= 0
                    && condition.isSameType(decorator, parent, child, i)) {
                parent.getDecoratedBoundsWithMargins(child, bounds);
                final int bottom = bounds.bottom + Math.round(child.getTranslationY());
                final int top = bottom - getIntrinsicHeight(typeIndex);
                draw(canvas, typeIndex, left + marginStart, top, right - marginEnd, bottom);
            }
        }
        canvas.restore();
    }

    @Override
    public final void draw(@NonNull Decorator decorator, @NonNull Canvas c,
                           @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (getOrientation() == VERTICAL) {
            drawVertical(decorator, c, parent);
        } else if (getOrientation() == HORIZONTAL) {
            drawHorizontal(decorator, c, parent);
        } else {
            drawOtherOrientation(decorator, c, parent, state);
        }
    }

    protected void drawOtherOrientation(@NonNull Decorator decoration, @NonNull Canvas c,
                                        @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

    }

}
