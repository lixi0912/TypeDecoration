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

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;

import com.lixicode.typedecoration.Decoration;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public class MultiTypeDecoration extends AbstractDecoration {

    private final SparseArrayCompat<Decoration> arrays = new SparseArrayCompat<>();

    @Override
    public Decoration registerDrawable(int typeIndex, Drawable drawable) {
        Decoration decoration = arrays.get(typeIndex);
        if (null == decoration) {
            decoration = new LinearDecoration(drawable);
            arrays.put(typeIndex, decoration);
        } else {
            decoration.registerDrawable(typeIndex, drawable);
        }
        return decoration;
    }

    @Override
    public Drawable getDrawable(int typeIndex) {
        Decoration decoration = arrays.get(typeIndex);
        if (null != decoration) {
            return decoration.getDrawable(typeIndex);
        }
        return null;
    }

    @Override
    public Decoration searchDecoration(int typeIndex) {
        return arrays.get(typeIndex);
    }

    @Override
    public void registerDecoration(int typeIndex, Decoration decoration) {
        arrays.put(typeIndex, decoration);
    }

    @Override
    public void draw(@NonNull Canvas canvas, boolean isSameType, int typeIndex, int left, int top, int right, int bottom, int parentRight) {
        Decoration decoration = arrays.get(parentRight);
        if (null != decoration) {
            decoration.draw(canvas, isSameType, typeIndex, left, top, right, bottom, parentRight);
        }
    }

    @Override
    public void boundsOut(@NonNull Rect outRect, int typeIndex) {
        Decoration decoration = arrays.get(typeIndex);
        if (null != decoration) {
            decoration.boundsOut(outRect, typeIndex);
        } else {
            outRect.setEmpty();
        }
    }

    @Override
    public int getIntrinsicWidth(int typeIndex) {
        Decoration decoration = arrays.get(typeIndex);
        if (null != decoration) {
            return decoration.getIntrinsicWidth(typeIndex);
        }
        return 0;
    }

    @Override
    public int getIntrinsicHeight(int typeIndex) {
        Decoration decoration = arrays.get(typeIndex);
        if (null != decoration) {
            return decoration.getIntrinsicHeight(typeIndex);
        }
        return 0;
    }


}
