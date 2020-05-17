package com.example.lap_shop.shahad_3asal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lap_shop.shahad_3asal.R;

/**
 * Created by Ahmed Salamony on 6/8/2017.
 */

public class AcceptOrderHolder extends RecyclerView.ViewHolder {

    private TextView tv_productName,tv_productPrice,tv_quantity,tv_totalProducrPrice;

    public AcceptOrderHolder(View itemView) {
        super(itemView);
        tv_productName=(TextView)itemView.findViewById(R.id.tv_product_name);
        tv_productPrice=(TextView)itemView.findViewById(R.id.tv_product_price);
        tv_quantity=(TextView)itemView.findViewById(R.id.tv_quantity);
        tv_totalProducrPrice=(TextView)itemView.findViewById(R.id.tv_product_total);

    }

    public TextView getTv_productName() {
        return tv_productName;
    }

    public void setTv_productName(TextView tv_productName) {
        this.tv_productName = tv_productName;
    }

    public TextView getTv_productPrice() {
        return tv_productPrice;
    }

    public void setTv_productPrice(TextView tv_productPrice) {
        this.tv_productPrice = tv_productPrice;
    }

    public TextView getTv_quantity() {
        return tv_quantity;
    }

    public void setTv_quantity(TextView tv_quantity) {
        this.tv_quantity = tv_quantity;
    }

    public TextView getTv_totalProducrPrice() {
        return tv_totalProducrPrice;
    }

    public void setTv_totalProducrPrice(TextView tv_totalProducrPrice) {
        this.tv_totalProducrPrice = tv_totalProducrPrice;
    }
}
