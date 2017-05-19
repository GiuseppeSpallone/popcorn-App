package com.peppe.popapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.peppe.popapp.R;
import com.peppe.popapp.models.Sala;
import java.util.List;

public class AdapterSale extends RecyclerView.Adapter<AdapterSale.ViewHolder>{

    private List<Sala> listaSale;
    private Context context;

    public AdapterSale(List<Sala> listaSale, Context context) {
        this.listaSale = listaSale;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sala, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sala sala = listaSale.get(position);

        holder.textViewNumero.setText(sala.getNumero());
        holder.textViewPosti.setText(sala.getPosti());
    }

    @Override
    public int getItemCount() {
        return listaSale.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewNumero;
        public TextView textViewPosti;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewNumero = (TextView) itemView.findViewById(R.id.textViewNumero);
            textViewPosti = (TextView) itemView.findViewById(R.id.textViewPosti);
        }

    }
}
