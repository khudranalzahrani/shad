package com.example.lap_shop.shahad_3asal.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lap_shop.shahad_3asal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class aboutus extends Fragment {

    private static Fragment fragment;
    public static Fragment newInstance() {

        fragment = new aboutus();
        return fragment;
    }
    public aboutus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aboutus, container, false);
    }

}
