package com.example.lap_shop.shahad_3asal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lap_shop.shahad_3asal.R;

/**
 * Created by LAP_SHOP on 04/05/2017.
 */

public class ReviewsHolder extends RecyclerView.ViewHolder {
    private ImageView star1, star2, star3, star4, star5;
    private TextView username, date, review;

    public ReviewsHolder(View itemView) {
        super(itemView);
        star1 = (ImageView) itemView.findViewById(R.id.star1);
        star5 = (ImageView) itemView.findViewById(R.id.star5);
        star2 = (ImageView) itemView.findViewById(R.id.star2);
        star3 = (ImageView) itemView.findViewById(R.id.star3);
        star4 = (ImageView) itemView.findViewById(R.id.star4);
        username=(TextView)itemView.findViewById(R.id.username);
        date=(TextView)itemView.findViewById(R.id.date);
        review=(TextView)itemView.findViewById(R.id.review);
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

    public TextView getDate() {
        return date;
    }

    public TextView getReview() {
        return review;
    }

    public TextView getUsername() {
        return username;
    }
}
