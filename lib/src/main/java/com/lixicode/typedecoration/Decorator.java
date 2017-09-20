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
 *     DelegateAdapter.encodeViewType(0, postIndex),
 *     DelegateAdapter.encodeViewType(1, postIndex),
 *     DelegateAdapter.encodeViewType(2, postIndex),
 *     DelegateAdapter.encodeViewType(3, postIndex)
 * );
 *
 * // top item baseDecoration - showDividers = middle
 * decorationEx.withType(DelegateAdapter.encodeViewType(0, topIndex));
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
        if (decoration == null || typeIndex == -1 || drawOverlay) {
            outRect.setEmpty();
            return;
        }
        decoration.boundsOut(outRect, typeIndex);
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
