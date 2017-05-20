package com.peppe.popapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.peppe.popapp.R;
import com.peppe.popapp.models.Programmazione;
import java.util.List;

public class AdapterProgrammazione extends RecyclerView.Adapter<AdapterProgrammazione.ViewHolder>{

    private List<Programmazione> listaProgrammazione;
    private Context context;

    public AdapterProgrammazione(List<Programmazione> listaProgrammazione, Context context) {
        this.listaProgrammazione = listaProgrammazione;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_programmazione, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Programmazione programmazione = listaProgrammazione.get(position);

        holder.textViewTitolo.setText(programmazione.getTitolo());
        //poster
        holder.textViewOrario1.setText(programmazione.getOrario1());
        holder.textViewOrario2.setText(programmazione.getOrario2());
        holder.textViewOrario3.setText(programmazione.getOrario3());
        holder.textViewSala.setText(programmazione.getSala());

    }

    @Override
    public int getItemCount() {
        return listaProgrammazione.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitolo, textViewOrario1, textViewOrario2, textViewOrario3, textViewSala;
        public CardView cardViewProgramazione;
        public ImageView imagePoster;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTitolo = (TextView) itemView.findViewById(R.id.textViewTitoloProgrammazione);
            imagePoster = (ImageView) itemView.findViewById(R.id.imagePoster);
            textViewOrario1 = (TextView) itemView.findViewById(R.id.textViewOrario1);
            textViewOrario2 = (TextView) itemView.findViewById(R.id.textViewOrario2);
            textViewOrario3 = (TextView) itemView.findViewById(R.id.textViewOrario3);
            textViewSala = (TextView) itemView.findViewById(R.id.textViewSala);

            cardViewProgramazione = (CardView) itemView.findViewById(R.id.cardViewProgrammazione);

        }

    }
}
