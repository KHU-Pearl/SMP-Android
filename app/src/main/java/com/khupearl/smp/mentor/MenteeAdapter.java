package com.khupearl.smp.mentor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khupearl.smp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenteeAdapter extends RecyclerView.Adapter<MenteeAdapter.MenteeViewHolder> {

    private Context context;
    private ArrayList<Mentee> menteeArrayList;

    public MenteeAdapter(Context context, ArrayList<Mentee> menteeArrayList) {
        this.context = context;
        this.menteeArrayList = menteeArrayList;
    }

    @NonNull
    @Override
    public MenteeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mentee_item, parent, false);
        MenteeViewHolder viewHolder = new MenteeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenteeViewHolder holder, int position) {
        final Mentee mentee = menteeArrayList.get(position);

        holder.menteeNameTextView.setText(mentee.getName());
        holder.menteeSubjectTextView.setText(mentee.getSubject());
    }

    @Override
    public int getItemCount() {
        if (menteeArrayList != null) {
            return menteeArrayList.size();
        }
        return 0;
    }

    public class MenteeViewHolder extends RecyclerView.ViewHolder {
        TextView menteeNameTextView;
        TextView menteeSubjectTextView;
        View itemView;

        public MenteeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;

            menteeNameTextView = itemView.findViewById(R.id.textview_mentee_name);
            menteeSubjectTextView = itemView.findViewById(R.id.textview_mentee_subject);
        }
    }
}
