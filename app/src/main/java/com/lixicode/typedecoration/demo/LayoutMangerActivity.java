package com.lixicode.typedecoration.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.lixicode.typedecoration.Decorator;
import com.lixicode.typedecoration.DecoratorFactory;
import com.lixicode.typedecoration.decoration.GridDecoration;

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


}
