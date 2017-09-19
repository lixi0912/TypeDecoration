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
        WithDecoration noCondition();

        WithDecoration condition(@NonNull Condition condition);
    }

    interface WithDecoration {
        With decoration();

        With decoration(@NonNull Decoration decoration);
    }

    interface With {
        With withType(int... viewTypes);

        With withMarginStart(int margin);

        With withMarginEnd(int margin);

        With withDrawOverlay(boolean drawOverlay);
        With withDrawEnd(boolean drawEnd);
        Then then();
    }


    interface Then {
        Then thenDrawable(int viewType, Drawable drawable);

        Then thenDecoration(int viewType, Decoration decoration);


        End end();
    }

    interface End {
        Decorator build();
    }
}
