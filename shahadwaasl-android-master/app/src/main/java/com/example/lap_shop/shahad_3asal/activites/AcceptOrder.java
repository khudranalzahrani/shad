package com.example.lap_shop.shahad_3asal.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.models.AcceptOrderModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.LanguageManager;
import com.example.lap_shop.shahad_3asal.tools.LogInManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;
import com.example.lap_shop.shahad_3asal.viewholder.AcceptOrderHolder;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AcceptOrder extends AppCompatActivity {

    RecyclerView recyclerView;
    LoadingManager loadingManager;
    TextView tv_totalAllProducts;
    Button btn_accept;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_order);


        if (LanguageManager.getLanguage(getApplicationContext()).equals("ar")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("font.TTF")
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        } else {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("en.TTF")
                    .setFontAttrId(R.attr.fontPath)
                    .build());

        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.paymentAddress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadingManager = new LoadingManager(this, findViewById(R.id.progressBar));
        loadingManager.showLoadingProgress();

        recyclerView = (RecyclerView) findViewById(R.id.recycler1);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(layoutManager);
        tv_totalAllProducts = (TextView) findViewById(R.id.tv_totalallproducts);
        btn_accept = (Button) findViewById(R.id.btn_accept);
        getSavedAddresses();

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 putSimpleConfirm();
            }
        });
    }

    public void putSimpleConfirm() {
        APIManager.putSimpleConfirm(this,LogInManager.getUserSession(this), new APIManager.ResponseListener<AcceptOrderModel>() {
            @Override
            public void done(AcceptOrderModel dataModel) {
                if (dataModel.isSuccess()) {
                    loadingManager.hideLoadingProgress();
                    Toast.makeText(AcceptOrder.this, R.string.ToastConfirm, Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(AcceptOrder.this,MainScreen.class);
                    i.putExtra("where",0);
                    startActivity(i);
                }     else {
                    Toast.makeText(AcceptOrder.this, R.string.ToastConfirmPrev, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failed(boolean fromConnection) {

            }



    });
    }


    public void getSavedAddresses() {
        APIManager.get_SimpleConfirm(this, LogInManager.getUserSession(this), new APIManager.ResponseListener<AcceptOrderModel>() {

            @Override
            public void done(AcceptOrderModel dataModel) {
                if (dataModel.isSuccess()) {
                    loadingManager.hideLoadingProgress();


                    if (dataModel.getData().getProductsModels().size() != 0) {
                        adddata(dataModel.getData().getProductsModels());
                    } else {
                        //rootview.findViewById(R.id.empty).setVisibility(View.VISIBLE);
                    }
                    if (dataModel.getData().getTotalModels().size() != 0) {
                        tv_totalAllProducts.setText(dataModel.getData().getTotalModels().get(1).getText());
                    }
                } else {
                    loadingManager.hideLoadingProgress();
                    Toast.makeText(AcceptOrder.this, "not success", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void failed(boolean fromConnection) {
                Toast.makeText(AcceptOrder.this, "fail", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void adddata(final List<AcceptOrderModel.DataModel.productsModel> data) {


        recyclerView.setAdapter(new RecyclerAdapter(data, R.layout.row_accept_order, AcceptOrderHolder.class,
                new RecyclerAdapter.AdapterInterface<AcceptOrderModel.DataModel.productsModel, AcceptOrderHolder>() {
                    @Override
                    public void fillData(final AcceptOrderModel.DataModel.productsModel dataModel, final AcceptOrderHolder viewHolder) {
                        viewHolder.getTv_productName().setText(Html.fromHtml(dataModel.getName()));
                        viewHolder.getTv_productPrice().setText(Html.fromHtml(dataModel.getPrice()));
                        viewHolder.getTv_quantity().setText(Html.fromHtml(dataModel.getQuantity()));
                        viewHolder.getTv_totalProducrPrice().setText(Html.fromHtml(dataModel.getTotal()));

                    }
                }));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
