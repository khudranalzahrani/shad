package com.example.lap_shop.shahad_3asal.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.ui.AutoScaleTextView;

/**
 * Created by LAP_SHOP on 06/02/2017.
 */

public class NavigationAdapter extends RecyclerView.ViewHolder {

    ImageView img;
    AutoScaleTextView text;
    View main;

    public NavigationAdapter(View itemView) {
        super(itemView);
        text = (AutoScaleTextView) itemView.findViewById(R.id.Navigationtext);
        main = itemView.findViewById(R.id.Navigationmain);
        img = (ImageView) itemView.findViewById(R.id.icoon);
    }

    public AutoScaleTextView gettextView() {
        return text;

    }

    public ImageView getimgView() {
        return img;

    }

    public View getmainView() {
        return main;

    }

}