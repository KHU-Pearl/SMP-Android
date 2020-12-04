package com.khupearl.smp.wbs.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.khupearl.smp.SmpToolbar;
import com.khupearl.smp.R;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class WbsListActivity extends AppCompatActivity {
    private static final String TAG = "WbsListActivity";

    private static final int NUM_PAGES = 3; // fragment 개수
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter; // pager adapter

    private SmpToolbar toolbar;

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
        viewPager.unregisterOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {

                super.onPageSelected(position);
                pagerAdapter.notifyItemChanged(position);
            }

        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout_wbs);
        final List<String> tabElement = Arrays.asList("예정", "진행", "완료");
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabElement.get(position));
            }
        }).attach();
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

        public WbsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position%NUM_PAGES) {
                default:
                    return new TodoFragment(teamName);
                case 1:
                    return new InprogressFragment(teamName);
                case 2:
                    return new DoneFragment(teamName);
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}
