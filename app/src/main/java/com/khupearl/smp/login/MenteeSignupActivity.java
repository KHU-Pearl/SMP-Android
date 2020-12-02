package com.khupearl.smp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khupearl.smp.R;
import com.khupearl.smp.SmpToolbar;

import androidx.appcompat.app.AppCompatActivity;

public class MenteeSignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_email, editText_password, editText_name, editText_major, editText_student_id;
    private Button IdcheckButton,SignupButton;
    private SmpToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_mentee);
        editText_email = findViewById(R.id.editTextTextEmailAddress_menteesignup);
        editText_password = findViewById(R.id.editTextTextPassword_menteesignup);
        editText_name = findViewById(R.id.editTextName_menteesignup);
        editText_major = findViewById(R.id.editTextMajor_menteesignup);
        editText_student_id = findViewById(R.id.editTextID_menteesignup);
        toolbar = findViewById(R.id.menteesignupToolbar);

        IdcheckButton = findViewById(R.id.mentee_id_check_button);
        SignupButton = findViewById(R.id.menteesignup_confirm_button);
        SignupButton.setOnClickListener(this);

        setToolBar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menteesignup_confirm_button:
                startActivity(new Intent(MenteeSignupActivity.this, LoginActivity.class));
                Toast.makeText(MenteeSignupActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mentee_id_check_button:
                break;
        }
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
