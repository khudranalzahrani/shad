package com.example.lap_shop.shahad_3asal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.ui.AutoScaleTextView;

/**
 * Created by LAP_SHOP on 07/05/2017.
 */

public class CartlistHolder extends RecyclerView.ViewHolder {
    private ImageView imageView,mines,plus;
    private AutoScaleTextView textView;
    TextView price,Qty,out;
    private View view;

    public CartlistHolder(View itemView) {
        super(itemView);
        imageView=(ImageView)itemView.findViewById(R.id.sub_catigory_image);
        mines=(ImageView)itemView.findViewById(R.id.mines);
        plus=(ImageView)itemView.findViewById(R.id.plus);
        textView=(AutoScaleTextView)itemView.findViewById(R.id.sub_catigory_textview);
        Qty=(TextView)itemView.findViewById(R.id.Qty);
        out=(TextView)itemView.findViewById(R.id.out);
        price=(TextView)itemView.findViewById(R.id.price);
        view=itemView.findViewById(R.id.subcatogres_Main);
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

    public TextView getQty() {
        return Qty;
    }

    public TextView getOut() {
        return out;
    }

    public ImageView getMines() {
        return mines;
    }

    public ImageView getPlus() {
        return plus;
    }
}
