package com.lixicode.typedecoration.demo;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.lixicode.typedecoration.Decorator;
import com.lixicode.typedecoration.conditions.MultiTypeCondition;
import com.lixicode.typedecoration.decorations.GridDecoration;
import com.lixicode.typedecoration.decorations.MultiTypeDecoration;
import com.lixicode.typedecoration.utils.DecorationUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            final PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_ACTIVITIES);


            List<CharSequence> charSequences = new ArrayList<>();
            for (ActivityInfo activity : info.activities) {
                charSequences.add(activity.loadLabel(getPackageManager()));
            }

            setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, charSequences));

            getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ActivityInfo target = info.activities[i];
                    Intent intent = new Intent();
                    intent.setClassName(getPackageName(), target.name);
                    startActivity(intent);
                }
            });
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }
}
