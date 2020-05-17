package com.example.lap_shop.shahad_3asal.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lap_shop.shahad_3asal.R;

/**
 * Created by Ahmed Salamony on 6/7/2017.
 */

public class AddressHolder extends RecyclerView.ViewHolder{

    TextView fullAddress;

    public AddressHolder(View itemView) {
        super(itemView);
        fullAddress=(TextView)itemView.findViewById(R.id.tv_fullAddress);
    }

    public TextView getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(TextView fullAddress) {
        this.fullAddress = fullAddress;
    }
}
