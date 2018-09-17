package com.example.android.intergrupopruebatecnica.view.listener;

import android.view.View;

import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;

public interface ItemClickListener {

    void onItemClick(View view, int adapterPos, Prospect item);
}