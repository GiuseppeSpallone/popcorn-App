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
                },100);
            }
        });
    }

    private void loadFilm() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<Film> call = service.getFilm("Mamma ho perso l'aereo");

        call.enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                progressDialog.dismiss();

                String titolo = response.body().getTitolo();
                String nazione = response.body().getNazione();
                String anno = response.body().getAnno();
                String genere = response.body().getGenere();
                String durata = response.body().getDurata();
                String regia = response.body().getRegia();
                String cast = response.body().getCast();
                String produzione = response.body().getProduzione();
                String distribuzione = response.body().getDistribuzione();
                String dataUscita = response.body().getDataUscita();
                String trama = response.body().getTrama();
                String poster = response.body().getPoster();

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
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_film, container, false);
    }
}
