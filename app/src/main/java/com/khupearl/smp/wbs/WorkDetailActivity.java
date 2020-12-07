package com.khupearl.smp.wbs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.R;
import com.khupearl.smp.databinding.ActivityWbsdetailBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "WorkDetailActivity";

    ActivityWbsdetailBinding binding;

    private int wbsId;
    private int wbsIdDefault = -1;
    private String nextState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wbsdetail);

        Intent intent = getIntent();
        wbsId = intent.getIntExtra("wbsId", wbsIdDefault);

        if (wbsId == -1) {
            Log.e(TAG, "failed to get wbs id");
            Toast.makeText(WorkDetailActivity.this, "정보를 가져오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
            finish(); // wbs 가져오는데 실패함. 액티비티 종료.
        } else {
            Log.e(TAG, "success to get wbs id : " + wbsId);
            getWorkDeatil(); // 레트로핏으로 서버에서 값을 받아서 View에 적
        }

        init();
    }

    private void init() {
//        StringBuilder sb = new StringBuilder().append(nextState).append("로 변경");
//        binding.changeStateButton.setText(sb);
        binding.changeStateButton.setOnClickListener(this);
    }

    private void getWorkDeatil() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Work> call = apiInterface.getWorkById(wbsId);
        call.enqueue(new Callback<Work>() {
            @Override
            public void onResponse(Call<Work> call, Response<Work> response) {
                if (response.body().isSuccess())
                {
                    setToolBar(response.body().getTitle());
                    binding.contentDetailTextView.setText(response.body().getContent());
                    binding.fieldDetailTextView.setText(response.body().getField());
                    binding.timeDetailTextView.setText(response.body().getDate());
                    // TODO: 04/12/2020 투입인력 추가

                    String text = response.body().getState();
                    binding.wbsDetailToolbar.setToolbarBadgeTextView(text);

                    if (text.equals("예정")) nextState = "진행";
                    else if (text.equals("진행")) nextState = "완료";
                    else nextState = "예정";
                    Log.e(TAG, "서버성공 : (title) " + response.body().getTitle());
                }
            }

            @Override
            public void onFailure(Call<Work> call, Throwable t) {
                Log.e(TAG, "서버에러 : " + t.getMessage());
            }
        });
    }

    private void setToolBar(String title) {
        binding.wbsDetailToolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.wbsDetailToolbar.setTitleTextView(title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeStateButton:
                changeWorkState(nextState);
                break;
        }

    }

    private void changeWorkState(final String nextState) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Work> call = apiInterface.setState(wbsId, nextState);
        call.enqueue(new Callback<Work>() {
            @Override
            public void onResponse(Call<Work> call, Response<Work> response) {
                if (response.body().isSuccess())
                {
                    binding.wbsDetailToolbar.setToolbarBadgeTextView(nextState);
                    Toast.makeText(WorkDetailActivity.this, "변경되었습니다.", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "state 변경 성공!");

                    getWorkDeatil(); // 화면 갱
                }
            }

            @Override
            public void onFailure(Call<Work> call, Throwable t) {
                Toast.makeText(WorkDetailActivity.this, "변경되지않았습니다.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "서버에러 : " + t.getMessage());
            }
        });
    }
}
