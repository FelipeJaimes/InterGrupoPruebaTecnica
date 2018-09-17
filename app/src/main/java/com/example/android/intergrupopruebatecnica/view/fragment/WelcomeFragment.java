package com.example.android.intergrupopruebatecnica.view.fragment;


import com.example.android.intergrupopruebatecnica.R;
import com.example.android.intergrupopruebatecnica.presenter.WelcomePresenter;
import com.example.android.intergrupopruebatecnica.view.base.BaseFragment;
import com.example.android.intergrupopruebatecnica.view.contract.WelcomeView;


public class WelcomeFragment extends BaseFragment<WelcomePresenter> implements WelcomeView {

    @Override
    public WelcomePresenter createPresenter() {
        return new WelcomePresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_welcome;
    }

    @Override
    public void initViews() {

    }
}
