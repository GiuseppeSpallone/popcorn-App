package com.peppe.popapp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.peppe.popapp.MainActivity;
import com.peppe.popapp.R;

public class InfoFragment extends Fragment {

    private static final int MY_PERMISSION = 10;
    TextView textViewTelefono, textViewEmail;
    String numeroTelefono, email;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(R.string.info);

        textViewTelefono = (TextView) getView().findViewById(R.id.textViewTelefono);
        textViewEmail = (TextView) getView().findViewById(R.id.textViewEmail);

        numeroTelefono = textViewTelefono.getText().toString();
        email = textViewEmail.getText().toString();


        textViewTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chiamata = new Intent(Intent.ACTION_CALL);
                chiamata.setData(Uri.parse("tel:" + numeroTelefono));

                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION);
                } else {
                    startActivity(chiamata);
                }
            }
        });


        /*textViewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                startActivity(email);
            }
        });*/
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }
}
