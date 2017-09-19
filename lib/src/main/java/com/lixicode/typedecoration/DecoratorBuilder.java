package com.lixicode.typedecoration;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

class DecoratorBuilder implements RegisterFlow.WithCondition,
        RegisterFlow.WithDecoration, RegisterFlow.With,
        RegisterFlow.Then, RegisterFlow.End {


    @NonNull
    private Condition condition;

    @Nullable
    private Decoration mDecoration;

    private int marginStart;
    private int marginEnd;
    private boolean drawOverlay;
    private boolean drawEnd;
    private int orientation;

    public DecoratorBuilder(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public RegisterFlow.With decoration() {
        return this;
    }

    @Override
    public RegisterFlow.WithDecoration noCondition() {
        this.condition = new Condition() {
            @Override
            public void registerType(int[] types) {

            }

            @Override
            public int typeIndexOf(int viewType) {
                return 0;
            }

            @Override
            public boolean isSameType(Decorator decorator, RecyclerView parent, View child, int index) {
                return true;
            }
        };
        return this;
    }

    @Override
    public RegisterFlow.WithDecoration condition(@NonNull Condition condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public RegisterFlow.With decoration(@Nullable Decoration decoration) {
        this.mDecoration = decoration;
        return this;
    }

    @Override
    public RegisterFlow.With withType(int... types) {
        condition.registerType(types);
        return this;
    }


    @Override
    public RegisterFlow.With withMarginStart(int margin) {
        this.marginStart = margin;
        return this;
    }

    @Override
    public RegisterFlow.With withMarginEnd(int margin) {
        this.marginEnd = margin;
        return this;
    }

    @Override
    public RegisterFlow.With withDrawOverlay(boolean drawOverlay) {
        this.drawOverlay = drawOverlay;
        return this;
    }

    @Override
    public RegisterFlow.With withDrawEnd(boolean drawEnd) {
        this.drawEnd = drawEnd;
        return this;
    }

    @Override
    public RegisterFlow.Then then() {
        return this;
    }

    @Override
    public RegisterFlow.Then thenDrawable(int viewType, Drawable drawable) {
        final int typeIndex = condition.typeIndexOf(viewType);
        if (null != mDecoration)
            mDecoration.registerDrawable(typeIndex, drawable);
        return this;
    }

    @Override
    public RegisterFlow.Then thenDecoration(int viewType, Decoration decoration) {
        decoration.setOrientation(orientation);
        final int typeIndex = condition.typeIndexOf(viewType);
        if (null != mDecoration)
            mDecoration.registerDecoration(typeIndex, decoration);
        return this;
    }


    @Override
    public RegisterFlow.End end() {
        return this;
    }

    @Override
    public Decorator build() {
        Decorator decoration = new Decorator(orientation, condition, mDecoration);
        decoration.setMarginStart(marginStart);
        decoration.setMarginEnd(marginEnd);
        decoration.setDrawOverlay(drawOverlay);
        decoration.setDrawEnd(drawEnd);
        return decoration;
    }
}
