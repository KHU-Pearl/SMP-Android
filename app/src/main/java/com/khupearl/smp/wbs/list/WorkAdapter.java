package com.khupearl.smp.wbs.list;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khupearl.smp.R;
import com.khupearl.smp.wbs.Work;
import com.khupearl.smp.wbs.WorkDetailActivity;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkViewHolder> {
    
    private Context context;
    private ArrayList<Work> workArrayList;

    private boolean isDateNull = false;
    
    public WorkAdapter(Context context, ArrayList<Work> workArrayList) {
        this.context = context;
        this.workArrayList = workArrayList;
    }
    @NonNull
    @Override
    public WorkAdapter.WorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wbs, parent, false);
        WorkAdapter.WorkViewHolder viewHolder = new WorkAdapter.WorkViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkAdapter.WorkViewHolder holder, int position) {
        final Work work = workArrayList.get(position);

        holder.workNameTextView.setText(work.getTitle());
        holder.workFieldTextView.setText(work.getField());

        try {
            long dday = getDday(work);
            if (isDateNull) {
                holder.ddayTextView.setText(""); // 종료날짜 없으면 디데이 표시 X
                isDateNull = false;
            } else {
                holder.ddayTextView.setText("D" + dday);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


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

    private long getDday(Work work) throws ParseException {
        long result = 0;
        try {
            long now = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(now);
            String today = simpleDateFormat.format(date);

            // Date 형으로 변환
            Date todayDate = simpleDateFormat.parse(today);
            String workDate = work.getDate();
            if (workDate == null) {
                isDateNull = true;
                return -1;
            }
            Date dueDate = simpleDateFormat.parse(workDate);

            long calDate = todayDate.getTime() - dueDate.getTime(); // 계산결과가 '초'로 나옴
            result = calDate / (24 * 60 * 60 * 1000); // 일수로 변환

        } catch (ParseException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsStrting = sw.toString();

            Log.e("WorkAdapter", exceptionAsStrting);
        }
        return result;
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
