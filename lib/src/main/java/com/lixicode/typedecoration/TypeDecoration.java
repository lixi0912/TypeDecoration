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
    private final DecorationCondition condition;

    @Nullable
    private final DrawableRegister typeDrawable;

    private int marginStart;
    private int marginEnd;
    private boolean drawOverlay;


    public static TypeDecoration simple(Context context, int orientation) {
        return new TypeDecoration(context, orientation, new DecorationCondition.SimpleTypeCondition());
    }

    public static TypeDecoration multiAble(Context context, int orientation) {
        return new TypeDecoration(context, orientation, new DecorationCondition.MultiTypeCondition());
    }


    public TypeDecoration(Context context, int orientation, @NonNull DecorationCondition condition) {
        this(context, orientation, condition, new DrawableRegister.SingleTypeDrawable(context));
    }

    public TypeDecoration(Context context, int orientation, @NonNull DecorationCondition condition, @Nullable DrawableRegister typeDrawable) {
        this.condition = condition;
        this.typeDrawable = typeDrawable;
        setOrientation(orientation);
    }

    public boolean registerDrawable(int type, Drawable drawable) {
        if (null != typeDrawable) {
            int typeIndex = condition.typeIndexOf(type);
            typeDrawable.registerTypeDraw(typeIndex, drawable);
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
        if (parent.getLayoutManager() == null || typeDrawable == null || drawOverlay) {
            return;
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getLayoutManager() == null || typeDrawable == null || !drawOverlay) {
            return;
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        DrawableRegister typeDrawable = this.typeDrawable;
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int viewType = viewTypeOf(parent, child);
            int typeIndex = condition.typeIndexOf(viewType);
            if (typeIndex >= 0
                    && condition.isSameType(parent, child, i)) {
                parent.getDecoratedBoundsWithMargins(child, mBounds);
                final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
                final int top = bottom - typeDrawable.getIntrinsicHeight(typeIndex);
                typeDrawable.draw(canvas, typeIndex, left, top + marginStart, right, bottom - marginEnd);
            }

        }
        canvas.restore();
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        DrawableRegister typeDrawable = this.typeDrawable;
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final int viewType = viewTypeOf(parent, child);
            int typeIndex = condition.typeIndexOf(viewType);
            if (typeIndex >= 0
                    && condition.isSameType(parent, child, i)) {
                parent.getDecoratedBoundsWithMargins(child, mBounds);
                final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
                final int top = bottom - typeDrawable.getIntrinsicHeight(typeIndex);
                typeDrawable.draw(canvas, typeIndex, left + marginStart, top, right - marginEnd, bottom);
            }
        }
        canvas.restore();
    }


    /**
     * @param parent the RecyclerView
     * @param view   the item view to draw
     */
    private int viewTypeOf(RecyclerView parent, View view) {
        int position = parent.getChildAdapterPosition(view);
        return parent.getAdapter().getItemViewType(position);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        final int typeIndex = condition.typeIndexOf(viewTypeOf(parent, view));
        DrawableRegister typeDrawable = this.typeDrawable;
        if (typeDrawable == null || typeIndex == -1 || drawOverlay) {
            outRect.setEmpty();
            return;
        }
        typeDrawable.boundsOut(outRect, mOrientation, typeIndex);
    }
}
