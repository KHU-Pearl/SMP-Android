package com.khupearl.smp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.khupearl.smp.databinding.ActivitySettingBinding;
import com.khupearl.smp.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySettingBinding binding;
    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);

        myApplication = (MyApplication)getApplication();
        init();
    }

    private void init() {
        binding.infoConstraintLayout.setOnClickListener(this);
        binding.logoutConstraintLayout.setOnClickListener(this);

        binding.versionTextView.setText(BuildConfig.VERSION_NAME);

        setToolBar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.infoConstraintLayout:
                Toast.makeText(SettingActivity.this, "준비중입니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logoutConstraintLayout:
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                appInit();
                Toast.makeText(SettingActivity.this, "로그아웃", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    private void setToolBar() {
        binding.settingToolbar.setTitleTextView(getString(R.string.setting_title));

        binding.settingToolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void appInit() {
        myApplication.setLogin_type("");
        myApplication.setEmail("");
        myApplication.setTeamName("");
    }
}
