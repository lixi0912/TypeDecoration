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

        WithIf decoration(@NonNull Decoration decoration);
    }


    interface If {
        Then ifType(int... viewTypes);
        Decorator build();
    }


    interface Then {
        If thenDrawable(Drawable drawable);

        If thenDecoration(Decoration decoration);

        If then();

        Decorator build();
    }

    interface WithIf extends With, If {
        WithIf withMarginStart(int margin);

        WithIf withMarginEnd(int margin);

        WithIf withDrawOverlay(boolean drawOverlay);

        WithIf withDrawEnd(boolean drawEnd);
    }

    interface With {
        With withMarginStart(int margin);

        With withMarginEnd(int margin);

        With withDrawOverlay(boolean drawOverlay);

        With withDrawEnd(boolean drawEnd);

        Decorator build();
    }

}
