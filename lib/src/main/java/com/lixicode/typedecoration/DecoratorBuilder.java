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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

class DecoratorBuilder implements DecoratorFlow.WithCondition,
        DecoratorFlow.WithDecoration, DecoratorFlow.WithIf {


    @NonNull
    private Condition condition;

    private Decorator decorator;


    @Override
    public DecoratorFlow.WithDecoration noCondition() {
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
    public DecoratorFlow.WithDecoration condition(@NonNull Condition condition) {
        this.condition = condition;
        return this;
    }

    @Override
    public DecoratorFlow.With noDecoration() {
        this.decorator = new Decorator(condition, null);
        return this;
    }

    @Override
    public DecoratorFlow.WithIf decoration(@Nullable Decoration decoration) {
        this.decorator = new Decorator(condition, decoration);
        return this;
    }

    @Override
    public DecoratorFlow.Then ifType(int... viewTypes) {
        return new DecorationBuilder(decorator).ifType(viewTypes);
    }


    @Override
    public DecoratorFlow.WithIf withDrawOverlay(boolean drawOverlay) {
        if (null != decorator) {
            decorator.setDrawOverlay(drawOverlay);
        }
        return this;
    }


    @Override
    public Decorator end() {
        return decorator;
    }


}
