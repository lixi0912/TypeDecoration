package com.lixicode.typedecoration;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */

public interface RegisterFlow {

    interface WithCondition {
        WithDecoration condition(@NonNull Condition condition);
    }

    interface WithDecoration {
        WithRegister decoration();

        WithRegister decoration(@NonNull Decoration decoration);
    }

    interface WithRegister {
        WithRegister register(int... types);

        WithThen then();
    }


    interface WithThen {
        WithThen thenDrawable(int type, Drawable drawable);

        WithThen thenDecoration(int type, Decoration decoration);

        WithThen thenMarginStart(int margin);

        WithThen thenMarginEnd(int margin);

        WithThen thenDrawOverlay(boolean drawOverlay);

        End end();
    }

    interface End {
        TypeDecoration build(int orientation);
    }
}
