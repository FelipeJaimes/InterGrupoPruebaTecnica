package com.example.android.intergrupopruebatecnica.view.fragment;

import android.app.Fragment;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.intergrupopruebatecnica.R;
import com.example.android.intergrupopruebatecnica.model.MyAppDatabase;
import com.example.android.intergrupopruebatecnica.model.Prospect;
import com.example.android.intergrupopruebatecnica.rest.response.ProspectsResponse;
import com.example.android.intergrupopruebatecnica.rest.service.ProspectsService;
import com.example.android.intergrupopruebatecnica.utils.NetworkUtils;
import com.example.android.intergrupopruebatecnica.view.adapter.ProspectsAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProspectsFragment extends Fragment {

    private static final String DATABASE_NAME = "db";

    private CompositeDisposable compositeDisposable;
    private MyAppDatabase mDatabase;
    private List<Prospect> prospects = new ArrayList<>();
    private List<Prospect> mLocalProspects = new ArrayList<>();
    private ProspectsAdapter mProspectsAdapter;
    ProspectsService prospectsService;
    RecyclerView recyclerView;

    Prospect prospect;
    String token;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragment, container, false);

        mDatabase = Room.databaseBuilder(getActivity().getApplicationContext(), MyAppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        prospect = new Prospect();

        if (NetworkUtils.checkInternetConnection(getActivity().getApplicationContext())) {

            prospectsService = new ProspectsService();
            getProspectsFromService(prospectsService, token);

            mProspectsAdapter = new ProspectsAdapter(prospects);
            recyclerView.setAdapter(mProspectsAdapter);

        } else {
            //TODO: get prospects from db to a list and initialize Recycler
        }


        return view;
    }

    private void getProspectsFromService(ProspectsService prospectsService, String token) {
        prospectsService.getProspects(token, new Callback<ProspectsResponse>() {
            @Override
            public void onResponse(Call<ProspectsResponse> call, Response<ProspectsResponse> response) {
                if (response.body() == null) {
                    recyclerView.setVisibility(View.GONE);
                    Log.d("ERROR: ", "empty response");
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    prospects.addAll(response.body().getProspects());
                    mProspectsAdapter.notifyDataSetChanged();

                    addProspectsSubscription(prospects);
                    addProspects(prospects);

                    getLocalProspectsSubscription(mLocalProspects);
                    getLocalProspects(mLocalProspects);


                }

            }

            @Override
            public void onFailure(Call<ProspectsResponse> call, Throwable t) {
                Log.e("GET PROSPECTS: ", t.getMessage());
            }
        });

    }


    private Completable getLocalProspects(final List<Prospect> mLocalProspects) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {
                for (int i = 0; i < mDatabase.prospectAccess().countRows(); i++) {
                    Prospect mProspect = new Prospect();
                    mProspect.setProspectId(mDatabase.prospectAccess().fetchOneProspectByProspectId(i + 1).getProspectId());
                    mProspect.setName(mDatabase.prospectAccess().fetchOneProspectByProspectId(i + 1).getName());
                    mProspect.setSurname(mDatabase.prospectAccess().fetchOneProspectByProspectId(i + 1).getSurname());
                    mProspect.setTelephone(mDatabase.prospectAccess().fetchOneProspectByProspectId(i + 1).getTelephone());
                    mProspect.setStatusCd(mDatabase.prospectAccess().fetchOneProspectByProspectId(i + 1).getStatusCd());
                    mLocalProspects.add(mProspect);
                }

                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private void getLocalProspectsSubscription(List<Prospect> mLocalProspects) {
        try {
            addSubscription(getLocalProspects(mLocalProspects).subscribe());
        } catch (Throwable exception) {
            Log.e("DATABASE ERROR", exception.getMessage());
        }
    }

    private Completable addProspects(final List<Prospect> prospects) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) throws Exception {

                if (mDatabase.prospectAccess().countRows() > 0) {
                    for (int i = 0; i < prospects.size(); i++) {
                        prospect.setProspectId(String.valueOf(prospects.get(i).getId()));
                        prospect.setName(prospects.get(i).getName());
                        prospect.setSurname(prospects.get(i).getSurname());
                        prospect.setTelephone(prospects.get(i).getTelephone());
                        prospect.setStatusCd(prospects.get(i).getStatusCd());
                        mDatabase.prospectAccess().updateProspect(prospect);
                    }
                } else {
                    for (int i = 0; i < prospects.size(); i++) {
                        prospect.setProspectId(String.valueOf(prospects.get(i).getId()));
                        prospect.setName(prospects.get(i).getName());
                        prospect.setSurname(prospects.get(i).getSurname());
                        prospect.setTelephone(prospects.get(i).getTelephone());
                        prospect.setStatusCd(prospects.get(i).getStatusCd());
                        mDatabase.prospectAccess().insertProspect(prospect);
                    }
                }

                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    private void addProspectsSubscription(List<Prospect> prospects) {
        try {
            addSubscription(addProspects(prospects).subscribe());
        } catch (Throwable exception) {
            Log.e("DATABASE ERROR", exception.getMessage());
        }
    }

    protected void addSubscription(Disposable subscription) {
        compositeDisposable.add(subscription);
    }

}
