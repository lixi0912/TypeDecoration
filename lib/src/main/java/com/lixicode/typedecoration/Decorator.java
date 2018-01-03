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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixicode.typedecoration.utils.DecorationUtils;

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
public class Decorator extends RecyclerView.ItemDecoration implements Orientation {

    private final Rect mBounds = new Rect();

    @NonNull
    private final Condition condition;

    @Nullable
    private final Decoration baseDecoration;

    private boolean drawOverlay;


    public static DecoratorFlow.WithCondition newBuilder() {
        return new DecoratorBuilder();
    }

    Decorator(@NonNull Condition condition,
              @Nullable Decoration decoration) {
        this.condition = condition;
        this.baseDecoration = decoration;
    }


    public Rect getBounds() {
        return mBounds;
    }

    @Nullable
    Decoration getDecoration() {
        return baseDecoration;
    }

    @NonNull
    public Condition getCondition() {
        return condition;
    }

    public void setDrawOverlay(boolean overlay) {
        this.drawOverlay = overlay;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getLayoutManager() == null || baseDecoration == null || drawOverlay) {
            return;
        }
        baseDecoration.draw(this, c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getLayoutManager() == null || baseDecoration == null || !drawOverlay) {
            return;
        }
        baseDecoration.draw(this, c, parent, state);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        final int typeIndex = condition.typeIndexOf(DecorationUtils.viewTypeOf(parent, view));
        Decoration decoration = this.baseDecoration;
        if (decoration == null || typeIndex == -1) {
            outRect.setEmpty();
            return;
        }
        decoration.boundsOut(this, parent, view, state, outRect, typeIndex);
    }

    public boolean isDrawOverlay() {
        return drawOverlay;
    }

    public boolean isDrawEnd(int typeIndex) {
        Decoration decoration = this.baseDecoration;
        if (null == decoration) {
            return false;
        }
        decoration = decoration.searchDecoration(typeIndex);
        return null != decoration && decoration.isDrawEnd();
    }
}
