package com.prem.eamo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prem.eamo.R;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    public StoreAdapter(){
    }

    @NonNull
    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.store, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
