package com.khupearl.smp.team;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.khupearl.smp.MyApplication;
import com.khupearl.smp.R;
import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.databinding.ActivityGithubBinding;
import com.khupearl.smp.mentee.MenteeMainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubActivity extends AppCompatActivity {
    ActivityGithubBinding binding;

    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_github);
        myApplication = (MyApplication)getApplication();

        setToolBar();
        initWebView();
    }

    private void initWebView() {
        WebSettings webSettings = binding.githubWebView.getSettings(); // 세부 세팅 등록
        webSettings.setJavaScriptEnabled(true); // use js
        binding.githubWebView.setWebViewClient(new WebViewClient()); //클릭시 새창 안뜨게
        webSettings.setSupportMultipleWindows(false); // 새창 띄우기 허용 여부
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); // 자바스크립트 새창 띄우기 여부
        webSettings.setUseWideViewPort(true); // 화면 사이즈 맞추기 허용 여부
        webSettings.setSupportZoom(true); // 화면 줌 허용 여부
        webSettings.setBuiltInZoomControls(true); // 화면 확대 축소 허용 여부

        loadGithubWebView();
    }

    private void loadGithubWebView() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Team> call = apiInterface.getTeamUrl(myApplication.getTeamName());
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if(response.body().getSuccess())
                {
                    binding.githubWebView.loadUrl(response.body().getGithub());
                }
                else
                {
                    Toast.makeText(GithubActivity.this, "서버실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(GithubActivity.this, "서버 실패!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setToolBar() {
        binding.githubToolbar.setTitleTextView("Team GitHub");
        binding.githubToolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
