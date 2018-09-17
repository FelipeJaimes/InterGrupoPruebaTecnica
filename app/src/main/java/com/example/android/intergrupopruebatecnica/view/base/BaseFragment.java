package com.example.android.intergrupopruebatecnica.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.intergrupopruebatecnica.Application;
import com.example.android.intergrupopruebatecnica.data.local.Database;
import com.example.android.intergrupopruebatecnica.data.remote.ApiConnection;
import com.example.android.intergrupopruebatecnica.presenter.BasePresenter;
import com.example.android.intergrupopruebatecnica.view.utils.NavigationUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<Presenter extends BasePresenter> extends Fragment {

    protected Presenter presenter;
    private Unbinder unbinder;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = createPresenter();
        presenter.init();
    }

    @Override
    public void onPause() {
        presenter.disposeSubscriptions();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    public Database getDatabase() {
        return ((Application)getActivity().getApplication()).getDatabase();
    }

    public ApiConnection getApiConnection() {
        return ((Application)getActivity().getApplication()).getApiConnection();
    }

    public abstract Presenter createPresenter();

    public abstract int getLayout();

    public abstract void initViews ();

    public void goToNextFragment(Fragment nextFragment, int fragmentContainerLayout) {
        NavigationUtils.changeFragment(fragmentContainerLayout,
                getFragmentManager(),
                nextFragment);
    }

}
