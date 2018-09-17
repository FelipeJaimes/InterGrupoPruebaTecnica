package com.example.android.intergrupopruebatecnica.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.intergrupopruebatecnica.R;
import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;
import com.example.android.intergrupopruebatecnica.view.listener.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProspectsAdapter extends RecyclerView.Adapter<ProspectsAdapter.ProspectViewHolder> {

    private List<Prospect> prospectList = new ArrayList<>();
    private ItemClickListener clickListener;

    public void addAll(List<Prospect> items) {
        prospectList.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProspectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prospect, parent, false);
        return new ProspectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProspectViewHolder holder, int position) {
        holder.prospectIdTextView.setText(prospectList.get(position).getSid());
        holder.nameTextView.setText(prospectList.get(position).getName());
        holder.surnameTextView.setText(prospectList.get(position).getSurname());
        holder.phoneTextView.setText(prospectList.get(position).getTelephone());
        //holder.statusTextView.setText("Status: "+ prospectList.get(position).getStatusCd().toString());
    }

    @Override
    public int getItemCount() {
        return prospectList.size();
    }

    public class ProspectViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_prospect_id) TextView prospectIdTextView;
        @BindView(R.id.item_prospect_name) TextView nameTextView;
        @BindView(R.id.item_prospect_surname) TextView surnameTextView;
        @BindView(R.id.item_prospect_phone) TextView phoneTextView;
       //@BindView(R.id.item_prospect_status)TextView statusTextView;

        public ProspectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            setViewHolderListener(this, itemView);
        }

    }

    private void setViewHolderListener(final RecyclerView.ViewHolder viewHolder, View itemView) {
        if (clickListener != null)
            itemView.setOnClickListener(view -> clickListener
                    .onItemClick(view, viewHolder.getAdapterPosition(),
                            prospectList.get(viewHolder.getAdapterPosition())));
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.clickListener = listener;
    }

}
