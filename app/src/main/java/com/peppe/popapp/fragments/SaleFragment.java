package com.peppe.popapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.peppe.popapp.R;
import com.peppe.popapp.adapters.AdapterSale;
import com.peppe.popapp.api.APIService;
import com.peppe.popapp.models.Sala;
import com.peppe.popapp.results.ResultSale;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.peppe.popapp.api.APIUrl.BASE_URL;

public class SaleFragment extends Fragment {

    private RecyclerView recyclerViewSale;
    private RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeSale;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Sale");

        recyclerViewSale = (RecyclerView) getView().findViewById(R.id.recyclerViewSale);
        recyclerViewSale.setHasFixedSize(true);
        recyclerViewSale.setLayoutManager(new LinearLayoutManager(getContext()));

        loadSale();

        swipeSale = (SwipeRefreshLayout) getView().findViewById(R.id.swipeSale);

        swipeSale.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeSale.setRefreshing(true);

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        swipeSale.setRefreshing(false);
                        loadSale();
                    }
                },100);
            }
        });
    }

    private void loadSale() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ResultSale> call = service.getSale();

        call.enqueue(new Callback<ResultSale>() {
            @Override
            public void onResponse(Call<ResultSale> call, Response<ResultSale> response) {
                progressDialog.dismiss();

                ArrayList<Sala> sale = response.body().getSale();

                adapter = new AdapterSale(sale, getContext());
                recyclerViewSale.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResultSale> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sale, container, false);
    }
}
