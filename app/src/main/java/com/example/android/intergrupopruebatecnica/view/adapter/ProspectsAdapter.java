package com.example.android.intergrupopruebatecnica.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.intergrupopruebatecnica.R;
import com.example.android.intergrupopruebatecnica.model.Prospect;
import com.example.android.intergrupopruebatecnica.view.listener.ItemClickListener;

import java.util.List;

public class ProspectsAdapter extends RecyclerView.Adapter<ProspectsAdapter.ProspectViewHolder> {

    private List<Prospect> mProspects;
    private ItemClickListener mItemClickListener;

    public ProspectsAdapter(List<Prospect> items) {
        mProspects = items;
    }

    @NonNull
    @Override
    public ProspectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prospect_list_item, parent, false);
        return new ProspectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProspectViewHolder holder, int position) {
        holder.prospectIdTextView.setText("ProspectId: "+ mProspects.get(position).getProspectId());
        holder.nameTextView.setText("Name: "+ mProspects.get(position).getName());
        holder.surnameTextView.setText("Surname: "+ mProspects.get(position).getSurname());
        holder.phoneTextView.setText("Phone: "+ mProspects.get(position).getTelephone());
        holder.statusTextView.setText("Status: "+ mProspects.get(position).getStatusCd().toString());
    }

    @Override
    public int getItemCount() {
        return mProspects.size();
    }

    public class ProspectViewHolder extends RecyclerView.ViewHolder {

        TextView prospectIdTextView;
        TextView nameTextView;
        TextView surnameTextView;
        TextView phoneTextView;
        TextView statusTextView;

        public ProspectViewHolder(View itemView) {
            super(itemView);
            initUI(itemView);
            setViewHolderListener(this, itemView);
        }

        private void initUI(View view) {
            prospectIdTextView = view.findViewById(R.id.prospectId_TextView);
            nameTextView = view.findViewById(R.id.name_TextView);
            surnameTextView = view.findViewById(R.id.surname_TextView);
            phoneTextView = view.findViewById(R.id.phone_TextView);
            statusTextView = view.findViewById(R.id.status_TextView);
        }
    }

    private void setViewHolderListener(final RecyclerView.ViewHolder viewHolder, View itemView) {
        if (mItemClickListener != null)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(view, viewHolder.getAdapterPosition(), mProspects.get(viewHolder.getAdapterPosition()));
                }
            });
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.mItemClickListener = listener;
    }

}
