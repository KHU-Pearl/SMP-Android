package com.khupearl.smp.mentor.team;

import android.os.Bundle;
import android.widget.TextView;

import com.khupearl.smp.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TeamListActivity extends AppCompatActivity {

    TextView titleTextView;
    RecyclerView teamListRecyclerView;
    LinearLayoutManager linearLayoutManager;
    TeamAdapter menteeAdapter;

    ArrayList<Team> menteeArrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menteelist);

        initView();
    }

    private void initView() {
        titleTextView = findViewById(R.id.textview_teamlist);
        teamListRecyclerView = findViewById(R.id.recyclerview_teamlist);


        linearLayoutManager = new LinearLayoutManager(this);
        teamListRecyclerView.setLayoutManager(linearLayoutManager);

        setMenteeTemp();

        menteeAdapter = new TeamAdapter(this, menteeArrayList);
        teamListRecyclerView.setAdapter(menteeAdapter);
    }

    private void setMenteeTemp() {
        menteeArrayList.add(new Team("진주라디오", "4차 산업 어쩌구"));
        menteeArrayList.add(new Team("진주티비", "4차 산업 어쩌구"));
        menteeArrayList.add(new Team("진주핸드폰", "4차 산업 어쩌구"));
    }
}
