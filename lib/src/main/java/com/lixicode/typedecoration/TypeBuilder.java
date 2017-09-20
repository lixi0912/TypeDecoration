package com.lixicode.typedecoration;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/20
 */
class TypeBuilder implements RegisterFlow.If, RegisterFlow.ThenIf {

    private int typeIndex;

    @NonNull
    private final Decorator decorator;

    TypeBuilder(@NonNull Decorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public RegisterFlow.Then ifType(int... viewTypes) {
        typeIndex = decorator.getCondition().registerType(viewTypes);
        return this;
    }

    @Override
    public RegisterFlow.ThenIf thenDrawable(Drawable drawable) {
        Decoration decoration = decorator.getDecoration();
        if (null != decoration) {
            decoration.registerDrawable(typeIndex, drawable);
        }
        return this;
    }

    @Override
    public RegisterFlow.ThenIf thenDecoration(Decoration decoration) {
        Decoration baseDecoration = decorator.getDecoration();
        if (null != baseDecoration) {
            baseDecoration.registerDecoration(typeIndex, decoration);
        }
        return this;
    }

    @Override
    public RegisterFlow.ThenIf then() {
        return this;
    }


    @Override
    public Decorator build() {
        return decorator;
    }

}
