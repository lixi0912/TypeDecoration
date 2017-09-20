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


/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

abstract class AbstractDecoration implements Decoration, DecorationParent {
    private int orientation;
    private int marginStart;
    private int marginEnd;
    private boolean drawEnd;

    AbstractDecoration() {
        this.orientation = VERTICAL;
    }

    @Override
    public void setDrawEnd(boolean drawEnd) {
        this.drawEnd = drawEnd;
    }

    @Override
    public boolean isDrawEnd() {
        return drawEnd;
    }

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


    @Override
    public int drawHorizontal(@NonNull Decorator decorator,
                              @NonNull Canvas canvas, @NonNull RecyclerView parent, int index, int childCount) {
        if (index >= childCount) {
            return index;
        }

        final int top;
        final int bottom;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }


        final Condition condition = decorator.getCondition();
        final Rect bounds = decorator.getBounds();
        for (int i = index; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int viewType = DecorationUtils.viewTypeOf(parent, child);
            final int typeIndex = condition.typeIndexOf(viewType);
            final boolean isSameType = condition.isSameType(decorator, parent, child, i);
            if (typeIndex >= 0 && isSameType || decorator.isDrawEnd(typeIndex)) {
                parent.getDecoratedBoundsWithMargins(child, bounds);
                final int right = bounds.right + Math.round(child.getTranslationX());
                final int left = right - getIntrinsicWidth(typeIndex);
                draw(canvas, isSameType, typeIndex, left, top, right, bottom, right);
                index = i;
                if (!isSameType) {
                    return index;
                }
            } else {
                return index;
            }
        }
        return index;
    }


    @Override
    public int drawVertical(@NonNull Decorator decorator,
                            @NonNull Canvas canvas, @NonNull RecyclerView parent, int index, int childCount) {
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
        for (int i = index; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int viewType = DecorationUtils.viewTypeOf(parent, child);
            final int typeIndex = condition.typeIndexOf(viewType);
            final boolean isSameType = condition.isSameType(decorator, parent, child, i);
            if (typeIndex >= 0 && isSameType || decorator.isDrawEnd(typeIndex)) {
                parent.getDecoratedBoundsWithMargins(child, bounds);
                final int bottom = bounds.bottom + Math.round(child.getTranslationY());
                final int top = bottom - getIntrinsicHeight(typeIndex);
                draw(canvas, isSameType, typeIndex, left, top, right, bottom, right);
                index = i;
                if (!isSameType) {
                    return index;
                }
            } else {
                return index;
            }
        }
        return index;
    }

    @Override
    public final void draw(@NonNull Decorator decorator, @NonNull Canvas c,
                           @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        final Condition condition = decorator.getCondition();
        final int childCount = parent.getChildCount();
        try {
            c.save();
            for (int i = 0; i < childCount; ) {
                final View child = parent.getChildAt(i);
                final int viewType = DecorationUtils.viewTypeOf(parent, child);
                final int typeIndex = condition.typeIndexOf(viewType);
                Decoration decoration = searchDecoration(typeIndex);
                if (null == decoration) {
                    i += 1;
                    continue;
                }
                int next;

                if (decoration instanceof DecorationParent) {
                    DecorationParent parentDecoration = ((DecorationParent) decoration);
                    if (decoration.getOrientation() == VERTICAL) {
                        next = parentDecoration.drawVertical(decorator, c, parent,
                                i, childCount);
                    } else if (decoration.getOrientation() == HORIZONTAL) {
                        next = parentDecoration.drawHorizontal(decorator, c, parent,
                                i, childCount);
                    } else {
                        next = parentDecoration.drawOtherOrientation(decorator, c,
                                parent, state, i, childCount);
                    }
                } else {
                    if (decoration.getOrientation() == VERTICAL) {
                        next = drawVertical(decorator, c, parent, i, childCount);
                    } else if (decoration.getOrientation() == HORIZONTAL) {
                        next = drawHorizontal(decorator, c, parent, i, childCount);
                    } else {
                        next = i;
                    }
                }
                i = Math.max(i + 1, next);
            }
        } finally {
            c.restore();
        }

    }


    @Override
    public int drawOtherOrientation(@NonNull Decorator decoration, @NonNull Canvas c,
                                    @NonNull RecyclerView parent, @NonNull RecyclerView.State state, int i,
                                    int childCount) {
        return i;
    }


}