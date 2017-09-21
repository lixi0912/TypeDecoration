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
package com.lixicode.typedecoration.decorations;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.lixicode.typedecoration.Decorator;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/20
 */
public interface DecorationParent {

    int drawHorizontal(@NonNull Decorator decorator,
                       @NonNull Canvas canvas,
                       @NonNull RecyclerView parent,
                       int index,
                       int childCount
    );

    int drawOtherOrientation(@NonNull Decorator decoration,
                             @NonNull Canvas canvas,
                             @NonNull RecyclerView parent,
                             @NonNull RecyclerView.State state,
                             int index,
                             int childCount
    );


    int drawVertical(@NonNull Decorator decorator,
                     @NonNull Canvas canvas,
                     @NonNull RecyclerView parent,
                     int index,
                     int childCount
    );

}
