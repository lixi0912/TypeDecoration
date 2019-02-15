package com.lixicode.typedecoration.demo;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

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
