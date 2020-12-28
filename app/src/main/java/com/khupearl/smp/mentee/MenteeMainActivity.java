package com.khupearl.smp.mentee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.khupearl.smp.MyApplication;
import com.khupearl.smp.R;
import com.khupearl.smp.SettingActivity;
import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.databinding.ActivityMenteeMainBinding;
import com.khupearl.smp.team.GithubActivity;
import com.khupearl.smp.team.Team;
import com.khupearl.smp.wbs.list.WbsListActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenteeMainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMenteeMainBinding binding;
    MyApplication myApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mentee_main);

        binding.summaryConstraintlayout.setOnClickListener(this);
        binding.githubButton.setOnClickListener(this);
        myApplication = (MyApplication)getApplication();

        setToolBar();
        init();
    }

    void init() {
        getTeamInfo();
        getTeamCountWbs();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.summaryConstraintlayout:
                Intent intent = new Intent(MenteeMainActivity.this, WbsListActivity.class);
                intent.putExtra("teamName", myApplication.getTeamName());
                view.getContext().startActivity(intent);
                break;
            case R.id.githubButton:
                Intent intent2 = new Intent(MenteeMainActivity.this, GithubActivity.class);
                view.getContext().startActivity(intent2);
        }

    }

    private void setToolBar() {
        binding.menteeMainToolbar.setLeftButton(R.drawable.ic_setting, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenteeMainActivity.this, SettingActivity.class));
            }
        });
        binding.menteeMainToolbar.setRightButton(R.drawable.ic_notification, null);
    }

    private void getTeamInfo() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Team> call = apiInterface.getTeamInfo(myApplication.getTeamName());
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if(response.body().getSuccess())
                {
                    binding.teamNameTextView.setText(response.body().getName());
                    binding.summaryTitleTextView.setText(response.body().getTitle());
                    binding.summaryContentTextView.setText(response.body().getContent());
                }
                else
                {
                    Toast.makeText(MenteeMainActivity.this, "서버실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(MenteeMainActivity.this, "서버 실패!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTeamCountWbs() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Progress> call = apiInterface.getTeamCountWbs(myApplication.getTeamName());
        call.enqueue(new Callback<Progress>() {
            @Override
            public void onResponse(Call<Progress> call, Response<Progress> response) {
                if(response.body().isSuccess())
                {
                    binding.todoNumTextView.setText(response.body().getCount_todo());
                    binding.inprogressNumTextView.setText(response.body().getCount_inprogress());
                    binding.doneNumTextView.setText(response.body().getCount_done());

                    int maxCnt = Integer.parseInt(response.body().getCount_todo())
                            + Integer.parseInt(response.body().getCount_inprogress())
                            + Integer.parseInt(response.body().getCount_done());
                    int progressCnt = Integer.parseInt(response.body().getCount_done());

                    binding.menteeProgressBar.setProgress(progressCnt);
                    binding.menteeProgressBar.setMax(maxCnt);
                }
                else
                {
                    Toast.makeText(MenteeMainActivity.this, "서버실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Progress> call, Throwable t) {
                Toast.makeText(MenteeMainActivity.this, "서버 실패!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void get_notice_list(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentee> call = apiInterface.mentee_notice_list(myApplication.getTeamName());
        call.enqueue(new Callback<Mentee>() {
            @Override
            public void onResponse(Call<Mentee> call, Response<Mentee> response) {
                if(response.body().getSuccess())
                {
                    String s = "";
                    int m = 3;
                    if(response.body().getNotices().size()<3) m =response.body().getNotices().size();
                    for(int i = 0; i < m; i ++){
                        s +=response.body().getNotices().get(i).getTitle();
                        if(i!=m-1)  s+="\n";
                    }
//                    for (Notice n : response.body().getNotices()) {
//                        Toast.makeText(MenteeMainActivity.this, n.getTitle(), Toast.LENGTH_SHORT).show();
//                    }
                    binding.noticeContentTextView.setText(s);

                }
                else
                {
                    Toast.makeText(MenteeMainActivity.this, "서버실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentee> call, Throwable t) {
                Toast.makeText(MenteeMainActivity.this, "서버실패"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        get_notice_list();
    }
}
