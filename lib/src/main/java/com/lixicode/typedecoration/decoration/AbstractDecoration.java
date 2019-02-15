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
package com.lixicode.typedecoration.decoration;

import com.lixicode.typedecoration.Decoration;
import com.lixicode.typedecoration.Range;


/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */
public abstract class AbstractDecoration implements Decoration {

    private int marginStart;
    private int marginEnd;
    private boolean drawEnd;
    private int orientation;
    private Range range;

    @Override
    public void setDrawEnd(boolean drawEnd) {
        this.drawEnd = drawEnd;
    }

    @Override
    public boolean isDrawEnd() {
        return drawEnd;
    }

    @Override
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    @Override
    public int getOrientation() {
        return orientation;
    }

    @Override
    public final void setMarginStart(int marginStart) {
        this.marginStart = marginStart;
    }

    @Override
    public final void setMarginEnd(int marginEnd) {
        this.marginEnd = marginEnd;
    }

    @Override
    public final int getMarginStart() {
        return marginStart;
    }

    @Override
    public final int getMarginEnd() {
        return marginEnd;
    }


    protected boolean autoCreateRange() {
        return false;
    }

    @Override
    public Range getRange() {
        if (null == range && autoCreateRange()) {
            range = Range.create();
        }
        return range;
    }

    @Override
    public void setRange(Range range) {
        this.range = range;
    }

}
