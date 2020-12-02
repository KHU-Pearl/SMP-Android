package com.khupearl.smp.mentor.team;

import android.os.Bundle;
import android.view.View;

import com.khupearl.smp.R;
import com.khupearl.smp.SmpToolbar;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TeamListActivity extends AppCompatActivity {

    RecyclerView teamListRecyclerView;
    LinearLayoutManager linearLayoutManager;
    TeamAdapter menteeAdapter;
    private SmpToolbar toolbar;

    ArrayList<Team> menteeArrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamlist);

        initView();
    }

    private void initView() {
        teamListRecyclerView = findViewById(R.id.recyclerview_teamlist);
        toolbar = findViewById(R.id.teamListToolbar);

        linearLayoutManager = new LinearLayoutManager(this);
        teamListRecyclerView.setLayoutManager(linearLayoutManager);

        setToolBar();

        setMenteeTemp();

        menteeAdapter = new TeamAdapter(this, menteeArrayList);
        teamListRecyclerView.setAdapter(menteeAdapter);
    }

    private void setMenteeTemp() {
        // TODO: 02/12/2020 DB연결
        menteeArrayList.add(new Team("진주라디오", "4차 산업 어쩌구"));
        menteeArrayList.add(new Team("진주티비", "4차 산업 어쩌구"));
        menteeArrayList.add(new Team("진주핸드폰", "4차 산업 어쩌구"));
    }

    private void setToolBar() {
        toolbar.setTitleTextView("멘티 목록");
        toolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
