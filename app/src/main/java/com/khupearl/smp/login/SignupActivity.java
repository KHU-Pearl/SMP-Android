package com.khupearl.smp.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.khupearl.smp.R;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText_ID, editText_password, editText_name, editText_major;
    private Button IdcheckButton,SignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editText_ID = findViewById(R.id.editTextTextEmailAddress_signup);
        editText_password = findViewById(R.id.editTextTextPassword_signup);
        editText_name = findViewById(R.id.editTextName_signup);
        editText_major = findViewById(R.id.editTextMajor_signup);


        IdcheckButton = findViewById(R.id.id_check_button);
        SignupButton = findViewById(R.id.signup_confirm_button);
        SignupButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_confirm_button:
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                Toast.makeText(SignupActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
