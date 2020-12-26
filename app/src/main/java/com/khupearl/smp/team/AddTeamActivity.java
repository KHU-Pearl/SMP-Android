package com.khupearl.smp.team;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.khupearl.smp.MyApplication;
import com.khupearl.smp.R;
import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.databinding.ActivityAddTeamBinding;
import com.khupearl.smp.mentee.Mentee;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddTeamActivity extends AppCompatActivity implements View.OnClickListener{
    MyApplication myApp;
    ArrayAdapter adapter;
    ActivityAddTeamBinding binding;
    ArrayList<String> members;
    String input_name, input_title, input_content ,input_memberemail;
    boolean check_possible;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_team);
        setToolBar();
        initView();

    }
    private void initView() {
        myApp = (MyApplication)getApplication();
        check_possible = false;
        members = new ArrayList<String>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,members);
        binding.memberListview.setAdapter(adapter);
        binding.teamNameCheckButton.setOnClickListener(this);
        binding.addMemberButton.setOnClickListener(this);
        binding.addteamConfirmButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.team_name_check_button:
                input_name = binding.editTextTeamNameAddteam.getText().toString();
                TeamNameCheck(input_name);
                break;
            case R.id.add_member_button:
                input_memberemail = binding.editTexMemberEmailAddteam.getText().toString();
                if(!input_memberemail.equals("") && !members.contains(input_memberemail)) AddMember(input_memberemail);
                break;
            case R.id.addteam_confirm_button:
                input_name = binding.editTextTeamNameAddteam.getText().toString();
                input_title = binding.editTextTeamTitleAddteam.getText().toString();
                input_content = binding.editTextTeamContentAddteam.getText().toString();
                if(check_possible) {
                    AddTeam(input_name, input_title, input_content);
                    AddTeamMentor(input_name);
                    for(int i = 0; i < members.size(); i++) {
                        AddTeamMentee(members.get(i),input_name);
                    }
                }
                startActivity(new Intent(this, TeamListActivity.class));
                break;
        }
    }



    private void TeamNameCheck(String input_teamname){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Team> call = apiInterface.AddTeamNamePossible(input_teamname);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if(response.body().getSuccess())
                {
                    if(response.body().getEmpty())
                    {
                        Toast.makeText(AddTeamActivity.this, "가능한 팀명입니다.", Toast.LENGTH_SHORT).show();
                        check_possible = true;
                    }
                    else
                    {
                        Toast.makeText(AddTeamActivity.this, "이미 존재하는 팀명입니다.", Toast.LENGTH_SHORT).show();
                        check_possible = false;
                    }
                }
                else
                {
                    Toast.makeText(AddTeamActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(AddTeamActivity.this, "서버 실패!.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AddMember(final String input_member_email){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentee> call = apiInterface.RegisterMenteePossible(input_member_email);
        call.enqueue(new Callback<Mentee>() {
            @Override
            public void onResponse(Call<Mentee> call, Response<Mentee> response) {
                if(response.body().getSuccess())
                {
                    if(response.body().getEmpty())
                    {
                        Toast.makeText(AddTeamActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //리스트뷰에 추가
                        members.add(input_member_email);
                        binding.editTexMemberEmailAddteam.setText("");
                        adapter.notifyDataSetChanged();
                    }
                }
                else
                {
                    Toast.makeText(AddTeamActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentee> call, Throwable t) {
                Toast.makeText(AddTeamActivity.this, "서버 실패!.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AddTeam(String input_name, String input_title, String input_content) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Team> call = apiInterface.AddTeam(input_name,input_title,input_content);
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if(response.body().getSuccess())
                {
                    Toast.makeText(AddTeamActivity.this, "팀이 생성되었습니다.", Toast.LENGTH_SHORT).show();
                    check_possible = false;
                }
                else
                {
                    Toast.makeText(AddTeamActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(AddTeamActivity.this, "서버 실패!.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AddTeamMentor(String input_name){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Team> call = apiInterface.AddTeamMentor(input_name,myApp.getEmail());
        call.enqueue(new Callback<Team>() {
            @Override
            public void onResponse(Call<Team> call, Response<Team> response) {
                if(response.body().getSuccess())
                {
                    Toast.makeText(AddTeamActivity.this, "멘토에 팀이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    check_possible = false;
                }
                else
                {
                    Toast.makeText(AddTeamActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Team> call, Throwable t) {
                Toast.makeText(AddTeamActivity.this, "서버 실패!.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AddTeamMentee(String input_email, String input_name) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentee> call = apiInterface.AddTeamMentee(input_email, input_name);
        call.enqueue(new Callback<Mentee>() {
            @Override
            public void onResponse(Call<Mentee> call, Response<Mentee> response) {
                if(response.body().getSuccess())
                {
                    Toast.makeText(AddTeamActivity.this, "멘티팀성공.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AddTeamActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentee> call, Throwable t) {
                Toast.makeText(AddTeamActivity.this, "서버 실패!.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setToolBar() {
        binding.addteamToolbar.setTitleTextView(getString(R.string.add_team));
        binding.addteamToolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
