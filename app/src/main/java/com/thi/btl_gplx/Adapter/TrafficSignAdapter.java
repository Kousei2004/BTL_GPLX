package com.thi.btl_gplx.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.thi.btl_gplx.Models.TrafficSign;
import com.thi.btl_gplx.R;

import java.util.List;

public class TrafficSignAdapter extends RecyclerView.Adapter<TrafficSignAdapter.ItemViewHolder> {

    List<TrafficSign> arr;
    private OnItemClickListener listener;

    public TrafficSignAdapter(List<TrafficSign> arr, OnItemClickListener listener) {
        this.arr = arr;
        this.listener = listener;
    }



    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bien_bao, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        TrafficSign item = arr.get(position);
        holder.textView.setText(item.getCode());

        holder.textView2.setText(item.getName());
        holder.textView3.setText(item.getDescription());
        holder.img.setImageResource(item.getImageResId());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public interface OnItemClickListener {
        void onItemClick(TrafficSign item);
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView textView3;
        ImageView img;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.sign_code);
            textView2 = itemView.findViewById(R.id.sign_name);
            textView3 = itemView.findViewById(R.id.sign_description);
            img = itemView.findViewById(R.id.sign_image);
        }
    }

}
