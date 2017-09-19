package com.lixicode.typedecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.util.SparseArrayCompat;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public interface DrawableRegister {
    void registerTypeDraw(int typeIndex, Drawable drawable);

    void draw(Canvas canvas, int typeIndex, int left, int top, int right, int bottom);


    /**
     * @param typeIndex the index of registered type array
     */
    void boundsOut(Rect outRect, int direction, int typeIndex);

    int getIntrinsicHeight(int typeIndex);

    /**
     * a drawable for recycler decoration
     *
     * @see android.R.attr#listDivider
     */
    class SingleTypeDrawable implements DrawableRegister {
        private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
        private Drawable drawable;

        public SingleTypeDrawable(Context context) {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            this.drawable = a.getDrawable(0);
            a.recycle();
        }

        public SingleTypeDrawable(Drawable drawable) {
            this.drawable = drawable;
        }

        @Override
        public void registerTypeDraw(int typeIndex, Drawable drawable) {
            this.drawable = drawable;
        }

        @Override
        public void draw(Canvas canvas, int typeIndex, int left, int top, int right, int bottom) {
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(canvas);
        }

        @Override
        public void boundsOut(Rect outRect, int direction, int typeIndex) {
            if (direction == TypeDecoration.VERTICAL) {
                outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
            }
        }

        @Override
        public int getIntrinsicHeight(int typeIndex) {
            return drawable.getIntrinsicHeight();
        }
    }


    class MultiTypeDrawable implements DrawableRegister {
        private final SparseArrayCompat<Drawable> arrays = new SparseArrayCompat<>();

        @Override
        public void registerTypeDraw(int typeIndex, Drawable drawable) {
            arrays.put(typeIndex, drawable);
        }

        @Override
        public void draw(Canvas canvas, int typeIndex, int left, int top, int right, int bottom) {
            Drawable drawable = arrays.get(typeIndex);
            if (null != drawable) {
                drawable.setBounds(left, top, right, bottom);
                drawable.draw(canvas);
            }
        }

        @Override
        public void boundsOut(Rect outRect, int direction, int typeIndex) {
            Drawable drawable = arrays.get(typeIndex);
            if (null != drawable) {
                if (direction == TypeDecoration.VERTICAL) {
                    outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
                } else {
                    outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
                }
            } else {
                outRect.setEmpty();
            }
        }

        @Override
        public int getIntrinsicHeight(int typeIndex) {
            Drawable drawable = arrays.get(typeIndex);
            if (null != drawable) {
                return drawable.getIntrinsicHeight();
            }
            return 0;
        }
    }
}
