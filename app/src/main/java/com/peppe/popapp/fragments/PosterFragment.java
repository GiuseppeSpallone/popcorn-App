package com.peppe.popapp.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;

import com.peppe.popapp.R;
import com.peppe.popapp.adapters.AdapterPoster;
import com.peppe.popapp.adapters.AdapterProgrammazione;
import com.peppe.popapp.api.APIService;
import com.peppe.popapp.models.Film;
import com.peppe.popapp.models.Programmazione;
import com.peppe.popapp.results.ResultProgrammazione;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.peppe.popapp.api.APIUrl.BASE_URL;

public class PosterFragment extends Fragment {

    private AdapterViewFlipper adapterViewFlipperPoster;
    SwipeRefreshLayout swipePoster;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.poster);

        adapterViewFlipperPoster = (AdapterViewFlipper) getView().findViewById(R.id.adapterViewFlipperPoster);

        loadPoster();

        swipePoster = (SwipeRefreshLayout) getView().findViewById(R.id.swipePoster);

        swipePoster.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipePoster.setRefreshing(true);

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        swipePoster.setRefreshing(false);
                        loadPoster();
                    }
                },100);
            }
        });
    }

    private void loadPoster() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ResultProgrammazione> call = service.getProgrammazione();

        call.enqueue(new Callback<ResultProgrammazione>() {
            @Override
            public void onResponse(Call<ResultProgrammazione> call, Response<ResultProgrammazione> response) {

                ArrayList<Programmazione> programmazione = response.body().getProgrammazione();

                AdapterPoster adapter = new AdapterPoster(programmazione, getContext());

                adapterViewFlipperPoster.setAdapter(adapter);
                adapterViewFlipperPoster.setFlipInterval(5000);
                adapterViewFlipperPoster.startFlipping();
            }

            @Override
            public void onFailure(Call<ResultProgrammazione> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_poster, container, false);
    }

}
