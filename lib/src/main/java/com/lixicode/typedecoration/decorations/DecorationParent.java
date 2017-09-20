package com.lixicode.typedecoration.decorations;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.lixicode.typedecoration.Decorator;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/20
 */
public interface DecorationParent {

    int drawHorizontal(@NonNull Decorator decorator,
                       @NonNull Canvas canvas,
                       @NonNull RecyclerView parent,
                       int index,
                       int childCount
    );

    int drawOtherOrientation(@NonNull Decorator decoration,
                             @NonNull Canvas canvas,
                             @NonNull RecyclerView parent,
                             @NonNull RecyclerView.State state,
                             int index,
                             int childCount
    );


    int drawVertical(@NonNull Decorator decorator,
                     @NonNull Canvas canvas,
                     @NonNull RecyclerView parent,
                     int index,
                     int childCount
    );

}
