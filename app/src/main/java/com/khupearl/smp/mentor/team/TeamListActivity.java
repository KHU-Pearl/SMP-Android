package com.khupearl.smp.mentor.team;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.khupearl.smp.MyApplication;
import com.khupearl.smp.R;
import com.khupearl.smp.SmpToolbar;
import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.mentor.Mentor;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamListActivity extends AppCompatActivity {
    MyApplication myApp;
    RecyclerView teamListRecyclerView;
    LinearLayoutManager linearLayoutManager;
    TeamAdapter menteeAdapter;
    private SmpToolbar toolbar;
    List<Team> t;

    ArrayList<Team> menteeArrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamlist);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMenteeTemp();
    }

    private void initView() {
        t = new ArrayList<Team>();;
        myApp = (MyApplication)getApplication();
        teamListRecyclerView = findViewById(R.id.recyclerview_teamlist);
        toolbar = findViewById(R.id.teamListToolbar);

        linearLayoutManager = new LinearLayoutManager(this);
        teamListRecyclerView.setLayoutManager(linearLayoutManager);

        setToolBar();
    }
    private void setMenteeTemp() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentor> call = apiInterface.getTeamListByMentor(myApp.getEmail());
        call.enqueue(new Callback<Mentor>() {
            @Override
            public void onResponse(Call<Mentor> call, Response<Mentor> response) {
                if(response.body().getSuccess())
                {
                    menteeArrayList.clear();
                    for (Team o : response.body().getTeams()) {
                        menteeArrayList.add(o);
                    }
                    menteeAdapter = new TeamAdapter(TeamListActivity.this, menteeArrayList);
                    teamListRecyclerView.setAdapter(menteeAdapter);
                }
                else
                {
                    Toast.makeText(TeamListActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentor> call, Throwable t) {
                Toast.makeText(TeamListActivity.this, "서버 실패!.", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setMenteeTemp1() {
        // TODO: 02/12/2020 DB연결
//        menteeArrayList.add(new Team("진주라디오", "4차 산업 어쩌구"));
//        menteeArrayList.add(new Team("진주티비", "4차 산업 어쩌구"));
//        menteeArrayList.add(new Team("진주핸드폰", "4차 산업 어쩌구"));
        for(int i = 0; i < t.size(); i++){
            menteeArrayList.add(new Team(t.get(i).getName(),t.get(i).getTitle()));
            Toast.makeText(TeamListActivity.this, t.get(i).getName(), Toast.LENGTH_SHORT).show();

        }



    }

    private void setToolBar() {
        toolbar.setTitleTextView("멘티 목록");
        toolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar.setRightButton(R.drawable.ic_baseline_add_24, new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(TeamListActivity.this, AddTeamActivity.class));
            }
        });
    }

}
