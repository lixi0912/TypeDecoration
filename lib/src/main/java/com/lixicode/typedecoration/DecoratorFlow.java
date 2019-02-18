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

/**
 * @author lixi
 * @description <>
 * @date 2017/9/19
 */
public interface DecoratorFlow {

    interface Creator {

        Then simple();

        Then multi(int... viewTypes);

    }


    interface If extends End {
        Then ifType(int... viewTypes);
    }


    interface Then extends End {
        AndIf thenDecoration(Decoration decoration);
    }

    interface AndIf extends If {
        AndIf andMarginStart(int margin);

        AndIf andDrawEnd(boolean drawEnd);

        AndIf andMarginEnd(int margin);

        AndIf andOrientation(int orientation);
    }


    interface End {
        Decorator end();
    }


}
