package com.khupearl.smp.mentor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.khupearl.smp.MyApplication;
import com.khupearl.smp.R;
import com.khupearl.smp.databinding.ActivityMentorMainBinding;
import com.khupearl.smp.team.TeamListActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MentorMainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMentorMainBinding binding;
    MyApplication myApplication;
    String mentorName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mentor_main);

        Intent intent = getIntent();
        mentorName = intent.getStringExtra("mentorName");

        binding.menteeListConstraintLayout.setOnClickListener(this);
        binding.noticeConstraintLayout.setOnClickListener(this);
        myApplication = (MyApplication)getApplication();

        setToolBar();
        init();
    }

    void init() {
        binding.mentorNameTextView.setText(mentorName + " 멘토님");
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.menteeListConstraintLayout:
                Intent intent = new Intent(MentorMainActivity.this, TeamListActivity.class);
                view.getContext().startActivity(intent);
                break;
            case R.id.noticeConstraintLayout:
                // TODO: 06/12/2020 공지사항 액티비티로 이동 ~~
                break;
        }
    }

    private void setToolBar() {
        binding.mentorMainToolbar.setLeftButton(R.drawable.ic_setting, null);
        binding.mentorMainToolbar.setRightButton(R.drawable.ic_notification, null);
    }
}
