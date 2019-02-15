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
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <pre>
 * Decorator baseDecoration = Decorator.multiAble(this,
 * Decorator.VERTICAL);
 * baseDecoration.setMarginStart(margin);
 * baseDecoration.setMarginEnd(margin);
 *
 * // post item baseDecoration - showDividers = middle
 * baseDecoration.withType(
 *     (int) CantorPairFunctions.process(0, postIndex),
 *     (int) CantorPairFunctions.process(1, postIndex),
 *     (int) CantorPairFunctions.process(2, postIndex),
 *     (int) CantorPairFunctions.process(3, postIndex)
 * );
 *
 * // top item baseDecoration - showDividers = middle
 * decorationEx.withType((int) CantorPairFunctions.process(0, topIndex));
 * </pre>
 *
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/5
 */
public class Decorator extends RecyclerView.ItemDecoration {


    private final DecorationManager mDecorations;
    private final Rect mBounds = new Rect();

    public Decorator(DecorationManager decorations) {
        this.mDecorations = decorations;
    }

    public Rect getBounds() {
        return mBounds;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        mDecorations.modifyRange(parent);
        try {
            c.save();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final int viewType = parent.getChildViewHolder(child).getItemViewType();
                Decoration decoration = mDecorations.findDecoration(viewType);
                if (null != decoration) {
                    decoration.onDraw(this, c, child, parent, state);
                }
            }
        } finally {
            c.restore();
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        int viewType = parent.getChildViewHolder(view).getItemViewType();
        Decoration decoration = mDecorations.findDecoration(viewType);
        if (null != decoration) {
            decoration.getItemOffsets(outRect, view, parent, state);
        }
    }

    static void put(Decorator decorator, int[] types, Decoration decoration) {
        decorator.mDecorations.cacheDecoration(types, decoration);
    }

    public static boolean isLastItem(Decorator decorator, RecyclerView parent, View child) {
        int index = parent.indexOfChild(child);
        int nextItem = index + 1;
        if (nextItem >= parent.getChildCount()) {
            return true;
        }
        View nextChild = parent.getChildAt(nextItem);

        final int viewType = parent.getChildViewHolder(child).getItemViewType();
        final int nextViewType = parent.getChildViewHolder(nextChild).getItemViewType();
        if (viewType == nextViewType) {
            return true;
        }
        return decorator.mDecorations.findDecoration(viewType) == decorator.mDecorations.findDecoration(nextViewType);
    }

}
