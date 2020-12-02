package com.khupearl.smp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.khupearl.smp.R;
import com.khupearl.smp.SmpToolbar;

public class MentorSignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_email, editText_password, editText_name;
    private Button IdcheckButton,SignupButton;
    private SmpToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_mentor);
        editText_email = findViewById(R.id.editTextTextEmailAddress_mentorsignup);
        editText_password = findViewById(R.id.editTextTextPassword_mentorsignup);
        editText_name = findViewById(R.id.editTextName_mentorsignup);
        toolbar = findViewById(R.id.mentorsignupToolbar);

        IdcheckButton = findViewById(R.id.mentor_id_check_button);
        SignupButton = findViewById(R.id.mentorsignup_confirm_button);
        SignupButton.setOnClickListener(this);

        setToolBar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mentorsignup_confirm_button:
                startActivity(new Intent(MentorSignupActivity.this, LoginActivity.class));
                Toast.makeText(MentorSignupActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mentor_id_check_button:
                break;
        }
    }

    private void setToolBar() {
        toolbar.setTitleTextView("멘토 회원가입");
        toolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
