package com.example.jack.projectwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jack on 13/04/18.
 */

public class Fragment_alt extends Fragment {

    TextView textViewTitolo, textViewDurata, textViewGenere, textViewDescrizione;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vView = inflater.inflate(R.layout.fragment_layout_primo, null);
        textViewTitolo = vView.findViewById(R.id.txt_titolo);
        textViewDurata = vView.findViewById(R.id.txt_durata);
        textViewGenere = vView.findViewById(R.id.txt_Genere);
        textViewDescrizione = vView.findViewById(R.id.txt_desc);
        return vView;
    }

}
