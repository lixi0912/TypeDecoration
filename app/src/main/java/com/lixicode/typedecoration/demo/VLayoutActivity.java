package com.lixicode.typedecoration.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.lixicode.typedecoration.Decorator;
import com.lixicode.typedecoration.DecoratorFactory;
import com.lixicode.typedecoration.decoration.GridDecoration;
import com.lixicode.typedecoration.decoration.LinearDecoration;

import java.util.ArrayList;
import java.util.List;

public class VLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        DelegateAdapter delegateAdapter = new DelegateAdapter(manager);
        List<DelegateAdapter.Adapter> adapters = new ArrayList<>();


        int index$0 = adapters.size();
        LinearLayoutHelper layout$0 = new LinearLayoutHelper();
        adapters.add(new SubAdapter(this, layout$0, 3));


        int index$1 = adapters.size();
        GridLayoutHelper layout$1 = new GridLayoutHelper(3);
        adapters.add(new SubAdapter(this, layout$1, 4));


        int index$2 = adapters.size();
        LinearLayoutHelper layout$2 = new LinearLayoutHelper();
        layout$2.setMarginTop(getResources().getDimensionPixelSize(R.dimen.margin));
        layout$2.setBgColor(ContextCompat.getColor(this, R.color.item_activated_color));
        adapters.add(new SubAdapter(this, layout$2, 5));

        int index$3 = adapters.size();
        LinearLayoutHelper layout$3 = new LinearLayoutHelper();
        layout$3.setBgColor(ContextCompat.getColor(this, R.color.item_activated_color2));
        adapters.add(new SubAdapter(this, layout$3, 3));


        int index$4 = adapters.size();
        GridLayoutHelper layout$4 = new GridLayoutHelper(3);
        adapters.add(new SubAdapter(this, layout$4, 5));


        int index$5 = adapters.size();
        LinearLayoutHelper layout$5 = new LinearLayoutHelper();
        layout$5.setBgColor(ContextCompat.getColor(this, R.color.item_activated_color3));
        adapters.add(new SubAdapter(this, layout$5, 3));


        int index$6 = adapters.size();
        StaggeredGridLayoutHelper layout$6 = new StaggeredGridLayoutHelper(2);
        adapters.add(new SubAdapter(this, layout$6, 5));


        int index$7 = adapters.size();
        OnePlusNLayoutHelper layout$7 = new OnePlusNLayoutHelper();
        adapters.add(new SubAdapter(this, layout$7, 5));


        delegateAdapter.setAdapters(adapters);
        recyclerView.setAdapter(delegateAdapter);


        int margin = getResources().getDimensionPixelSize(R.dimen.margin);

        Decorator decorator = DecoratorFactory.newBuilder()
                .multi(
                        DelegateAdapterCompat.encodeViewType(0, index$4)
                )
                .thenDecoration(new GridDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2), 3))
                .andMarginEnd(margin)
                .andMarginStart(margin)
                .andDrawEnd(true)
                .ifType(
                        DelegateAdapterCompat.encodeViewType(0, index$6),
                        DelegateAdapterCompat.encodeViewType(0, index$7)
                )
                .thenDecoration(new GridDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2), 5))
                .ifType(DelegateAdapterCompat.encodeViewType(0, index$0))
                .thenDecoration(new LinearDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2)))
                .andMarginStart(margin)
                .andMarginEnd(margin)
                .ifType(
                        DelegateAdapterCompat.encodeViewType(0, index$2),
                        DelegateAdapterCompat.encodeViewType(0, index$3),
                        DelegateAdapterCompat.encodeViewType(0, index$5)
                )
                .thenDecoration(new LinearDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2)))
                .end();

        recyclerView.addItemDecoration(decorator);

    }


    /**
     * MIT License
     * <p>
     * Copyright (c) 2016 Alibaba Group
     * <p>
     * Permission is hereby granted, free of charge, to any person obtaining a copy
     * of this software and associated documentation files (the "Software"), to deal
     * in the Software without restriction, including without limitation the rights
     * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
     * copies of the Software, and to permit persons to whom the Software is
     * furnished to do so, subject to the following conditions:
     * <p>
     * The above copyright notice and this permission notice shall be included in all
     * copies or substantial portions of the Software.
     * <p>
     * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
     * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
     * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
     * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
     * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
     * SOFTWARE.
     */
    static class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder> {

        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private RecyclerView.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull RecyclerView.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(
                    new RecyclerView.LayoutParams(mLayoutParams));
        }


        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }

    /**
     * MIT License
     * <p>
     * Copyright (c) 2016 Alibaba Group
     * <p>
     * Permission is hereby granted, free of charge, to any person obtaining a copy
     * of this software and associated documentation files (the "Software"), to deal
     * in the Software without restriction, including without limitation the rights
     * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
     * copies of the Software, and to permit persons to whom the Software is
     * furnished to do so, subject to the following conditions:
     * <p>
     * The above copyright notice and this permission notice shall be included in all
     * copies or substantial portions of the Software.
     * <p>
     * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
     * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
     * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
     * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
     * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
     * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
     * SOFTWARE.
     */
    static class MainViewHolder extends RecyclerView.ViewHolder {

        public static volatile int existing = 0;
        public static int createdTimes = 0;

        public MainViewHolder(View itemView) {
            super(itemView);
            createdTimes++;
            existing++;
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }
}
