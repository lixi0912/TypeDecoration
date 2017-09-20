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
        RegisterFlow.WithDecoration, RegisterFlow.WithIf {


    @NonNull
    private Condition condition;

    private Decorator decorator;

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
            public int registerType(int[] types) {
                return 0;
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
    public RegisterFlow.WithIf decoration(@Nullable Decoration decoration) {
        Decorator decorator = new Decorator(condition, decoration);
        decorator.setOrientation(orientation);
        this.decorator = decorator;
        return this;
    }

    @Override
    public RegisterFlow.Then ifType(int... viewTypes) {
        return new TypeBuilder(decorator).ifType(viewTypes);
    }

    @Override
    public RegisterFlow.With withMarginStart(int margin) {
        if (null != decorator) {
            decorator.setMarginStart(margin);
        }
        return this;
    }

    @Override
    public RegisterFlow.With withMarginEnd(int margin) {
        if (null != decorator) {
            decorator.setMarginEnd(margin);
        }
        return this;
    }

    @Override
    public RegisterFlow.With withDrawOverlay(boolean drawOverlay) {
        if (null != decorator) {
            decorator.setDrawOverlay(drawOverlay);
        }
        return this;
    }

    @Override
    public RegisterFlow.With withDrawEnd(boolean drawEnd) {
        if (null != decorator) {
            decorator.setDrawEnd(drawEnd);
        }
        return this;
    }


    @Override
    public Decorator build() {
        return decorator;
    }


}
