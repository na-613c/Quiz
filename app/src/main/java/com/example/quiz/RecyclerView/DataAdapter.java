package com.example.quiz.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.Data.PlayerInformation;
import com.example.quiz.R;

import java.util.List;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<PlayerInformation> gameRecords;

    public DataAdapter(Context context, List<PlayerInformation> gameRecords) {
        this.gameRecords = gameRecords;
        this.inflater = LayoutInflater.from(context);
    }

    public void updateItems() {
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        PlayerInformation playerInformation = gameRecords.get(position);


        holder.userNameView.setText(playerInformation.getUserName());
        holder.modView.setText(playerInformation.getMod());
        holder.numberOfPointsView.setText(playerInformation.getNumberOfPoints() + "");

    }

    @Override
    public int getItemCount() {
        return gameRecords.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView userNameView, modView, numberOfPointsView;

        ViewHolder(View view) {
            super(view);
            userNameView = (TextView) view.findViewById(R.id.userNameRecord);
            modView = (TextView) view.findViewById(R.id.modRecord);
            numberOfPointsView = (TextView) view.findViewById(R.id.pointRecord);
        }
    }
}