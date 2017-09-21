package com.lixicode.typedecoration.conditions;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixicode.typedecoration.Condition;
import com.lixicode.typedecoration.Decorator;

import java.util.Arrays;

public class SingleTypeCondition implements Condition {

    private int[] types;

    @Override
    public int registerType(int[] types) {
        this.types = types;
        Arrays.sort(types);
        return 0;
    }

    @Override
    public int typeIndexOf(int viewType) {
        return Arrays.binarySearch(this.types, viewType);
    }

    @Override
    public boolean isSameType(Decorator decorator, RecyclerView parent,
                              View child, int index) {
        final int position = parent.getChildAdapterPosition(child);

        final int viewType = parent.getAdapter().getItemViewType(position);
        final int nextType = parent.getAdapter().getItemViewType(position + 1);

        return viewType == nextType || typeIndexOf(viewType) >= 0
                && typeIndexOf(nextType) >= 0;
    }
}