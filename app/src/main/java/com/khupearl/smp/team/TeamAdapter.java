package com.khupearl.smp.team;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.khupearl.smp.R;
import com.khupearl.smp.api.ApiClient;
import com.khupearl.smp.api.ApiInterface;
import com.khupearl.smp.mentee.Progress;
import com.khupearl.smp.wbs.list.WbsListActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    private static final String TAG = "TeamAdapter";

    private Context context;
    private ArrayList<Team> teamArrayList;

    public TeamAdapter(Context context, ArrayList<Team> teamArrayList) {
        this.context = context;
        this.teamArrayList = teamArrayList;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        TeamViewHolder viewHolder = new TeamViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, final int position) {
        final Team team = teamArrayList.get(position);

        holder.teamNameTextView.setText(team.getName());
        holder.teamTitleTextView.setText(team.getTitle());
        setProgressByTeamName(holder, team);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WbsListActivity.class);
                intent.putExtra("teamName", team.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (teamArrayList != null) {
            return teamArrayList.size();
        }
        return 0;
    }

    private void setProgressByTeamName(@NonNull final TeamViewHolder holder, final Team team) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Progress> call = apiInterface.getTeamCountWbs(team.getName());
        call.enqueue(new Callback<Progress>() {
            @Override
            public void onResponse(Call<Progress> call, Response<Progress> response) {
                if(response.body().isSuccess())
                {
                    int maxCnt = Integer.parseInt(response.body().getCount_todo())
                            + Integer.parseInt(response.body().getCount_inprogress())
                            + Integer.parseInt(response.body().getCount_done());
                    int progressCnt = Integer.parseInt(response.body().getCount_done());
                    holder.teamProgressBar.setProgress(progressCnt);
                    holder.teamProgressBar.setMax(maxCnt);
                    holder.progressTextView.setText(response.body().getProgress() + "%");
                }
                else
                {
                    Log.e(TAG, "서버 실패");
                }
            }

            @Override
            public void onFailure(Call<Progress> call, Throwable t) {
                Log.e(TAG, "서버 실패!");
            }
        });
    }

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView teamNameTextView;
        TextView teamTitleTextView;
        ProgressBar teamProgressBar;
        TextView progressTextView;
        View itemView;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;

            teamNameTextView = itemView.findViewById(R.id.textview_team_name);
            teamTitleTextView = itemView.findViewById(R.id.textview_team_title);
            teamProgressBar = itemView.findViewById(R.id.progressbar_team);
            progressTextView = itemView.findViewById(R.id.textview_team_progress);
        }
    }
}
