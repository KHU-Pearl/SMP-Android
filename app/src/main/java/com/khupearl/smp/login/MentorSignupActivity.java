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
import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.mentee.Mentee;
import com.khupearl.smp.mentor.Mentor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                String input_email = editText_email.getText().toString();
                String input_password = editText_password.getText().toString();
                String input_name = editText_name.getText().toString();
                Register(input_email,input_password, input_name);
                Toast.makeText(MentorSignupActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mentor_id_check_button:
                break;
        }
    }
    private void Register(String input_email, String input_password, String input_name) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentor> call = apiInterface.RegisterMentor(input_email, input_password, input_name);
        call.enqueue(new Callback<Mentor>() {
            @Override
            public void onResponse(Call<Mentor> call, Response<Mentor> response) {
                if(response.body().getSuccess())
                {
                    Toast.makeText(MentorSignupActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MentorSignupActivity.this, LoginActivity.class));
                }
                else
                {
                    Toast.makeText(MentorSignupActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentor> call, Throwable t) {
                Toast.makeText(MentorSignupActivity.this, "회원가입 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });

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
