package com.example.android.intergrupopruebatecnica.view.fragment;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.android.intergrupopruebatecnica.R;
import com.example.android.intergrupopruebatecnica.data.local.access.ProspectsLocal;
import com.example.android.intergrupopruebatecnica.data.local.entity.Prospect;
import com.example.android.intergrupopruebatecnica.data.remote.access.ProspectsRemote;
import com.example.android.intergrupopruebatecnica.data.repository.ProspectRepository;
import com.example.android.intergrupopruebatecnica.model.ProspectsModel;
import com.example.android.intergrupopruebatecnica.presenter.ProspectsPresenter;
import com.example.android.intergrupopruebatecnica.view.activity.EditActivity;
import com.example.android.intergrupopruebatecnica.view.adapter.ProspectsAdapter;
import com.example.android.intergrupopruebatecnica.view.base.BaseFragment;
import com.example.android.intergrupopruebatecnica.view.contract.ProspectsView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProspectsFragment extends BaseFragment<ProspectsPresenter> implements ProspectsView {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.prospect_progress) ProgressBar progressBar;
    @BindView(R.id.prospects_fab) FloatingActionButton floatingActionButton;
    private ProspectsAdapter prospectsAdapter;

    @Override
    public void initViews() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        prospectsAdapter = new ProspectsAdapter();
        recyclerView.setAdapter(prospectsAdapter);
        progressBar.setVisibility(View.VISIBLE);

    }

    @OnClick (R.id.prospects_fab)
    void onFabClick(){
        presenter.addNew();
    }

    @Override
    public ProspectsPresenter createPresenter() {
        ProspectsModel model = new ProspectsModel(
                new ProspectRepository(
                        new ProspectsLocal(getDatabase()),
                        getApiConnection().getProspectRemote())
        );
        return new ProspectsPresenter(this, model);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_prospects;
    }

    @Override
    public void showItemsOnList(List<Prospect> prospects) {
        if (prospectsAdapter != null) {
            prospectsAdapter.addAll(prospects);
        }
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void goToAdd() {
        Intent intent = new Intent(getActivity(), EditActivity.class);
        startActivity(intent);
    }
}