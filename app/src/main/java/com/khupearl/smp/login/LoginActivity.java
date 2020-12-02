package com.khupearl.smp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.mentee.Mentee;
import com.khupearl.smp.R;
import com.khupearl.smp.mentee.MenteeMainActivity;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_ID, editText_password;
    private Button loginButton,menteesignupButton, mentorsignupbutton;
    String email, fk_team, name, password, major, student_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_ID = findViewById(R.id.editTextTextEmailAddress);
        editText_password = findViewById(R.id.editTextTextPassword);

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        menteesignupButton = findViewById(R.id.mentee_signup_button);
        menteesignupButton.setOnClickListener(this);
        mentorsignupbutton = findViewById(R.id.mentor_signup_button);
        mentorsignupbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                String input_email = editText_ID.getText().toString();
                String input_password = editText_password.getText().toString();
                Login(input_email,input_password);
                break;
            case R.id.mentee_signup_button:
                startActivity(new Intent(this, MenteeSignupActivity.class));
                Toast.makeText(LoginActivity.this, "멘티 회원가입.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mentor_signup_button:
                startActivity(new Intent(this, MentorSignupActivity.class));
                Toast.makeText(LoginActivity.this, "멘토 회원가입.", Toast.LENGTH_SHORT).show();
                break;

        }
    }
    private void Login(String input_email, String input_password){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentee> call = apiInterface.loginMentee(input_email, input_password);
        call.enqueue(new Callback<Mentee>() {
            @Override
            public void onResponse(Call<Mentee> call, Response<Mentee> response) {
                if(response.body().getSuccess())
                {
                    email = response.body().getEmail();
                    fk_team=response.body().getFk_team();
                    name = response.body().getName();
                    password = response.body().getPassword();
                    major = response.body().getMajor() ;
                    student_id = response.body().getStudent_id();
                    Toast.makeText(LoginActivity.this, "멘티로 로그인되었습니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MenteeMainActivity.class));

                }
                else{
                    Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentee> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage()+"서버 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
