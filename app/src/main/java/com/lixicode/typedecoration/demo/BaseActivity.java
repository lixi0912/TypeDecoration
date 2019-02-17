package com.lixicode.typedecoration.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import com.lixicode.typedecoration.Decorator;
import com.lixicode.typedecoration.DecoratorFactory;
import com.lixicode.typedecoration.decoration.GridDecoration;

/**
 * @author lixi
 * @description <>
 * @date 2019/2/17
 */
public class BaseActivity extends AppCompatActivity {


    protected int marginStart;
    protected int marginEnd;
    protected boolean drawEnd;

    protected RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = findViewById(R.id.recycler_view);

        Switch switchButton = findViewById(R.id.draw_end);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                drawEnd = isChecked;
                updateItemDecoration();
            }
        });


        final SeekBar marginStartSeekBar = findViewById(R.id.margin_start);
        SeekBar marginEndSeekBar = findViewById(R.id.margin_end);

        marginStartSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                marginStart = progress;
                updateItemDecoration();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        marginEndSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                marginEnd = progress;
                updateItemDecoration();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        updateItemDecoration();
    }


    protected Decorator decorator;
    protected static int SPAN_COUNT = 3;

    protected void updateItemDecoration() {
        recyclerView.removeItemDecoration(decorator);

        decorator = DecoratorFactory
                .newBuilder()
                .simple()
                .thenDecoration(new GridDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2), SPAN_COUNT))
                .andMarginStart(marginStart)
                .andMarginEnd(marginEnd)
                .andDrawEnd(drawEnd)
                .end();

        recyclerView.addItemDecoration(decorator);
    }

}
