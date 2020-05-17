package com.example.lap_shop.shahad_3asal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap_shop.shahad_3asal.R;

/**
 * Created by LAP_SHOP on 25/04/2017.
 */

public class ProductViewholder extends RecyclerView.ViewHolder {
    private ImageView imageView,star1,star2,star3,star4,star5;
    private TextView textView1, textView2;
    private View latest;

    public ProductViewholder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.protactimage);
        star1 = (ImageView) itemView.findViewById(R.id.star1);
        star5 = (ImageView) itemView.findViewById(R.id.star5);
        star2 = (ImageView) itemView.findViewById(R.id.star2);
        star3 = (ImageView) itemView.findViewById(R.id.star3);
        star4 = (ImageView) itemView.findViewById(R.id.star4);
        textView1 = (TextView) itemView.findViewById(R.id.productname);
        textView2 = (TextView) itemView.findViewById(R.id.productprice);
        latest = itemView.findViewById(R.id.Latestview);

    }

    public ImageView getStar1() {
        return star1;
    }

    public ImageView getStar2() {
        return star2;
    }

    public ImageView getStar3() {
        return star3;
    }

    public ImageView getStar4() {
        return star4;
    }

    public ImageView getStar5() {
        return star5;
    }

    public ImageView GetImageProduct() {
        return imageView;
    }

    public TextView NameProduct() {
        return textView1;
    }

    public TextView PriceProduct() {
        return textView2;
    }

    public View getLatest() {
        return latest;
    }
}
