package com.lixicode.typedecoration.decorations;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;

import com.lixicode.typedecoration.Decoration;
import com.lixicode.typedecoration.TypeDecoration;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public class MultiTypeDecoration extends AbstractDecoration {

    private final SparseArrayCompat<Decoration> arrays = new SparseArrayCompat<>();

    @Override
    public void registerTypeDraw(int typeIndex, Drawable drawable) {
        Decoration decoration = arrays.get(typeIndex);
        if (null == decoration) {
            arrays.put(typeIndex, new SingleLinearDecoration(drawable));
        } else {
            decoration.registerTypeDraw(typeIndex, drawable);
        }
    }

    @Override
    public Drawable getDrawable(int typeIndex) {
        Decoration decoration = arrays.get(typeIndex);
        if (null != decoration) {
            return decoration.getDrawable(typeIndex);
        }
        return null;
    }

    @Override
    public void registerDecoration(int typeIndex, Decoration decoration) {
        arrays.put(typeIndex, decoration);
    }

    @Override
    protected void drawOtherOrientation(@NonNull TypeDecoration decoration,
                                        @NonNull Canvas c,
                                        @NonNull RecyclerView parent,
                                        @NonNull RecyclerView.State state) {
        //TODO GridLayoutManger
        //TODO StaggeredGridLayoutManager
    }

    @Override
    public void draw(@NonNull Canvas canvas, int typeIndex, int left, int top, int right, int bottom) {
        Decoration decoration = arrays.get(typeIndex);
        if (null != decoration) {
            decoration.draw(canvas, typeIndex, left, top, right, bottom);
        }
    }

    @Override
    public void boundsOut(@NonNull Rect outRect, int direction, int typeIndex) {
        Decoration decoration = arrays.get(typeIndex);
        if (null != decoration) {
            decoration.boundsOut(outRect, direction, typeIndex);
        } else {
            outRect.setEmpty();
        }
    }

    @Override
    public int getIntrinsicHeight(int typeIndex) {
        Decoration decoration = arrays.get(typeIndex);
        if (null != decoration) {
            return decoration.getIntrinsicHeight(typeIndex);
        }
        return 0;
    }


}
