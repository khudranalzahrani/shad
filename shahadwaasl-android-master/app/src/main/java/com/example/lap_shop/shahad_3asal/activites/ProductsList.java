package com.example.lap_shop.shahad_3asal.activites;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.models.ProductsModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.LanguageManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;
import com.example.lap_shop.shahad_3asal.viewholder.ProductViewholder;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductsList extends AppCompatActivity {
    static RecyclerView recyclerView;
    Toolbar toolbar;
    private LoadingManager loadingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageManager.setAppLanguage(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        if (LanguageManager.getLanguage(getApplicationContext()).equals("ar")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("font.TTF")
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        loadingManager = new LoadingManager(ProductsList.this, findViewById(R.id.progressBar));
        loadingManager.showLoadingProgress();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.empty));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(ProductsList.this, 2));
        findViewById(R.id.row).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setLayoutManager(new GridLayoutManager(ProductsList.this, 1));
                ((ImageView) findViewById(R.id.row)).setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
                ((ImageView) findViewById(R.id.item)).setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.SRC_IN);
            }
        });
        findViewById(R.id.item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setLayoutManager(new GridLayoutManager(ProductsList.this, 2));
                ((ImageView) findViewById(R.id.item)).setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
                ((ImageView) findViewById(R.id.row)).setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.SRC_IN);

            }
        });
        if (getIntent().hasExtra("Name")) {
            ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(Html.fromHtml(getIntent().getStringExtra("Name")));

            ((TextView) (findViewById(R.id.category_name))).setText(Html.fromHtml(getIntent().getStringExtra("Kind")));
            APIManager.GetAllProducts(ProductsList.this, getIntent().getStringExtra("Category_id"), new APIManager.ResponseListener<ProductsModel>() {
                @Override
                public void done(ProductsModel dataModel) {
                    if (dataModel.getSuccess()) {

                        ((TextView) (findViewById(R.id.Product_num))).setText(dataModel.getDataModelList().size() + " " + getApplicationContext().getString(R.string.product));

                        adddata(dataModel.getDataModelList());
                        findViewById(R.id.mainview).setVisibility(View.VISIBLE);
                    } else {
                        findViewById(R.id.empty).setVisibility(View.VISIBLE);
                        findViewById(R.id.cont).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i=new Intent(ProductsList.this,MainScreen.class);
                                i.putExtra("where",66);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                            }
                        });
                    }

                    loadingManager.hideLoadingProgress();
                }

                @Override
                public void failed(boolean fromConnection) {

                }
            });

        }
    }

    private void adddata(List<ProductsModel.DataModel> data) {

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data, R.layout.productrow, ProductViewholder.class,
                new RecyclerAdapter.AdapterInterface<ProductsModel.DataModel, ProductViewholder>() {
                    @Override
                    public void fillData(final ProductsModel.DataModel dataModel, final ProductViewholder viewHolder) {
                        viewHolder.NameProduct().setText(Html.fromHtml(dataModel.getName()));
                        viewHolder.PriceProduct().setText(Html.fromHtml(dataModel.getPrice_formated()));
                        Glide.with(ProductsList.this)

                                .load(dataModel.getImage()).crossFade()
                                .placeholder(R.drawable.muzdan_logo1)
                                .into(viewHolder.GetImageProduct());
                        ImageView[] rateImageViewsArray = {viewHolder.getStar1(), viewHolder.getStar2(),
                                viewHolder.getStar3(), viewHolder.getStar4(), viewHolder.getStar5()};
                        for (int i = 0; i < rateImageViewsArray.length; i++) {
                            if (dataModel.getRating() > i) {
                                rateImageViewsArray[i].setImageResource(R.drawable.starfill);
                            } else {
                                rateImageViewsArray[i].setImageResource(R.drawable.star);
                            }
                        }

                        viewHolder.getLatest().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivityWithCategoryId(ProductsList.this, dataModel.getId());

                                System.out.println(viewHolder.getAdapterPosition() + "ejfuieyrui");
                            }
                        });
                    }
                });
        recyclerView.setAdapter(recyclerAdapter);


    }



    public static void startActivityWithCategoryId(Context context, String id) {
        Intent intent = new Intent(context, ProductDetails.class);
        intent.putExtra("Category_id", id);
        intent.putExtra("where", 2);
        context.startActivity(intent);
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

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
