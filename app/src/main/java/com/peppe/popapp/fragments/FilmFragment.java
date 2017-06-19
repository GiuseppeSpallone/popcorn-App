package com.peppe.popapp.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peppe.popapp.R;
import com.peppe.popapp.api.APIService;
import com.peppe.popapp.models.Film;
import com.peppe.popapp.results.ResultFilm;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.peppe.popapp.api.APIUrl.BASE_URL;

public class FilmFragment extends Fragment {

    TextView textViewTitolo, textViewNazione, textViewAnno, textViewGenere,
            textViewDurata, textViewRegia, textViewCast, textViewProduzione,
            textViewDistribuzione, textViewDataUscita, textViewTrama;


    SwipeRefreshLayout swipeFilm;
    String titolo;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.film);

        textViewTitolo = (TextView) getView().findViewById(R.id.textViewTitoloProgrammazione);
        textViewNazione = (TextView) getView().findViewById(R.id.textViewNazione);
        textViewAnno = (TextView) getView().findViewById(R.id.textViewAnno);
        textViewGenere = (TextView) getView().findViewById(R.id.textViewGenere);
        textViewDurata = (TextView) getView().findViewById(R.id.textViewDurata);
        textViewRegia = (TextView) getView().findViewById(R.id.textViewRegia);
        textViewCast = (TextView) getView().findViewById(R.id.textViewCast);
        textViewProduzione = (TextView) getView().findViewById(R.id.textViewProduzione);
        textViewDistribuzione = (TextView) getView().findViewById(R.id.textViewDistribuzione);
        textViewDataUscita = (TextView) getView().findViewById(R.id.textViewDataUscita);
        textViewTrama = (TextView) getView().findViewById(R.id.textViewTrama);

        loadFilm();

        swipeFilm = (SwipeRefreshLayout) getView().findViewById(R.id.swipeFilm);

        swipeFilm.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeFilm.setRefreshing(true);

                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        swipeFilm.setRefreshing(false);
                        loadFilm();
                    }
                }, 100);
            }
        });
    }

    private void loadFilm() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getContext().getResources().getString(R.string.load));
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ResultFilm> call = service.getFilm(titolo);

        call.enqueue(new Callback<ResultFilm>() {
            @Override
            public void onResponse(Call<ResultFilm> call, Response<ResultFilm> response) {
                progressDialog.dismiss();

                String titolo = response.body().getFilm().getTitolo();
                String nazione = response.body().getFilm().getNazione();
                String anno = response.body().getFilm().getAnno();
                String genere = response.body().getFilm().getGenere();
                String durata = response.body().getFilm().getDurata();
                String regia = response.body().getFilm().getRegia();
                String cast = response.body().getFilm().getCast();
                String produzione = response.body().getFilm().getProduzione();
                String distribuzione = response.body().getFilm().getDistribuzione();
                String dataUscita = response.body().getFilm().getDataUscita();
                String trama = response.body().getFilm().getTrama();
                String poster = response.body().getFilm().getPoster();

                Film film = new Film(titolo, nazione, anno, genere, durata, regia, cast, produzione, distribuzione, dataUscita, trama, poster);

                textViewTitolo.setText(film.getTitolo());
                textViewNazione.setText(film.getNazione());
                textViewAnno.setText(film.getAnno());
                textViewGenere.setText(film.getGenere());
                textViewDurata.setText(film.getDurata());
                textViewRegia.setText(film.getRegia());
                textViewCast.setText(film.getCast());
                textViewProduzione.setText(film.getProduzione());
                textViewDistribuzione.setText(film.getProduzione());
                textViewDataUscita.setText(film.getDataUscita());
                textViewTrama.setText(film.getTrama());
            }

            @Override
            public void onFailure(Call<ResultFilm> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_film, container, false);
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }
}
