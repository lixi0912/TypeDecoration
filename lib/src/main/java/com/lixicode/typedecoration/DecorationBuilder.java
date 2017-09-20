package com.lixicode.typedecoration;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/20
 */
class DecorationBuilder implements DecoratorFlow.If, DecoratorFlow.Then, DecoratorFlow.AndIf {

    private int typeIndex;

    @NonNull
    private final Decorator decorator;
    private Decoration newDecoration;

    DecorationBuilder(@NonNull Decorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public DecoratorFlow.Then ifType(int... viewTypes) {
        typeIndex = decorator.getCondition().registerType(viewTypes);
        return this;
    }

    @Override
    public DecoratorFlow.AndIf thenDrawable(Drawable drawable) {
        Decoration decoration = decorator.getDecoration();
        if (null != decoration) {
            newDecoration = decoration.registerDrawable(typeIndex, drawable);
        }
        return this;
    }

    @Override
    public DecoratorFlow.AndIf thenDecoration(Decoration decoration) {
        Decoration baseDecoration = decorator.getDecoration();
        if (null != baseDecoration) {
            baseDecoration.registerDecoration(typeIndex, decoration);
        }
        newDecoration = decoration;
        return this;
    }

    @Override
    public DecoratorFlow.AndIf andMarginStart(int margin) {
        if (null != newDecoration) {
            newDecoration.setMarginStart(margin);
        }
        return this;
    }

    @Override
    public DecoratorFlow.AndIf andDrawEnd(boolean drawEnd) {
        if (null != newDecoration) {
            newDecoration.setDrawEnd(drawEnd);
        }
        return this;
    }

    @Override
    public DecoratorFlow.AndIf andMarginEnd(int margin) {
        if (null != newDecoration) {
            newDecoration.setMarginEnd(margin);
        }
        return this;
    }

    @Override
    public DecoratorFlow.AndIf andOrientation(int orientation) {
        if (null != newDecoration) {
            newDecoration.setOrientation(orientation);
        }
        return this;    }

    @Override
    public DecoratorFlow.If thenNothing() {
        return this;
    }


    @Override
    public Decorator end() {
        return decorator;
    }


}
