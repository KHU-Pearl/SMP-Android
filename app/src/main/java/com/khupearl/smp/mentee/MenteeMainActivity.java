package com.khupearl.smp.mentee;

import android.os.Bundle;

import com.khupearl.smp.R;
import com.khupearl.smp.databinding.ActivityMenteeMainBinding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class MenteeMainActivity extends AppCompatActivity {

    ActivityMenteeMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mentee_main);
    }


}
