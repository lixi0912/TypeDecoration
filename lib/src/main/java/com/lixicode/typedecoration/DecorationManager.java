package com.lixicode.typedecoration;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <>
 *
 * @author 陈晓辉
 * @date 2019/2/15
 */
interface DecorationManager {

    public void modifyRange(RecyclerView parent);

    public Decoration findDecoration(int viewType);

    void cacheDecoration(int[] type, Decoration decoration);


    class MultiImpl implements DecorationManager {

        private SparseArrayCompat<Decoration> mDecorations = new SparseArrayCompat<>();

        @Override
        public void modifyRange(RecyclerView parent) {
            SparseArrayCompat<Decoration> decorations = this.mDecorations;
            final int size = decorations.size();
            for (int i = 0; i < size; i++) {
                Decoration decoration = decorations.valueAt(i);
                Range range = decoration.getRange();
                if (null != range) {
                    range.reset();
                }
            }
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final int viewType = parent.getChildViewHolder(child).getItemViewType();
                Decoration decoration = decorations.get(viewType);
                if (null != decoration) {
                    Range range = decoration.getRange();
                    if (null != range) {
                        range.modify(i);
                    }
                }
            }
        }

        @Override
        public Decoration findDecoration(int viewType) {
            return mDecorations.get(viewType);
        }

        @Override
        public void cacheDecoration(int[] types, Decoration decoration) {
            if (null != decoration && null != types && types.length > 0) {
                for (int type : types) {
                    mDecorations.put(type, decoration);
                }
            }
        }


    }


    class SimpleImpl implements DecorationManager {

        private Decoration mDecoration;

        @Override
        public void modifyRange(RecyclerView parent) {
            Range range = mDecoration.getRange();
            if (null != range) {
                range.set(0, 0, parent.getChildCount() - 1);
            }
        }

        @Override
        public Decoration findDecoration(int viewType) {
            return mDecoration;
        }

        @Override
        public void cacheDecoration(int[] type, Decoration decoration) {
            this.mDecoration = decoration;
        }


    }
}
