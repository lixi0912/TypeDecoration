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
package com.lixicode.typedecoration.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public final class DecorationUtils {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    /**
     * @param parent the RecyclerView
     * @param view   the item view to draw
     */
    public static int viewTypeOf(RecyclerView parent, View view) {
        int position = parent.getChildAdapterPosition(view);
        return parent.getAdapter().getItemViewType(position);
    }


    public static Drawable listDivider(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        Drawable drawable = a.getDrawable(0);
        a.recycle();
        return drawable;
    }

    public static boolean hasFlag(int originFlag, int checkFlag) {
        return originFlag == checkFlag || (originFlag & checkFlag) == checkFlag;
    }

}
