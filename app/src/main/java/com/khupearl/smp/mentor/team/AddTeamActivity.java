package com.khupearl.smp.mentor.team;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khupearl.smp.R;
import com.khupearl.smp.databinding.ActivityAddTeamBinding;
import com.khupearl.smp.login.LoginActivity;
import com.khupearl.smp.login.MenteeSignupActivity;
import com.khupearl.smp.login.MentorSignupActivity;


public class AddTeamActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityAddTeamBinding binding;
    String input_name, input_title, input_content ,input_memberemail;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_team);
        setToolBar();
        initView();

    }
    private void initView() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        binding.memberListview.setAdapter(adapter);
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
                AddMember(input_memberemail);
                break;
            case R.id.addteam_confirm_button:
                input_name = binding.editTextTeamNameAddteam.getText().toString();
                input_title = binding.editTextTeamTitleAddteam.getText().toString();
                input_content = binding.editTextTeamContentAddteam.getText().toString();
                AddTeam(input_name,input_title,input_content);
//                Toast.makeText(AddTeamActivity.this, "멘티 회원가입.", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(this, MentorSignupActivity.class));
                break;
        }
    }


    private void TeamNameCheck(String input_teamname){

    }
    private void AddMember(String input_member_email){

    }
    private void AddTeam(String input_name, String input_title, String input_content) {
    }


    private void setToolBar() {
        binding.addteamToolbar.setTitleTextView("팀 만들기");
        binding.addteamToolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
