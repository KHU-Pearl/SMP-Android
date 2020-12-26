package com.khupearl.smp.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.khupearl.smp.R;
import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.databinding.ActivitySignupBinding;
import com.khupearl.smp.mentee.Mentee;
import com.khupearl.smp.mentor.Mentor;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySignupBinding binding;

    private boolean check_possible;
    boolean isMentee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);

        Intent intent = getIntent();
        isMentee = intent.getStringExtra("userType").equals("mentee") ? true : false;

        check_possible = false;

        init();
    }

    private void init() {
        if (!isMentee) {
            binding.editTextMajor.setVisibility(View.GONE);
            binding.editTextID.setVisibility(View.GONE);
            Log.d("SignUpActivity", "멘토 회원가입");
        }

        binding.checkIdbutton.setOnClickListener(this);
        binding.confirmButton.setOnClickListener(this);

        setToolBar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_button:
                if(check_possible) {
                    String input_email = binding.editTextEmailAddress.getText().toString();
                    String input_password = binding.editTextPassword.getText().toString();
                    String input_name = binding.editTextName.getText().toString();

                    if (isMentee) {
                        String input_major = binding.editTextMajor.getText().toString();
                        String input_student_id = binding.editTextID.getText().toString();

                        RegisterMentee(input_email,input_password, input_name, input_major, Integer.parseInt(input_student_id));
                    } else {
                        RegisterMentor(input_email,input_name, input_password);
                    }

                } else {
                    Toast.makeText(SignupActivity.this, "아이디 중복확인이 되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.checkIdbutton:
                if (isMentee) CheckPossibleMentee(binding.editTextEmailAddress.getText().toString());
                else CheckPossibleMentor(binding.editTextEmailAddress.getText().toString());
                break;
        }
    }

    private void setToolBar() {
        if (isMentee) binding.signupToolbar.setTitleTextView(getString(R.string.mentee_signup));
        else binding.signupToolbar.setTitleTextView(getString(R.string.mentor_signup));

        binding.signupToolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void CheckPossibleMentee(String input_email) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentee> call = apiInterface.RegisterMenteePossible(input_email);
        call.enqueue(new Callback<Mentee>() {
            @Override
            public void onResponse(Call<Mentee> call, Response<Mentee> response) {
                if(response.body().getSuccess())
                {
                    if(response.body().getEmpty())
                    {
                        Toast.makeText(SignupActivity.this, "가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                        check_possible = true;
                    }
                    else
                    {
                        Toast.makeText(SignupActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                        check_possible = false;
                    }
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentee> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "서버 실패!.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void RegisterMentee(String input_email, String input_password, String input_name, String input_major, int input_student_id) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentee> call = apiInterface.RegisterMentee(input_email, input_password, input_name, input_major, input_student_id);
        call.enqueue(new Callback<Mentee>() {
            @Override
            public void onResponse(Call<Mentee> call, Response<Mentee> response) {
                if(response.body().getSuccess())
                {
                    Toast.makeText(SignupActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));

                }
                else
                {
                    Toast.makeText(SignupActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentee> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "회원가입 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CheckPossibleMentor(String input_email) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentor> call = apiInterface.RegisterMentorPossible(input_email);
        call.enqueue(new Callback<Mentor>() {
            @Override
            public void onResponse(Call<Mentor> call, Response<Mentor> response) {
                if(response.body().getSuccess())
                {
                    if(response.body().getEmpty())
                    {
                        Toast.makeText(SignupActivity.this, "가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                        check_possible = true;
                    }
                    else
                    {
                        Toast.makeText(SignupActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                        check_possible = false;
                    }
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentor> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "서버 실패!.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void RegisterMentor(String input_email, String input_name, String input_password) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentor> call = apiInterface.RegisterMentor(input_email, input_name, input_password);
        call.enqueue(new Callback<Mentor>() {
            @Override
            public void onResponse(Call<Mentor> call, Response<Mentor> response) {
                if(response.body().getSuccess())
                {
                    Toast.makeText(SignupActivity.this, "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "서버실패.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentor> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "회원가입 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
