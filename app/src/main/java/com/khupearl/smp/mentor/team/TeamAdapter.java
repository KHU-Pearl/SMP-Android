package com.khupearl.smp.mentor.team;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khupearl.smp.R;
import com.khupearl.smp.wbs.WbsListActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

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
        final Team Team = teamArrayList.get(position);

        holder.teamNameTextView.setText(Team.getName());
        holder.teamTitleTextView.setText(Team.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WbsListActivity.class);
//                intent.putExtra("position", position);
//                intent.putExtra("teamName", Team.getName());
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

    public class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView teamNameTextView;
        TextView teamTitleTextView;
        View itemView;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;

            teamNameTextView = itemView.findViewById(R.id.textview_team_name);
            teamTitleTextView = itemView.findViewById(R.id.textview_team_title);
        }
    }
}
