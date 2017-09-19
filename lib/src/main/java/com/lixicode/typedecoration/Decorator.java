package com.lixicode.typedecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

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
public class Decorator extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;
    public static final int OTHER = -1;

    /**
     * Current orientation. Either {@link #HORIZONTAL} or {@link #VERTICAL}. or {@link #OTHER}
     */
    private int mOrientation;
    private final Rect mBounds = new Rect();

    @NonNull
    private final Condition condition;

    @Nullable
    private final Decoration baseDecoration;

    private int marginStart;
    private int marginEnd;
    private boolean drawOverlay;
    private boolean drawEnd;


    public static RegisterFlow.WithCondition newBuilder(int orientation) {
        return new DecoratorBuilder(orientation);
    }

    Decorator(int orientation, @NonNull Condition condition,
              @Nullable Decoration decoration) {
        this.condition = condition;
        this.baseDecoration = decoration;
        setOrientation(orientation);
    }


    public boolean isDrawEnd() {
        return drawEnd;
    }

    public void setDrawEnd(boolean drawEnd) {
        this.drawEnd = drawEnd;
    }

    public void setMarginStart(int marginStart) {
        this.marginStart = marginStart;
    }

    public void setMarginEnd(int marginEnd) {
        this.marginEnd = marginEnd;
    }

    public int getOrientation() {
        return mOrientation;
    }

    public Rect getBounds() {
        return mBounds;
    }

    @NonNull
    public Condition getCondition() {
        return condition;
    }

    public int getMarginStart() {
        return marginStart;
    }

    public int getMarginEnd() {
        return marginEnd;
    }


    /**
     * Sets the orientation for this divider. This should be called if
     * {@link RecyclerView.LayoutManager} changes orientation.
     *
     * @param orientation {@link #HORIZONTAL} or {@link #VERTICAL}
     */
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        mOrientation = orientation;
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
        Decoration typeDrawable = this.baseDecoration;
        if (typeDrawable == null || typeIndex == -1 || drawOverlay) {
            outRect.setEmpty();
            return;
        }
        typeDrawable.boundsOut(outRect, mOrientation, typeIndex);
    }


}
