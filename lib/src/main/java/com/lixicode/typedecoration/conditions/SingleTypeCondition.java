package com.lixicode.typedecoration.conditions;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixicode.typedecoration.Condition;

import java.util.Arrays;

public class SingleTypeCondition implements Condition {

    private int[] types;

    @Override
    public void registerType(int[] types) {
        this.types = types;
        Arrays.sort(types);
    }

    @Override
    public int typeIndexOf(int viewType) {
        return Arrays.binarySearch(this.types, viewType);
    }

    @Override
    public boolean isSameType(RecyclerView parent, View child, int index) {
        return true;
    }
}