package com.khupearl.smp.wbs;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.khupearl.smp.MyApplication;
import com.khupearl.smp.R;
import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.databinding.ActivityAddWorkBinding;
import com.khupearl.smp.mentee.Mentee;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWorkActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AddWorkActivity";

    private MyApplication myApplication;

    private ArrayAdapter arrayAdapter;
    private ArrayList<String> members;

    ActivityAddWorkBinding binding;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    private String inputMember;
    private String inputTitle;
    private String inputContent;
    private String inputField;
    private String inputDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_work);

        setToolBar();

        init();

    }

    private void init() {
        myApplication = (MyApplication)getApplication();
        myApplication.setTeamName("진주라디오"); // TODO: 05/12/2020 temp team
        members = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, members);
        binding.addMemberListView.setAdapter(arrayAdapter);
        binding.addMemberButton.setOnClickListener(this);
        binding.workDateEditText.setOnClickListener(this);
        binding.addWorkButton.setOnClickListener(this);
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int month = monthOfYear + 1;
                int day = dayOfMonth;
                inputDate = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);
                binding.workDateEditText.setText(inputDate);
            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.workDateEditText:
                // TODO: 05/12/2020 서버날짜 받아와서 디폴트값 지정
                DatePickerDialog dialog = new DatePickerDialog(this, dateSetListener, 2020, 12, 05);
                dialog.show();
                break;
            case R.id.addMemberButton:
                inputMember = binding.addMemberEditText.getText().toString();
                if(!inputMember.equals("") && !members.contains(inputMember)) setMember();
                break;
            case R.id.addWorkButton:
                addWork();
                finish();
                break;
        }

    }

    private void setToolBar() {
        binding.addWorkToolbar.setLeftButton(R.drawable.ic_arrow_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.addWorkToolbar.setTitleTextView("할 일 추가하기");
    }

    private void setMember() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Mentee> call = apiInterface.RegisterTeamMemberPossible(inputMember, myApplication.getTeamName());
        call.enqueue(new Callback<Mentee>() {
            @Override
            public void onResponse(Call<Mentee> call, Response<Mentee> response) {
                if(response.body().getSuccess())
                {
                    if(response.body().getEmpty())
                    {
                        Toast.makeText(AddWorkActivity.this, "존재하지 않는 이메일입니다.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        //리스트뷰에 추가
                        members.add(inputMember);
                        binding.addMemberEditText.setText("");
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
                else
                {
                    Toast.makeText(AddWorkActivity.this, "서버실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mentee> call, Throwable t) {
                Toast.makeText(AddWorkActivity.this, "서버와의 연결이 끊어졌습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addWork() {
        inputTitle = binding.workTitleEditText.getText().toString();
        inputContent = binding.workContentEditText.getText().toString();
        inputField = binding.workFieldEditText.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Work> call = apiInterface.AddWork(myApplication.getTeamName(), inputTitle, inputContent, inputField, inputDate);
        call.enqueue(new Callback<Work>() {
            @Override
            public void onResponse(Call<Work> call, Response<Work> response) {
                if(response.body().isSuccess())
                {
                    addTeamMember(response.body().getId()); // 참여멤버추가
                    Toast.makeText(AddWorkActivity.this, "추가되었습니다.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AddWorkActivity.this, "서버실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Work> call, Throwable t) {
                Toast.makeText(AddWorkActivity.this, "서버 실패!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addTeamMember(int id) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        for (int i=0; i<members.size(); i++) {
            Call<Work> call = apiInterface.AddWorkMembers(id, members.get(i));
            call.enqueue(new Callback<Work>() {
                @Override
                public void onResponse(Call<Work> call, Response<Work> response) {
                    if(response.body().isSuccess())
                    {
//                        Toast.makeText(AddWorkActivity.this, "멤버가 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(AddWorkActivity.this, "서버실패", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Work> call, Throwable t) {
                    Toast.makeText(AddWorkActivity.this, "서버 실패!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
