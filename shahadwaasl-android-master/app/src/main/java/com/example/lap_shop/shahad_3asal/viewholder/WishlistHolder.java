package com.example.lap_shop.shahad_3asal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap_shop.shahad_3asal.R;

/**
 * Created by LAP_SHOP on 04/05/2017.
 */

public class WishlistHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textView,price,model;
    private View view;
    private ImageView btn_cart;

    public ImageView getBtn_cart() {
        return btn_cart;
    }

    public void setBtn_cart(ImageView btn_cart) {
        this.btn_cart = btn_cart;
    }

    public WishlistHolder(View itemView) {
        super(itemView);
        imageView=(ImageView)itemView.findViewById(R.id.sub_catigory_image);
        textView=(TextView)itemView.findViewById(R.id.sub_catigory_textview);
        model=(TextView)itemView.findViewById(R.id.model);
        price=(TextView)itemView.findViewById(R.id.price);
        view=itemView.findViewById(R.id.subcatogres_Main);
        btn_cart=(ImageView)itemView.findViewById(R.id.add_to_cart);
    }

    public TextView getPrice() {
        return price;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public View getView() {
        return view;
    }

    public TextView getModel() {
        return model;
    }
}
