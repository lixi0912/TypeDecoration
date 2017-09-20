package com.lixicode.typedecoration.conditions;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixicode.typedecoration.Condition;
import com.lixicode.typedecoration.Decorator;

import java.util.Arrays;

public class MultiTypeCondition implements Condition {
    @NonNull
    private final SparseArrayCompat<int[]> typeArray = new SparseArrayCompat<>();

    @Override
    public int registerType(int[] types) {
        SparseArrayCompat<int[]> typeArray = this.typeArray;
        final int newIndex = typeArray.size();
        typeArray.put(newIndex, types);
        Arrays.sort(types);
        return newIndex;
    }

    @Override
    public int typeIndexOf(int viewType) {
        SparseArrayCompat<int[]> typeArray = this.typeArray;
        int typeSize = typeArray.size();
        for (int typesIndex = 0; typesIndex < typeSize; typesIndex++) {
            if (findType(typeArray.valueAt(typesIndex), viewType)) {
                return typesIndex;
            }
        }
        return -1;
    }

    private boolean findType(int[] source, int key) {
        return Arrays.binarySearch(source, key) >= 0;
    }


    /**
     * @param parent the RecyclerView
     * @param child  the view to draw divider
     * @param index  the index to draw
     */
    @Override
    public boolean isSameType(Decorator decorator, RecyclerView parent, View child, int index) {
        SparseArrayCompat<int[]> typeArray = this.typeArray;
        final int childCount = parent.getChildCount();
        if (index == childCount - 1)
            return true;

        final int position = parent.getChildAdapterPosition(child);

        final int viewType = parent.getAdapter().getItemViewType(position);
        final int nextType = parent.getAdapter().getItemViewType(position + 1);
        if (viewType == nextType)
            return true;
        int typeIndex = typeIndexOf(viewType);
        return typeIndex >= 0 && findType(typeArray.valueAt(typeIndex), nextType);
    }
}