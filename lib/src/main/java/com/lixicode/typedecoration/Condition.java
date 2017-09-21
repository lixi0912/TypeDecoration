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
package com.lixicode.typedecoration;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/5
 */

public interface Condition {

    /**
     * @param types the types of RecyclerView`s ViewHolder
     * @return type index for type noDecoration array
     */
    int registerType(int[] types);


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
    boolean isSameType(Decorator decorator, RecyclerView parent, View child, int index);


}
