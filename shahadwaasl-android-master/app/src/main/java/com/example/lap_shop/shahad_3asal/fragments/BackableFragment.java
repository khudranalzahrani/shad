package com.example.lap_shop.shahad_3asal.fragments;

/**
 * Created by Ahmed Salamony on 8/1/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

public abstract class BackableFragment extends Fragment implements View.OnKeyListener {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                onBackButtonPressed();
                return true;
            }
        }

        return false;
    }

    public abstract void onBackButtonPressed();
}
