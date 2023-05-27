package com.example.gestinterim;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    private final List<Job> jobsList;

    public JobAdapter(List<Job> jobsList) {
        this.jobsList = jobsList;
        Log.e("TAG", jobsList.toString());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.title_job);
            txtDescription = itemView.findViewById(R.id.item_descript);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_offers, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.e("", String.valueOf(position));
        Job job = jobsList.get(position);
        holder.txtTitle.setText(job.getTitle());
        holder.txtDescription.setText(job.getDescription());
        // Assignez les autres données aux vues, si nécessaire
    }

    @Override
    public int getItemCount() {
        Log.e("",String.valueOf(jobsList.size()));
        return jobsList.size();
    }

}

