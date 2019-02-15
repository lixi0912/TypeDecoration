package com.lixicode.typedecoration;

import android.support.annotation.NonNull;

/**
 * <>
 *
 * @author 陈晓辉
 * @date 2019/2/15
 */
public final class DecoratorFactory {

    public static DecoratorFlow.Creator newBuilder() {
        return new DecoratorFlow.Creator() {
            @Override
            public DecoratorFlow.Then simple() {
                final DecorationManager manager = new DecorationManager.SimpleImpl();
                final Decorator decorator = new Decorator(manager);
                return new DecoratorFlowImpl(decorator, null);
            }


            @Override
            public DecoratorFlow.Then multi(int... viewTypes) {
                final DecorationManager manager = new DecorationManager.MultiImpl();
                final Decorator decorator = new Decorator(manager);
                return new DecoratorFlowImpl(decorator, viewTypes);
            }
        };
    }


    static class DecoratorFlowImpl implements DecoratorFlow.If, DecoratorFlow.Then, DecoratorFlow.AndIf {

        private final int[] types;

        @NonNull
        private final Decorator decorator;
        private Decoration mDecoration;

        static DecoratorFlow.Then newBuilder(Decorator decorator, int[] viewTypes) {
            return new DecoratorFlowImpl(decorator, viewTypes);
        }

        private DecoratorFlowImpl(@NonNull Decorator decorator, int[] viewTypes) {
            this.decorator = decorator;
            this.types = viewTypes;
        }

        @Override
        public DecoratorFlow.Then ifType(int... viewTypes) {
            return newBuilder(decorator, viewTypes);
        }


        @Override
        public DecoratorFlow.AndIf thenDecoration(Decoration decoration) {
            Decorator.put(decorator, types, decoration);
            this.mDecoration = decoration;
            return this;
        }

        @Override
        public DecoratorFlow.AndIf andMarginStart(int margin) {
            if (null != mDecoration) {
                mDecoration.setMarginStart(margin);
            }
            return this;
        }

        @Override
        public DecoratorFlow.AndIf andDrawEnd(boolean drawEnd) {
            if (null != mDecoration) {
                mDecoration.setDrawEnd(drawEnd);
            }
            return this;
        }

        @Override
        public DecoratorFlow.AndIf andMarginEnd(int margin) {
            if (null != mDecoration) {
                mDecoration.setMarginEnd(margin);
            }
            return this;
        }

        @Override
        public DecoratorFlow.AndIf andOrientation(int orientation) {
            if (null != mDecoration) {
                mDecoration.setOrientation(orientation);
            }
            return this;
        }


        @Override
        public Decorator end() {
            return decorator;
        }


    }
}
