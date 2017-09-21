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
package com.lixicode.typedecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public interface Decoration extends Orientation {

    /**
     * @param typeIndex the index of registered type array
     */
    Decoration searchDecoration(int typeIndex);

    /**
     * @param typeIndex the index of registered type array
     */
    void registerDecoration(int typeIndex, @Nullable Decoration decoration);


    Decoration registerDrawable(int typeIndex, @Nullable Drawable drawable);

    /**
     * @param isSameType true if hasConsistItemType or has same type with next item
     * @param typeIndex  the index of registered type array
     */
    void draw(@NonNull Canvas canvas, boolean isSameType, int typeIndex, int left, int top, int right, int bottom, int parentRight);


    /**
     * @param typeIndex the index of registered type array
     */
    void boundsOut(@NonNull Rect outRect, int typeIndex);

    /**
     * @param typeIndex the index of registered type array
     */
    int getIntrinsicHeight(int typeIndex);

    /**
     * @param typeIndex the index of registered type array
     */
    int getIntrinsicWidth(int typeIndex);

    void draw(@NonNull Decorator decorator, @NonNull Canvas canvas,
              @NonNull RecyclerView parent, @NonNull RecyclerView.State state);


    /**
     * @param typeIndex the index of registered type array
     */
    @Nullable
    Drawable getDrawable(int typeIndex);

    void setMarginStart(int marginStart);

    void setMarginEnd(int marginEnd);

    int getMarginStart();

    int getMarginEnd();

    void setOrientation(int orientation);

    int getOrientation();

    void setDrawEnd(boolean drawEnd);

    boolean isDrawEnd();

}
