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
package com.lixicode.typedecoration.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixicode.typedecoration.Decorator;
import com.lixicode.typedecoration.Range;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/20
 */
public class GridDecoration extends AbstractDecoration {


    protected int spanCount;

    @Nullable
    protected Drawable drawable;

    public GridDecoration(@Nullable Drawable drawable, int spanCount) {
        this.drawable = drawable;
        setSpanCount(spanCount);
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

    }

    @Override
    protected boolean autoCreateRange() {
        return true;
    }

    @Override
    public final void onDraw(Decorator decorator, Canvas c, View child, RecyclerView parent, RecyclerView.State state) {
        if (null == drawable) {
            return;
        }
        parent.getDecoratedBoundsWithMargins(child, decorator.getBounds());
        final int right = decorator.getBounds().right;
        final int left = decorator.getBounds().left;
        final int bottom = decorator.getBounds().bottom + Math.round(child.getTranslationY());
        final int top = bottom - decorator.getBounds().height();

        final int index = parent.indexOfChild(child);
        final int indexOfRange = getRange().indexOfRange(index);
        final int length = getRange().length();

        drawRight(c, parent, indexOfRange, left, top, right, bottom, drawable);
        drawBottom(c, parent, indexOfRange, left, top, right, bottom, length, drawable);
    }


    protected void drawBottom(Canvas c, RecyclerView parent, int indexOfRange,
                              int left, int top, int right, int bottom, int length,
                              @NonNull Drawable drawable) {
        final boolean excludeLastRow = indexOfRange / spanCount != length / spanCount;
        if (excludeLastRow || isDrawEnd()) {
            drawable.setBounds(left + getMarginStart(), bottom - drawable.getIntrinsicHeight(), right - getMarginEnd(), bottom);
            drawable.draw(c);
        }
    }

    protected void drawRight(Canvas c, RecyclerView parent, int indexOfRange, int left, int top, int right, int bottom, @NonNull Drawable drawable) {
        // vlayout - auto expand
        final boolean fullItem = parent.getClipToPadding() && right == parent.getRight() - parent.getPaddingRight()
                || right == parent.getRight();

        final boolean excludeLastColumn = indexOfRange % spanCount != (spanCount - 1) || !fullItem;
        if (excludeLastColumn) {
            // 如果不是最后一列
            int drawableWidth = drawable.getIntrinsicWidth() / 2;
            drawable.setBounds(right - drawableWidth, top + getMarginStart(), right + drawableWidth, bottom - getMarginEnd());
            drawable.draw(c);
        }

    }


}
