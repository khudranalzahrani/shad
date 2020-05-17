package com.example.lap_shop.shahad_3asal.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.activites.AcceptOrder;
import com.example.lap_shop.shahad_3asal.activites.NewPaymentAddress;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.models.RegisteredAddresses;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.LogInManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;
import com.example.lap_shop.shahad_3asal.viewholder.AddressHolder;

import java.util.List;

/**
 * Created by Ahmed Salamony on 6/6/2017.
 */

public class PaymentDetailsDialog extends Dialog {

    TextView iv_closeDialog;
    RadioButton rb_savedAddress, rb_newAddress;
    RecyclerView recyclerView;
    LoadingManager loadingManager;
    Activity activity;
    public int flag = 0;


    public PaymentDetailsDialog(final Activity activity, int category_id) {
        super(activity);
        setContentView(R.layout.dialog_payment_details);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        this.activity = activity;
        iv_closeDialog = (TextView) findViewById(R.id.iv_closedialog);
        rb_savedAddress = (RadioButton) findViewById(R.id.rb_savedaddress);
        rb_newAddress = (RadioButton) findViewById(R.id.rb_newaddress);

        recyclerView = (RecyclerView) findViewById(R.id.recycler1);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 1));
        iv_closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        rb_newAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Intent intent = new Intent(activity, NewPaymentAddress.class);
                activity.startActivity(intent);
            }
        });

        rb_savedAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag++;
                loadingManager = new LoadingManager(activity, findViewById(R.id.progressBar));
                loadingManager.showLoadingProgress();
                rb_newAddress.setVisibility(View.INVISIBLE);
                getSavedAddresses();




                if (flag == 2) {
                    recyclerView.setVisibility(View.GONE);
                    rb_savedAddress.setChecked(false);
                    rb_newAddress.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void getSavedAddresses() {
        APIManager.getRegisteredAddresses(activity, LogInManager.getUserSession(activity),
                new APIManager.ResponseListener<RegisteredAddresses>() {
                    @Override
                    public void done(RegisteredAddresses dataModel) {
                        if (dataModel.isSuccess()) {
                            loadingManager.hideLoadingProgress();
                            if (dataModel.getData().getAddressesModel().size() != 0) {

                                adddata(dataModel.getData().getAddressesModel());

                                recyclerView.setVisibility(View.VISIBLE);
                                if (flag==2){
                                    recyclerView.setVisibility(View.GONE);
                                }
                            } else {

                            }
                        } else {
                            loadingManager.hideLoadingProgress();
                            rb_newAddress.setVisibility(View.VISIBLE);
                            Toast.makeText(activity, R.string.no_payment_address, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void failed(boolean fromConnection) {

                    }
                });
    }


    public void adddata(final List<RegisteredAddresses.DataModel.AddressesModel> data) {
        recyclerView.setAdapter(new RecyclerAdapter(data, R.layout.row_address, AddressHolder.class,
                new RecyclerAdapter.AdapterInterface<RegisteredAddresses.DataModel.AddressesModel, AddressHolder>() {
                    @Override
                    public void fillData(final RegisteredAddresses.DataModel.AddressesModel dataModel, final AddressHolder viewHolder) {
                        viewHolder.getFullAddress().setText(Html.fromHtml(dataModel.getAddress_1() +
                                "," + dataModel.getCity() + "," + dataModel.getZone() + "," + dataModel.getCountry()));

                        viewHolder.getFullAddress().setOnClickListener(new View.OnClickListener() {
                                                                           @Override
                                                                           public void onClick(View view) {
                                                                               Intent intent = new Intent(activity, AcceptOrder.class);
                                                                               activity.startActivity(intent);
                                                                           }

                                                                       }
                        );
                    }
                }));
    }

    public PaymentDetailsDialog ivCloseDialogClicked(View.OnClickListener ivclicked) {
        iv_closeDialog.setOnClickListener(ivclicked);
        return this;
    }

    public PaymentDetailsDialog rbSavedAddressClicked(CompoundButton.OnCheckedChangeListener rbsaveclicked) {
        rb_savedAddress.setOnCheckedChangeListener(rbsaveclicked);
        return this;
    }


    public PaymentDetailsDialog rbNewAddressClicked(CompoundButton.OnCheckedChangeListener rbnewclicked) {
        rb_newAddress.setOnCheckedChangeListener(rbnewclicked);
        return this;
    }
}
