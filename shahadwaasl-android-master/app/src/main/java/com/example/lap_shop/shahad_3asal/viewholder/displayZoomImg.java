package com.example.lap_shop.shahad_3asal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.lap_shop.shahad_3asal.R;

/**
 * Created by ahmed on 6/18/2017.
 */

public class displayZoomImg  extends RecyclerView.ViewHolder  {
    ImageView img;

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public displayZoomImg(View itemView) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.img);
    }
}
