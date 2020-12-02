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
        wbsId = intent.getIntExtra("wbsId", wbsIdDefault);
        if (wbsId == -1) {
            Log.e(TAG, "failed to get wbs id");
            Toast.makeText(WorkDetailActivity.this, "정보를 가져오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
            finish(); // wbs 가져오는데 실패함. 액티비티 종료.
        } else {
            Log.e(TAG, "success to get wbs id : " + wbsId);
            getWorkDeatil(wbsId); // 레트로핏으로 서버에서 값을 받아옴
        }
    }

    private void getWorkDeatil(int id) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Work> call = apiInterface.getWorkById(id);
        call.enqueue(new Callback<Work>() {
            @Override
            public void onResponse(Call<Work> call, Response<Work> response) {
                if (response.body().isSuccess())
                {
                    setToolBar(response.body().getTitle());
                    binding.contentDetailTextView.setText(response.body().getContent());
                    binding.fieldDetailTextView.setText(response.body().getField());
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
}
