package com.khupearl.smp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khupearl.smp.R;
import com.khupearl.smp.SmpToolbar;
import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.mentee.Mentee;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenteeSignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_email, editText_password, editText_name, editText_major, editText_student_id;
    private Button IdcheckButton,SignupButton;
    private boolean check_possible;
    private SmpToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_mentee);
        check_possible = false;
        editText_email = findViewById(R.id.editTextTextEmailAddress_menteesignup);
        editText_password = findViewById(R.id.editTextTextPassword_menteesignup);
        editText_name = findViewById(R.id.editTextName_menteesignup);
        editText_major = findViewById(R.id.editTextMajor_menteesignup);
        editText_student_id = findViewById(R.id.editTextID_menteesignup);
        toolbar = findViewById(R.id.menteesignupToolbar);

        IdcheckButton = findViewById(R.id.mentee_id_check_button);
        IdcheckButton.setOnClickListener(this);
        SignupButton = findViewById(R.id.menteesignup_confirm_button);
        SignupButton.setOnClickListener(this);

        setToolBar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menteesignup_confirm_button:
                String input_email = editText_email.getText().toString();
                String input_password = editText_password.getText().toString();
                String input_name = editText_name.getText().toString();
                String input_major = editText_major.getText().toString();
                String input_student_id = editText_student_id.getText().toString();
                if(check_possible)  Register(input_email,input_password, input_name, input_major, Integer.parseInt(input_student_id));
                else Toast.makeText(MenteeSignupActivity.this, "아이디 중복확인이 되지 않았습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mentee_id_check_button:
                CheckPossible(editText_email.getText().toString());

                break;
        }
    }

    private void CheckPossible(String input_email) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentee> call = apiInterface.RegisterMenteePossible(input_email);
        call.enqueue(new Callback<Mentee>() {
            @Override
            public void onResponse(Call<Mentee> call, Response<Mentee> response) {
                if(response.body().getSuccess())
                {
                    if(response.body().getEmpty())
                    {
                        Toast.makeText(MenteeSignupActivity.this, "가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                        check_possible = true;
                    }
                    else
                    {
                        Toast.makeText(MenteeSignupActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                        check_possible = false;
                    }
                }
                else
                {
                    Toast.makeText(MenteeSignupActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentee> call, Throwable t) {
                Toast.makeText(MenteeSignupActivity.this, "서버 실패!.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Register(String input_email, String input_password, String input_name, String input_major, int input_student_id) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentee> call = apiInterface.RegisterMentee(input_email, input_password, input_name, input_major, input_student_id);
        call.enqueue(new Callback<Mentee>() {
            @Override
            public void onResponse(Call<Mentee> call, Response<Mentee> response) {
                if(response.body().getSuccess())
                {
                    Toast.makeText(MenteeSignupActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MenteeSignupActivity.this, LoginActivity.class));

                }
                else
                {
                    Toast.makeText(MenteeSignupActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentee> call, Throwable t) {
                Toast.makeText(MenteeSignupActivity.this, "회원가입 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setToolBar() {
        toolbar.setTitleTextView("멘티 회원가입");
        toolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
