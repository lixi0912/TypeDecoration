package com.lixicode.typedecoration;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

class TypeDecorationBuilder implements RegisterFlow.WithCondition,
        RegisterFlow.WithDecoration,
        RegisterFlow.WithRegister, RegisterFlow.WithThen, RegisterFlow.End {


    @NonNull
    private Condition condition;

    @Nullable
    private Decoration mDecoration;

    private int marginStart;
    private int marginEnd;
    private boolean drawOverlay;

    @Override
    public RegisterFlow.WithRegister decoration() {
        return this;
    }

    @Override
    public RegisterFlow.WithDecoration condition(@NonNull Condition condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public RegisterFlow.WithRegister decoration(@Nullable Decoration decoration) {
        this.mDecoration = decoration;
        return this;
    }

    @Override
    public RegisterFlow.WithRegister register(int... types) {
        condition.registerType(types);
        return this;
    }

    @Override
    public RegisterFlow.WithThen then() {
        return this;
    }

    @Override
    public RegisterFlow.WithThen thenDrawable(int type, Drawable drawable) {
        if (null != mDecoration)
            mDecoration.registerTypeDraw(type, drawable);
        return this;
    }

    @Override
    public RegisterFlow.WithThen thenDecoration(int type, Decoration decoration) {
        if (null != mDecoration)
            mDecoration.registerDecoration(type, decoration);
        return this;
    }

    @Override
    public RegisterFlow.WithThen thenMarginStart(int margin) {
        this.marginStart = margin;
        return this;
    }

    @Override
    public RegisterFlow.WithThen thenMarginEnd(int margin) {
        this.marginEnd = margin;
        return this;
    }

    @Override
    public RegisterFlow.WithThen thenDrawOverlay(boolean drawOverlay) {
        this.drawOverlay = drawOverlay;
        return this;
    }

    @Override
    public RegisterFlow.End end() {
        return this;
    }

    @Override
    public TypeDecoration build(int orientation) {
        TypeDecoration decoration = new TypeDecoration(orientation, condition, mDecoration);
        decoration.setMarginStart(marginStart);
        decoration.setMarginEnd(marginEnd);
        decoration.setDrawOverlay(drawOverlay);
        return decoration;
    }
}
