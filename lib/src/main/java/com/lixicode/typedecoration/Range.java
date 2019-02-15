package com.lixicode.typedecoration;

/**
 * <>
 *
 * @author 陈晓辉
 * @date 2019/2/15
 */
public class Range {
    private int lower;
    private int upper;

    private Range() {

    }

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }

    public int length() {
        return upper - lower;
    }

    public void set(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public void modify(int index) {
        this.lower = Math.min(lower, index);
        this.upper = Math.max(upper, index);
    }

    public void reset() {
        set(Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    public static Range create() {
        Range range = new Range();
        range.reset();
        return range;
    }

}
