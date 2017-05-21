package com.peppe.popapp.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peppe.popapp.MainActivity;
import com.peppe.popapp.R;
import com.peppe.popapp.SplashActivity;
import com.peppe.popapp.adapters.AdapterBiglietti;
import com.peppe.popapp.api.APIService;
import com.peppe.popapp.models.Biglietto;
import com.peppe.popapp.results.ResultBiglietti;

import java.util.ArrayList;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.peppe.popapp.api.APIUrl.BASE_URL;


public class BigliettiFragment extends Fragment {

    private RecyclerView recyclerViewBiglietti;
    private RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeBiglietti;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.biglietti);

        recyclerViewBiglietti = (RecyclerView) getView().findViewById(R.id.recyclerViewBiglietti);
        recyclerViewBiglietti.setHasFixedSize(true);
        recyclerViewBiglietti.setLayoutManager(new LinearLayoutManager(getContext()));

        loadBiglietti();

        swipeBiglietti = (SwipeRefreshLayout) getView().findViewById(R.id.swipeBiglietti);

        swipeBiglietti.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeBiglietti.setRefreshing(true);

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        swipeBiglietti.setRefreshing(false);
                        loadBiglietti();
                    }
                },100);
            }
        });


    }

    private void loadBiglietti() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getContext().getResources().getString(R.string.load));
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ResultBiglietti> call = service.getPrezzi();

        call.enqueue(new Callback<ResultBiglietti>() {
            @Override
            public void onResponse(Call<ResultBiglietti> call, Response<ResultBiglietti> response) {
                progressDialog.dismiss();

                ArrayList<Biglietto> biglietti = response.body().getPrezzi();

                adapter = new AdapterBiglietti(biglietti, getContext());
                recyclerViewBiglietti.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResultBiglietti> call, Throwable t) {

            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_biglietti, container, false);
    }
}
