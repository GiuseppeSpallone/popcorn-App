package com.peppe.popapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.peppe.popapp.R;
import com.peppe.popapp.models.Biglietto;
import java.util.List;

public class AdapterBiglietti extends RecyclerView.Adapter<AdapterBiglietti.ViewHolder> {

    private List<Biglietto> listaBiglietti;
    private Context context;

    public AdapterBiglietti(List<Biglietto> listaBiglietti, Context context) {
        this.listaBiglietti = listaBiglietti;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_biglietto, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Biglietto biglietto = listaBiglietti.get(position);

        holder.textViewTipo.setText(biglietto.getTipo());
        holder.textViewPrezzo.setText(biglietto.getPrezzo());
    }

    @Override
    public int getItemCount() {
        return listaBiglietti.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTipo;
        public TextView textViewPrezzo;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewTipo = (TextView) itemView.findViewById(R.id.textViewTipo);
            textViewPrezzo = (TextView) itemView.findViewById(R.id.textViewPrezzo);
        }

    }
}
