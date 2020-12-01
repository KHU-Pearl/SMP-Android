package com.khupearl.smp.wbs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.khupearl.smp.R;
import com.khupearl.smp.databinding.ActivityWbsdetailBinding;
import com.khupearl.smp.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class WorkDetailActivity extends AppCompatActivity {

    private static final String TAG = "WorkDetailActivity";

    ActivityWbsdetailBinding binding;

    private int wbsId;
    private int wbsIdDefault = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wbsdetail);

        Intent intent = getIntent();
        wbsId = intent.getIntExtra("wbsId", -1);
        if (wbsId == -1) {
            Log.e(TAG, "failed to get wbs id");
            Toast.makeText(WorkDetailActivity.this, "정보를 가져오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
            finish(); // wbs 가져오는데 실패함. 액티비티 종료.
        }


    }


}
