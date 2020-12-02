package com.khupearl.smp.wbs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WbsListActivity extends AppCompatActivity {
    private static final String TAG = "WbsListActivity";

    TextView titleTextView;

    RecyclerView wbsListRecyclerView;
    LinearLayoutManager linearLayoutManager;
    WorkAdapter workAdapter;

    ArrayList<Work> workArrayList= new ArrayList<>();

    private String teamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wbs);

        Intent intent = getIntent();
        teamName = intent.getStringExtra("teamName");

        initView();
    }

    private void initView() {
        titleTextView = findViewById(R.id.textview_wbs_title);
        wbsListRecyclerView = findViewById(R.id.recyclerview_wbslist);


        linearLayoutManager = new LinearLayoutManager(this);
        wbsListRecyclerView.setLayoutManager(linearLayoutManager);

        getWorkList(teamName);

        workAdapter = new WorkAdapter(this, workArrayList);
        wbsListRecyclerView.setAdapter(workAdapter);
    }

    private void getWorkList(final String team) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Work>> call = apiInterface.getWbsListByTeam(team);
        call.enqueue(new Callback<List<Work>>() {
            @Override
            public void onResponse(Call<List<Work>> call, Response<List<Work>> response) {
                if (response.isSuccessful()) {
                    for (Work w : response.body()) {
                        workArrayList.add(w);
                    }

                    Log.e(TAG, "서버성공 | wbs 개수 : " + workArrayList.size());
                    workAdapter = new WorkAdapter(WbsListActivity.this, workArrayList);
                    wbsListRecyclerView.setAdapter(workAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Work>> call, Throwable t) {
                Log.e(TAG, "서버에러 : " + t.getMessage());
            }
        });
    }
}
