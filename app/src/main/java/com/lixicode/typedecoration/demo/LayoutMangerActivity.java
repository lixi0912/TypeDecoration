package com.lixicode.typedecoration.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lixicode.typedecoration.Decorator;
import com.lixicode.typedecoration.conditions.MultiTypeCondition;
import com.lixicode.typedecoration.decorations.GridDecoration;
import com.lixicode.typedecoration.decorations.MultiTypeDecoration;
import com.lixicode.typedecoration.utils.DecorationUtils;

/**
 * @author 陈晓辉
 * @description <>
 * @date 2017/9/20
 */
public class LayoutMangerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
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
                return 5;
            }
        });

        Decorator decorator = Decorator.newBuilder()
                .noCondition()
                .decoration(new GridDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2)))
                .withDrawOverlay(true)
                .end();

        recyclerView.addItemDecoration(decorator);
    }
}
