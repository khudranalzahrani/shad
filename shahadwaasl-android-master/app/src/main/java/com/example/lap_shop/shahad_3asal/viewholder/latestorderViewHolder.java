package com.example.lap_shop.shahad_3asal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lap_shop.shahad_3asal.R;

/**
 * Created by Ahmed Salamony on 6/28/2017.
 */

public class latestorderViewHolder extends RecyclerView.ViewHolder  {

    TextView tv_status,tv_date,tv_products,tv_total;

    public TextView getTv_status() {
        return tv_status;
    }

    public void setTv_status(TextView tv_status) {
        this.tv_status = tv_status;
    }

    public TextView getTv_date() {
        return tv_date;
    }

    public void setTv_date(TextView tv_date) {
        this.tv_date = tv_date;
    }

    public TextView getTv_products() {
        return tv_products;
    }

    public void setTv_products(TextView tv_products) {
        this.tv_products = tv_products;
    }

    public TextView getTv_total() {
        return tv_total;
    }

    public void setTv_total(TextView tv_total) {
        this.tv_total = tv_total;
    }

    public latestorderViewHolder(View itemView) {
        super(itemView);
        tv_status=(TextView)itemView.findViewById(R.id.tv_status);
        tv_date=(TextView)itemView.findViewById(R.id.tv_date);
        tv_products=(TextView)itemView.findViewById(R.id.tv_product);
        tv_total=(TextView)itemView.findViewById(R.id.tv_total);
    }

}
