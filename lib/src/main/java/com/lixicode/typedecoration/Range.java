package com.lixicode.typedecoration;

import android.util.SparseIntArray;

/**
 * <>
 *
 * @author 陈晓辉
 * @date 2019/2/15
 */
public class Range {

    private final SparseIntArray ranges = new SparseIntArray(2);

    public Range() {

    }


    public int length(int value) {
        int index = searchIndex(value);
        return ranges.get(getLowerIndex(index), -1)
                - ranges.get(getLowerIndex(index), -1);
    }

    public void set(int index, int lower, int upper) {
        ranges.put(getLowerIndex(index), lower);
        ranges.put(getUpperIndex(index), upper);
    }

    public void modify(int value) {
        SparseIntArray ranges = this.ranges;
        int size = ranges.size();
        for (int i = 0; i < size; ) {
            int lower = ranges.get(i, Integer.MAX_VALUE);
            int upper = ranges.get(i + 1, Integer.MIN_VALUE);

            if (lower == Integer.MAX_VALUE || upper == Integer.MIN_VALUE || upper == value - 1) {
                ranges.put(i, Math.min(lower, value));
                ranges.put(i + 1, Math.max(upper, value));
                return;
            }
            i += 2;
        }

        ranges.put(size, value);
        ranges.put(size + 1, value);
    }

    private int searchIndex(int value) {
        final   SparseIntArray ranges = this.ranges;
        int size = ranges.size();
        if (size != 0) {
            int index = 0;
            for (int i = 0; i < size; ) {
                if (value >= ranges.get(i) && value <= ranges.get(i + 1)) {
                    return index;
                }
                i += 2;
                index++;
            }
        }
        return 0;
    }

    public int indexOfRange(int value) {
        int index = searchIndex(value);
        SparseIntArray ranges = this.ranges;
        int lower = ranges.get(getLowerIndex(index), -1);
        if (lower != -1) {
            return value - lower;
        }
        return lower;
    }


    private int getLowerIndex(int index) {
        return 2 * index;
    }

    private int getUpperIndex(int index) {
        return 2 * index + 1;
    }


    public void reset() {
        ranges.clear();
    }


}
