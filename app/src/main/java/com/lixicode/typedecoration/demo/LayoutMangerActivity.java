package com.lixicode.typedecoration.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lixicode.typedecoration.DecoratorFactory;
import com.lixicode.typedecoration.decoration.GridDecoration;
import com.lixicode.typedecoration.decoration.LinearDecoration;

/**
 * @author lixi
 * @description <>
 * @date 2017/9/20
 */
public class LayoutMangerActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new VLayoutActivity.MainViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item2, parent, false));
            }

            @Override
            public int getItemViewType(int position) {
                return position % 2;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(position));
            }

            @Override
            public int getItemCount() {
                return 100;
            }
        });


        findViewById(R.id.action_container).setVisibility(View.VISIBLE);
        findViewById(R.id.linear_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPAN_COUNT = 1;
                updateItemDecoration();
                recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
            }
        });
        findViewById(R.id.grid_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPAN_COUNT = 3;
                updateItemDecoration();
                recyclerView.setLayoutManager(new GridLayoutManager(v.getContext(), SPAN_COUNT));
            }
        });
        findViewById(R.id.staggered_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPAN_COUNT = 2;
                updateItemDecoration();
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL));
            }
        });


    }

    @Override
    protected void updateItemDecoration() {
        recyclerView.removeItemDecoration(decorator);
        if (SPAN_COUNT > 1) {
            decorator = DecoratorFactory
                    .newBuilder()
                    .multi(0)
                    .thenDecoration(new GridDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2), SPAN_COUNT))
                    .andMarginStart(marginStart)
                    .andMarginEnd(marginEnd)
                    .andDrawEnd(drawEnd)
                    .ifType(1)
                    .thenDecoration(new GridDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical), SPAN_COUNT))
                    .andMarginStart(marginStart)
                    .andMarginEnd(marginEnd)
                    .andDrawEnd(drawEnd)
                    .end();

        } else {
            decorator = DecoratorFactory
                    .newBuilder()
                    .multi(0)
                    .thenDecoration(new LinearDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2)))
                    .andMarginStart(marginStart)
                    .andMarginEnd(marginEnd)
                    .andDrawEnd(drawEnd)
                    .ifType(1)
                    .thenDecoration(new LinearDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical)))
                    .andMarginStart(marginStart)
                    .andMarginEnd(marginEnd)
                    .andDrawEnd(drawEnd)
                    .end();
        }

        recyclerView.addItemDecoration(decorator);
    }


}
