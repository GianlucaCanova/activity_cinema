package com.example.jack.projectwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jack on 13/04/18.
 */

public class Fragment_moreDesc extends Fragment {
    Button mButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.fragment_layout_secondo, null);

        mButton = vView.findViewById(R.id.button);
            mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*FragmentTransaction vTransaction= mManager.beginTransaction();
                vTransaction.replace(R.id.fragment_zone,fCalendar);

                vTransaction.commit();*/

            }
        });

            return vView;
    }
}
