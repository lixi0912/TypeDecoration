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
