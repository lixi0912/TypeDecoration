/*
 * Copyright 2017 lixi0912@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
        if (null == types) {
            return -1;
        }
        return Arrays.binarySearch(this.types, viewType);
    }

    @Override
    public boolean isSameType(Decorator decorator, RecyclerView parent,
                              View child, int index) {
        
        final int childCount = parent.getChildCount();
        if (index == childCount - 1)// lastItem
            return false;

        final int position = parent.getChildAdapterPosition(child);

        final int viewType = parent.getAdapter().getItemViewType(position);
        final int nextType = parent.getAdapter().getItemViewType(position + 1);

        return viewType == nextType || typeIndexOf(viewType) >= 0
                && typeIndexOf(nextType) >= 0;
    }
}