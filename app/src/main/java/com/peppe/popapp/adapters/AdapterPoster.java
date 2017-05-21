package com.peppe.popapp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.peppe.popapp.R;
import com.peppe.popapp.models.Film;
import com.peppe.popapp.models.Programmazione;

import java.util.List;

public class AdapterPoster extends BaseAdapter{

    private List<Programmazione> listaPoster;
    private Context context;

    public AdapterPoster(List<Programmazione> listaPoster, Context context) {
        this.listaPoster = listaPoster;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaPoster.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Programmazione programmazione = listaPoster.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_poster, null);
        TextView textView = (TextView) view.findViewById(R.id.textViewTitoloPoster);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewPoster);
        //textView.setText(programmazione.getTitolo());

        Glide.with(context).load(programmazione.getPoster()).into(imageView);
        return view;
    }

}
