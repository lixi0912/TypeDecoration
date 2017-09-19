package com.lixicode.typedecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.lixicode.typedecoration.conditions.MultiTypeCondition;
import com.lixicode.typedecoration.conditions.SimpleTypeCondition;
import com.lixicode.typedecoration.decorations.SingleLinearDecoration;
import com.lixicode.typedecoration.utils.DecorationUtils;

/**
 * <pre>
 * TypeDecoration decoration = TypeDecoration.multiAble(this,
 * TypeDecoration.VERTICAL);
 * decoration.setMarginStart(margin);
 * decoration.setMarginEnd(margin);
 *
 * // post item decoration - showDividers = middle
 * decoration.register(
 *     DelegateAdapter.encodeViewType(0, postIndex),
 *     DelegateAdapter.encodeViewType(1, postIndex),
 *     DelegateAdapter.encodeViewType(2, postIndex),
 *     DelegateAdapter.encodeViewType(3, postIndex)
 * );
 *
 * // top item decoration - showDividers = middle
 * decorationEx.register(DelegateAdapter.encodeViewType(0, topIndex));
 * </pre>
 *
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/5
 */
public class TypeDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    /**
     * Current orientation. Either {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    private int mOrientation;
    private final Rect mBounds = new Rect();

    @NonNull
    private final Condition condition;

    @Nullable
    private final Decoration decoration;

    private int marginStart;
    private int marginEnd;
    private boolean drawOverlay;


    public static TypeDecoration simple(Context context, int orientation) {
        return new TypeDecoration(context, orientation, new SimpleTypeCondition());
    }

    public static TypeDecoration multiAble(Context context, int orientation) {
        return new TypeDecoration(context, orientation, new MultiTypeCondition());
    }


    public TypeDecoration(Context context, int orientation, @NonNull Condition condition) {
        this(context, orientation, condition, new SingleLinearDecoration(context));
    }

    public TypeDecoration(Context context, int orientation, @NonNull Condition condition, @Nullable Decoration decoration) {
        this.condition = condition;
        this.decoration = decoration;
        setOrientation(orientation);
    }

    public boolean registerDrawable(int type, Drawable drawable) {
        if (null != decoration) {
            int typeIndex = condition.typeIndexOf(type);
            decoration.registerTypeDraw(typeIndex, drawable);
            return true;
        }
        return false;
    }

    public boolean registerDecoration(int type, Decoration decoration) {
        if (null != decoration) {
            int typeIndex = condition.typeIndexOf(type);
            decoration.registerDecoration(typeIndex, decoration);
            return true;
        }
        return false;
    }

    /**
     * @param types types of RecyclerView
     */
    public void register(int... types) {
        condition.registerType(types);
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
        if (parent.getLayoutManager() == null || decoration == null || drawOverlay) {
            return;
        }
        decoration.draw(this, c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getLayoutManager() == null || decoration == null || !drawOverlay) {
            return;
        }
        decoration.draw(this, c, parent, state);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        final int typeIndex = condition.typeIndexOf(DecorationUtils.viewTypeOf(parent, view));
        Decoration typeDrawable = this.decoration;
        if (typeDrawable == null || typeIndex == -1 || drawOverlay) {
            outRect.setEmpty();
            return;
        }
        typeDrawable.boundsOut(outRect, mOrientation, typeIndex);
    }
}
