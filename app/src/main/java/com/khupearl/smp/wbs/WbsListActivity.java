package com.khupearl.smp.wbs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.khupearl.smp.SmpToolbar;
import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WbsListActivity extends AppCompatActivity {
    private static final String TAG = "WbsListActivity";

    private static final int NUM_PAGES = 3; // fragment 개수
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter; // pager adapter

    private SmpToolbar toolbar;

    private RecyclerView wbsListRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private WorkAdapter workAdapter;

    private ArrayList<Work> workArrayList= new ArrayList<>();

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
        toolbar = findViewById(R.id.wbsListToolbar);
        setToolBar();

        // view pager & tab layout 연결
        viewPager = findViewById(R.id.viewpager_wbs);
        pagerAdapter = new WbsPagerAdapter(this);;
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_wbs);
        final List<String> tabElement = Arrays.asList("예정", "진행", "완료");
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabElement.get(position));
            }
        }).attach();

//        wbsListRecyclerView = findViewById(R.id.recyclerview_wbslist);


//        linearLayoutManager = new LinearLayoutManager(this);
//        wbsListRecyclerView.setLayoutManager(linearLayoutManager);
//
//        getWorkList(teamName);
//
//        workAdapter = new WorkAdapter(this, workArrayList);
//        wbsListRecyclerView.setAdapter(workAdapter);
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

    private void setToolBar() {
        toolbar.setTitleTextView("프로젝트 작업 목록");
        toolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * pager adapter
     */
    private class WbsPagerAdapter extends FragmentStateAdapter {

        ArrayList<Fragment> fragments = new ArrayList<>();

        public WbsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            fragments.add(new TodoFragment());
            fragments.add(new InprogressFragment());
            fragments.add(new DoneFragment());
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}
