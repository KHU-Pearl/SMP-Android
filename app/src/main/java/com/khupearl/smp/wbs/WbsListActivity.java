package com.khupearl.smp.wbs;

import android.os.Bundle;
import android.widget.TextView;

import com.khupearl.smp.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WbsListActivity extends AppCompatActivity {

    TextView titleTextView;

    RecyclerView wbsListRecyclerView;
    LinearLayoutManager linearLayoutManager;
    WorkAdapter workAdapter;

    ArrayList<Work> workArrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wbs);

        initView();
    }

    private void initView() {
        titleTextView = findViewById(R.id.textview_wbs_title);
        wbsListRecyclerView = findViewById(R.id.recyclerview_wbslist);


        linearLayoutManager = new LinearLayoutManager(this);
        wbsListRecyclerView.setLayoutManager(linearLayoutManager);

        setWorkTemp();

        workAdapter = new WorkAdapter(this, workArrayList);
        wbsListRecyclerView.setAdapter(workAdapter);
    }

    private void setWorkTemp() {
        workArrayList.add(new Work("UI구현하기", "안드로이드"));
        workArrayList.add(new Work("창업기획서작성", "기획"));
        workArrayList.add(new Work("기능개발", "안드로이드"));
    }
}
