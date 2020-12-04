package com.khupearl.smp.wbs.list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khupearl.smp.R;
import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.wbs.Work;

import java.util.ArrayList;
import java.util.List;

public class DoneFragment extends Fragment {

    private static final String TAG = "DoneFragment";
    private static final String wbsState = "완료";

    private RecyclerView wbsListRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private WorkAdapter workAdapter;

    private ArrayList<Work> workArrayList= new ArrayList<>();

    private String teamName;

    private View view;

    public DoneFragment(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_done, container, false);

        init();

        return view;
    }

    private void init() {
        wbsListRecyclerView = view.findViewById(R.id.recyclerview_wbs_done);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        wbsListRecyclerView.setLayoutManager(linearLayoutManager);

        getWorkList(teamName, wbsState);
    }

    private void getWorkList(final String team, final String wbsState) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Work>> call = apiInterface.getWbsList(team, wbsState);
        call.enqueue(new Callback<List<Work>>() {
            @Override
            public void onResponse(Call<List<Work>> call, Response<List<Work>> response) {
                if (response.isSuccessful()) {
                    for (Work w : response.body()) {
                        workArrayList.add(w);
                    }

                    Log.e(TAG, "서버성공 | wbs 개수 : " + workArrayList.size());
                    workAdapter = new WorkAdapter(getActivity(), workArrayList);
                    wbsListRecyclerView.setAdapter(workAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Work>> call, Throwable t) {
                Log.e(TAG, "서버에러 : " + t.getMessage());
            }
        });
    }
}