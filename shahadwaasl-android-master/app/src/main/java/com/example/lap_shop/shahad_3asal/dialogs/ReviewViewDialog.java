package com.example.lap_shop.shahad_3asal.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.models.ProductDetailsModel;
import com.example.lap_shop.shahad_3asal.viewholder.ReviewsHolder;

import java.util.List;

/**
 * Created by LAP_SHOP on 04/05/2017.
 */

public class ReviewViewDialog extends Dialog {
    RecyclerView recyclerView;
    public ReviewViewDialog(Activity context,List <ProductDetailsModel.DataModel.reviewsModel.reviewsdetailsModel >data) {
        super(context);
        setContentView(R.layout.dialog_view_review);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        adddata(data);

        ImageView img=(ImageView)findViewById(R.id.img_close);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }


    private void adddata(final List<ProductDetailsModel.DataModel.reviewsModel.reviewsdetailsModel> data) {

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data, R.layout.row_view_reviews, ReviewsHolder.class,
                new RecyclerAdapter.AdapterInterface<ProductDetailsModel.DataModel.reviewsModel.reviewsdetailsModel, ReviewsHolder>() {
                    @Override
                    public void fillData(final ProductDetailsModel.DataModel.reviewsModel.reviewsdetailsModel dataModel, ReviewsHolder viewHolder) {


                        viewHolder.getDate().setText(dataModel.getDate_added());
                        viewHolder.getReview().setText(dataModel.getText());
                        Log.e("get_",dataModel.getText()+" ");

                        viewHolder.getUsername().setText(dataModel.getAuthor());

                        ImageView[] rateImageViewsArray = {viewHolder.getStar1(), viewHolder.getStar2(),
                                viewHolder.getStar3(), viewHolder.getStar4(), viewHolder.getStar5()};



                        for (int i = 0; i < rateImageViewsArray.length; i++) {

                            if (dataModel.getRating() > i) {
                                rateImageViewsArray[i].setImageResource(R.drawable.starfill);

                            } else {
                                rateImageViewsArray[i].setImageResource(R.drawable.star);
                            }
                        }


                    }
                });

        recyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerAdapter);
    }


}
