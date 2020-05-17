package com.example.lap_shop.shahad_3asal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap_shop.shahad_3asal.R;

/**
 * Created by LAP_SHOP on 25/04/2017.
 */

public class CatigoresHolder extends RecyclerView.ViewHolder {
    private ImageView ImgeView;
    private TextView textView;
    private View main;

    public CatigoresHolder(View itemView) {
        super(itemView);
        ImgeView=(ImageView)itemView.findViewById(R.id.catigory_main_image);
        textView=(TextView)itemView.findViewById(R.id.catigory_textView);
        main=itemView.findViewById(R.id.mainview);
    }

    public ImageView getImgeView() {
        return ImgeView;
    }

    public TextView getTextView() {
        return textView;
    }

    public View getMain() {
        return main;
    }
}
