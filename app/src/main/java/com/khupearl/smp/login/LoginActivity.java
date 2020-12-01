package com.khupearl.smp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khupearl.smp.R;
import com.khupearl.smp.mentee.MenteeMainActivity;
import com.khupearl.smp.mentor.team.MenteeListActivity;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_ID, editText_password;
    private Button loginButton,signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_ID = findViewById(R.id.editTextTextEmailAddress);
        editText_password = findViewById(R.id.editTextTextPassword);

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);
        signupButton = findViewById(R.id.signup_button);
        signupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                startActivity(new Intent(LoginActivity.this, MenteeMainActivity.class));
                Toast.makeText(LoginActivity.this, "멘티로 로그인되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.signup_button:
                startActivity(new Intent(this, SignupActivity.class));
                Toast.makeText(LoginActivity.this, "회원가입.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
