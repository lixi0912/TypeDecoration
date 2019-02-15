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
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixicode.typedecoration.Decorator;

import static com.lixicode.typedecoration.Orientation.VERTICAL;


/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */
public class LinearDecoration extends AbstractDecoration {

    @Nullable
    private Drawable drawable;



    public LinearDecoration(@Nullable Drawable drawable) {
        this.drawable = drawable;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (needDraw(null, parent, view)) {
            assert drawable != null;
            if (getOrientation() == VERTICAL) {
                outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
            }
        }
    }


    private boolean needDraw(Decorator decorator, RecyclerView parent, View child) {
        // 判断当前是否需要绘制
        return null != drawable && (!Decorator.isLastItem(decorator, parent, child) || isDrawEnd());
    }

    @Override
    public void onDraw(Decorator decorator, Canvas c, View child, RecyclerView parent, RecyclerView.State state) {
        if (needDraw(decorator, parent, child)) {
            assert drawable != null;

            final int left;
            final int right;
            //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
            if (parent.getClipToPadding()) {
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();
            } else {
                left = 0;
                right = parent.getWidth();
            }

            parent.getDecoratedBoundsWithMargins(child, decorator.getBounds());
            final int bottom = decorator.getBounds().bottom + Math.round(child.getTranslationY());
            final int top = bottom - drawable.getIntrinsicHeight();

            if (getOrientation() == VERTICAL) {
                drawable.setBounds(left + getMarginStart(), top, right - getMarginEnd(), bottom);
            } else {
                drawable.setBounds(left, top + getMarginStart(), right, bottom - getMarginEnd());
            }
            drawable.draw(c);
        }
    }


}
