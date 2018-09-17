package com.example.android.intergrupopruebatecnica.view.contract;

import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;

import java.util.List;

public interface ProspectsView {
    void showItemsOnList(List<Prospect> prospects);
    void hideProgress();
    void goToAdd();
}
