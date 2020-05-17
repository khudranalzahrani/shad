package com.example.lap_shop.shahad_3asal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap_shop.shahad_3asal.R;

/**
 * Created by LAP_SHOP on 26/04/2017.
 */

public class SubcatigoresHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView textView;
    private View view;

    public SubcatigoresHolder(View itemView) {
        super(itemView);
        imageView=(ImageView)itemView.findViewById(R.id.sub_catigory_image);
        textView=(TextView)itemView.findViewById(R.id.sub_catigory_textview);
        view=itemView.findViewById(R.id.subcatogres_Main);
    }

    public TextView getTextView() {
        return textView;
    }

    public View getView() {
        return view;
    }

    public ImageView getImageView() {
        return imageView;
    }

}
