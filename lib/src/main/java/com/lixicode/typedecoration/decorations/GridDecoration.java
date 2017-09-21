/*
 * Copyright 2017 lixi0912@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lixicode.typedecoration.decorations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixicode.typedecoration.Condition;
import com.lixicode.typedecoration.Decoration;
import com.lixicode.typedecoration.Decorator;
import com.lixicode.typedecoration.utils.DecorationUtils;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/20
 */
public class GridDecoration extends AbstractDecoration {

    @Nullable
    private Drawable drawable;

    /**
     * a drawable for recycler noDecoration
     *
     * @see android.R.attr#listDivider
     */
    public GridDecoration(Context context) {
        this(DecorationUtils.listDivider(context));
        setOrientation(OTHER);
    }

    public GridDecoration(@Nullable Drawable drawable) {
        this.drawable = drawable;
        setOrientation(OTHER);
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


    @Override
    public int drawOtherOrientation(@NonNull Decorator decorator, @NonNull Canvas canvas,
                                    @NonNull RecyclerView parent,
                                    @NonNull RecyclerView.State state, int index, int childCount) {
        //TODO GridLayoutManger
        //TODO StaggeredGridLayoutManager
        final int parentRight;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            int left = parent.getPaddingLeft();
            parentRight = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), parentRight,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            parentRight = parent.getWidth() - parent.getPaddingRight();
        }

        final Condition condition = decorator.getCondition();
        final Rect bounds = decorator.getBounds();
        for (int i = index; i < childCount; i++) {
            index = i;

            final View child = parent.getChildAt(i);
            final int viewType = DecorationUtils.viewTypeOf(parent, child);
            final int typeIndex = condition.typeIndexOf(viewType);
            final boolean isSameType = condition.isSameType(decorator, parent, child, i);

            if (typeIndex >= 0 && isSameType || decorator.isDrawEnd(typeIndex)) {
                parent.getDecoratedBoundsWithMargins(child, bounds);

                int left = bounds.left;
                int right = bounds.right;
                final int bottom = bounds.bottom + Math.round(child.getTranslationY());
                final int top = bottom - child.getHeight();
                draw(canvas, isSameType, typeIndex, left, top, right, bottom, parentRight);

                if (!isSameType) {
                    break;
                }
            } else {
                break;
            }
        }
        return index;

    }

    @Override
    public void draw(@NonNull Canvas canvas, boolean isSameType, int typeIndex,
                     int left, int top, int right, int bottom,
                     int parentRight) {
        Drawable drawable = this.drawable;
        if (null == drawable) {
            return;
        }

        final int marginStart;
        final int marginEnd;

//        if (isSameType) {
        // TODO can`t handle span size here
        marginStart = getMarginStart();
        marginEnd = getMarginEnd();
//        } else {
//            marginStart = 0;
//            marginEnd = 0;
//        }

        // TODO can`t skip last row
        drawable.setBounds(left + marginStart, bottom - getIntrinsicHeight(parentRight), right - marginEnd, bottom);
        drawable.draw(canvas);

        if (right != parentRight) {
            drawable.setBounds(right - getIntrinsicWidth(parentRight), top + marginStart, right, bottom - marginEnd);
            drawable.draw(canvas);
        }
    }

    @Override
    public void boundsOut(@NonNull Rect outRect, int typeIndex) {
        outRect.setEmpty();
    }

    @Override
    public int getIntrinsicWidth(int typeIndex) {
        return null == drawable ? 0 : drawable.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight(int typeIndex) {
        return null == drawable ? 0 : drawable.getIntrinsicHeight();
    }


}
