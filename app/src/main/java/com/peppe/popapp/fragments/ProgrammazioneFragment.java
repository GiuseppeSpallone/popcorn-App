package com.peppe.popapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.peppe.popapp.R;
import com.peppe.popapp.adapters.AdapterBiglietti;
import com.peppe.popapp.adapters.AdapterProgrammazione;
import com.peppe.popapp.api.APIService;
import com.peppe.popapp.models.Biglietto;
import com.peppe.popapp.models.Programmazione;
import com.peppe.popapp.results.ResultBiglietti;
import com.peppe.popapp.results.ResultProgrammazione;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.peppe.popapp.api.APIUrl.BASE_URL;

public class ProgrammazioneFragment extends Fragment {

    private RecyclerView recyclerViewProgrammazione;
    private RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeProgrammazione;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.programmazione);

        recyclerViewProgrammazione = (RecyclerView) getView().findViewById(R.id.recyclerViewProgrammazione);
        recyclerViewProgrammazione.setHasFixedSize(true);
        recyclerViewProgrammazione.setLayoutManager(new LinearLayoutManager(getContext()));

        loadProgrammazione();

        swipeProgrammazione = (SwipeRefreshLayout) getView().findViewById(R.id.swipeProgrammazione);

        swipeProgrammazione.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeProgrammazione.setRefreshing(true);

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        swipeProgrammazione.setRefreshing(false);
                        loadProgrammazione();
                    }
                },100);
            }
        });
    }

    private void loadProgrammazione() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ResultProgrammazione> call = service.getProgrammazione();

        call.enqueue(new Callback<ResultProgrammazione>() {
            @Override
            public void onResponse(Call<ResultProgrammazione> call, Response<ResultProgrammazione> response) {
                progressDialog.dismiss();

                ArrayList<Programmazione> programmazione = response.body().getProgrammazione();

                adapter = new AdapterProgrammazione(programmazione, getContext());
                recyclerViewProgrammazione.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResultProgrammazione> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_programmazione, container, false);
    }
}
