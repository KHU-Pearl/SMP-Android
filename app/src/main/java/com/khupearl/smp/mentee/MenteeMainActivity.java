package com.khupearl.smp.mentee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.khupearl.smp.R;
import com.khupearl.smp.databinding.ActivityMenteeMainBinding;
import com.khupearl.smp.wbs.WbsListActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MenteeMainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMenteeMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mentee_main);

        binding.summaryConstraintlayout.setOnClickListener(this);

        setToolBar();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.summaryConstraintlayout:
                Intent intent = new Intent(MenteeMainActivity.this, WbsListActivity.class);
                // TODO: 02/12/2020 teamname 하드코딩된부분 수정해야됨
                intent.putExtra("teamName", "진주라디오");
                view.getContext().startActivity(intent);
                break;
        }

    }

    private void setToolBar() {
        binding.menteeMainToolbar.setLeftButton(R.drawable.ic_setting, null);
        binding.menteeMainToolbar.setRightButton(R.drawable.ic_notification, null);
    }
}
