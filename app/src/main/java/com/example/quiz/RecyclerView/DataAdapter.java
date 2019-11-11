package com.example.quiz.RecyclerView;

/****************************************
 *      created by Shavlovskii Ivan     *
 *               10.11.2019             *
 ***************************************/

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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

        int gold = Color.rgb(255, 215, 0);
        int silver = Color.rgb(197, 201, 199);
        int bronze = Color.rgb(205, 127, 50);

        switch (position) {
            case 0:
                holder.numberOfPointsView.setTextColor(gold);
                break;

            case 1:
                holder.numberOfPointsView.setTextColor(silver);
                break;

            case 2:
                holder.numberOfPointsView.setTextColor(bronze);
                break;
        }

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