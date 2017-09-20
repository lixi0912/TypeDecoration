package com.lixicode.typedecoration;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/19
 */
public interface DecoratorFlow {

    interface WithCondition {
        WithDecoration noCondition();

        WithDecoration condition(@NonNull Condition condition);
    }

    interface WithDecoration {
        With noDecoration();

        WithIf decoration(@NonNull Decoration decoration);
    }


    interface If extends End {
        Then ifType(int... viewTypes);
    }


    interface Then extends End {
        AndIf thenDrawable(Drawable drawable);

        AndIf thenDecoration(Decoration decoration);

        If thenNothing();
    }

    interface AndIf extends If {
        AndIf andMarginStart(int margin);

        AndIf andDrawEnd(boolean drawEnd);

        AndIf andMarginEnd(int margin);

        AndIf andOrientation(int orientation);
    }

    interface With extends End {
        With withDrawOverlay(boolean drawOverlay);
    }

    interface WithIf extends With, If {
        WithIf withDrawOverlay(boolean drawOverlay);
    }


    interface End {
        Decorator end();
    }


}
