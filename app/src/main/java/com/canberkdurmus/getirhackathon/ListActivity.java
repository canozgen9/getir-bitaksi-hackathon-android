package com.canberkdurmus.getirhackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;

import com.canberkdurmus.getirhackathon.interfaces.RequestListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ListView lv;
    Button previous;
    Button next;

    String response;
    int showIndex;
    String startDate;
    String endDate;
    int minCount = -1;
    int maxCount = -1;

    ArrayList<Record> currentRecords = new ArrayList<>();
    ArrayList<Record> recordList = new ArrayList<>();
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lv = findViewById(R.id.lv);
        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);

        listAdapter = new ListAdapter(getApplicationContext(), R.layout.list_item, currentRecords);
        lv.setAdapter(listAdapter);


        Intent intent = getIntent();
        startDate = intent.getStringExtra("startDate");
        endDate = intent.getStringExtra("endDate");
        minCount = intent.getIntExtra("minCount", -1);
        maxCount = intent.getIntExtra("maxCount", -1);


        showIndex = 0;

        previous.setOnClickListener(view -> {
            if (showIndex * 10 > 0) {
                showIndex--;
                currentRecords.clear();
                for (int i = 0; i < 10; i++) {
                    currentRecords.add(recordList.get((showIndex * 10) + i));
                }

                listAdapter.notifyDataSetChanged();

            }
        });

        next.setOnClickListener(view -> {
            if (showIndex * 10 < recordList.size() - 10) {
                showIndex++;
                currentRecords.clear();
                for (int i = 0; i < 10; i++) {
                    currentRecords.add(recordList.get((showIndex * 10) + i));
                }

                listAdapter.notifyDataSetChanged();

            }
        });

        new RequestTask(this, new RequestListener() {
            @Override
            public void onSuccess(ArrayList<Record> records) {
                recordList = records;


                for (int t = 0; t < 10; t++) {
                    if (t < recordList.size()) {
                        currentRecords.add(recordList.get(t));
                    }
                }


                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String msg) {
                System.err.println(msg);
            }
        }).execute(startDate, endDate, minCount + "", maxCount + "");


    }


}
