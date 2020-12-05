package com.khupearl.smp.mentee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.khupearl.smp.MyApplication;
import com.khupearl.smp.R;
import com.khupearl.smp.databinding.ActivityMenteeMainBinding;
import com.khupearl.smp.wbs.list.WbsListActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MenteeMainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMenteeMainBinding binding;
    MyApplication myApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mentee_main);

        binding.summaryConstraintlayout.setOnClickListener(this);
        myApplication = (MyApplication)getApplication();

        setToolBar();
        init();
    }

    void init() {
        binding.teamNameTextView.setText(myApplication.getTeamName());
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.summaryConstraintlayout:
                Intent intent = new Intent(MenteeMainActivity.this, WbsListActivity.class);
                intent.putExtra("teamName", myApplication.getTeamName());
                view.getContext().startActivity(intent);
                break;
        }

    }

    private void setToolBar() {
        binding.menteeMainToolbar.setLeftButton(R.drawable.ic_setting, null);
        binding.menteeMainToolbar.setRightButton(R.drawable.ic_notification, null);
    }
}
