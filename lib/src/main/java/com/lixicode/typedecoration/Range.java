package com.lixicode.typedecoration;

import android.util.SparseIntArray;

/**
 * <>
 *
 * @author 陈晓辉
 * @date 2019/2/15
 */
public class Range {


    private int index;
    private final SparseIntArray ranges = new SparseIntArray(2);

    public Range() {

    }


    private int getLower() {
        int key = getLowerIndex(index);
        return ranges.get(key, -1);
    }

    private int getUpper() {
        int key = getUpperIndex(index);
        return ranges.get(key, -1);
    }

    public int length() {
        return getUpper() - getLower();
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


    public int indexOfRange(int value) {
        SparseIntArray ranges = this.ranges;
        int size = ranges.size();
        if (size == 0) {
            return -1;
        }
        index = 0;
        for (int i = 0; i < size; ) {
            int lower = ranges.get(i);
            int upper = ranges.get(i + 1);
            if (value >= lower && value <= upper) {
                return value - lower;
            }
            i += 2;
            index++;
        }
        return -1;
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
