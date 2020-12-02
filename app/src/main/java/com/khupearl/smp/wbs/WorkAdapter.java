package com.khupearl.smp.wbs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khupearl.smp.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder> {
    
    private Context context;
    private ArrayList<Work> workArrayList;
    
    public WorkAdapter(Context context, ArrayList<Work> workArrayList) {
        this.context = context;
        this.workArrayList = workArrayList;
    }
    @NonNull
    @Override
    public WorkAdapter.WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wbs_item, parent, false);
        WorkAdapter.WorkViewHolder viewHolder = new WorkAdapter.WorkViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkAdapter.WorkViewHolder holder, int position) {
        // TODO: 12/11/2020 d-day time이랑 현재날짜 계산해서 표시 

        final Work work = workArrayList.get(position);

        holder.workNameTextView.setText(work.getTitle());
        holder.workFieldTextView.setText(work.getField());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WorkDetailActivity.class);
                intent.putExtra("wbsId", work.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (workArrayList != null) {
            return workArrayList.size();
        }
        return 0;
    }

    public class WorkViewHolder extends RecyclerView.ViewHolder {
        TextView workNameTextView;
        TextView workFieldTextView;
        TextView ddayTextView;
        View itemView;
        
        public WorkViewHolder(@NonNull View itemView) {
            super(itemView);
            
            this.itemView = itemView;
            
            workNameTextView = itemView.findViewById(R.id.textview_work_name);
            workFieldTextView = itemView.findViewById(R.id.textview_work_field);
            ddayTextView = itemView.findViewById(R.id.textview_work_dday);
        }
    }
}
