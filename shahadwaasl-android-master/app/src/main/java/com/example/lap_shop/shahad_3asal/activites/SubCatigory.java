package com.example.lap_shop.shahad_3asal.activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.models.CatigoryModel;
import com.example.lap_shop.shahad_3asal.tools.LanguageManager;
import com.example.lap_shop.shahad_3asal.viewholder.SubcatigoresHolder;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SubCatigory extends AppCompatActivity {
    private RecyclerView recyclerView;
    Toolbar toolbar;
    static String namee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageManager.setAppLanguage(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_catigory);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (LanguageManager.getLanguage(getApplicationContext()).equals("ar")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("font.TTF")
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }

        //
        getSupportActionBar().setTitle(getString(R.string.empty));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        if (getIntent().hasExtra("Catigories_list")) {
            namee = getIntent().getStringExtra("Name");
            ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(Html.fromHtml(namee));
            List<CatigoryModel.DataModel.categoriesModel> subcatigoryList = getIntent().getParcelableArrayListExtra("Catigories_list");

            adddata(subcatigoryList);
        }
    }


    private void adddata(final List<CatigoryModel.DataModel.categoriesModel> data) {

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data, R.layout.subcatigory_row, SubcatigoresHolder.class,
                new RecyclerAdapter.AdapterInterface<CatigoryModel.DataModel.categoriesModel, SubcatigoresHolder>() {
                    @Override
                    public void fillData(final CatigoryModel.DataModel.categoriesModel dataModel, SubcatigoresHolder viewHolder) {
                        viewHolder.getTextView().setText(Html.fromHtml(dataModel.getName()));

                        Glide.with(SubCatigory.this)
                                .load(dataModel.getImage()).centerCrop()
                                .placeholder(R.drawable.muzdan_logo1)
                                .crossFade()
                                .into(viewHolder.getImageView());
                        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                SubCatigory.startActivityWithCategoryId(SubCatigory.this, dataModel.getCategory_id(), dataModel.getName());
                            }
                        });
                    }
                });
        recyclerView.setAdapter(recyclerAdapter);


    }

    public static void startActivityWithCategoryId(Context context, String name, String Kind) {
        Intent intent = new Intent(context, ProductsList.class);

        intent.putExtra("Category_id", name);
        intent.putExtra("Name", namee);
        intent.putExtra("Kind", Kind);
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
