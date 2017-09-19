package com.lixicode.typedecoration;

import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Arrays;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/5
 */

public interface DecorationCondition {

    void registerType(int[] types);


    /**
     * @param viewType the item view type of ViewHolder
     * @return the index of registeredTypeArray
     */
    int typeIndexOf(int viewType);

    /**
     * @param parent the RecyclerView
     * @param child  the view to draw divider
     * @return true if hasConsistItemType or has same type with next item
     */
    boolean isSameType(RecyclerView parent, View child, int index);


    final class SimpleTypeCondition implements DecorationCondition {

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

    final class MultiTypeCondition implements DecorationCondition {
        @NonNull
        private final SparseArrayCompat<int[]> typeArray = new SparseArrayCompat<>();

        @Override
        public void registerType(int[] types) {
            SparseArrayCompat<int[]> typeArray = this.typeArray;
            typeArray.put(typeArray.size() + 1, types);
            Arrays.sort(types);
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
        public boolean isSameType(RecyclerView parent, View child, int index) {
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
            return typeIndex > 0 && findType(typeArray.valueAt(typeIndex), nextType);
        }
    }
}
