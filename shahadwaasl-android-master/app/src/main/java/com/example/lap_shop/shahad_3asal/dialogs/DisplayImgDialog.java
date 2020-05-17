package com.example.lap_shop.shahad_3asal.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.activites.ProductDetails;
import com.example.lap_shop.shahad_3asal.models.ProductDetailsModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ahmed on 6/18/2017.
 */

public class DisplayImgDialog extends Dialog {
    private SliderLayout mDemoSlider;
    RecyclerView recyclerView;
    LoadingManager loadingManager;
    Activity activity;

    public DisplayImgDialog(Activity activity) {
        super(activity);
        setContentView(R.layout.activity_separated_img);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.activity = activity;
        loadingManager = new LoadingManager(activity, findViewById(R.id.progressBar));
        loadingManager.showLoadingProgress();
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        mDemoSlider.stopAutoCycle();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //mDemoSlider.startAutoCycle();

                mDemoSlider.stopAutoCycle();
            }
        }, 5000);

        filldata(ProductDetails.category);


    }
    private void filldata(String id) {
        APIManager.GetAllProductsDetails(activity, id, new APIManager.ResponseListener<ProductDetailsModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void done(final ProductDetailsModel dataModel) {
                if (dataModel.getSuccess()) {
                    loadingManager.hideLoadingProgress();

                    List<String> data = new ArrayList<String>();
                    data = dataModel.getData().getImageModelList();
                    addslider(data);

                }
            }

            @Override
            public void failed(boolean fromConnection) {

            }

        });
    }
    private void addslider(final List<String> data) {

        HashMap<String, String> url_maps = new HashMap<String, String>();

        for (int i = 0; i < data.size(); i++) {

            url_maps.put(i + "", data.get(i));
        }


        for (String name : url_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(activity);
            // initialize a SliderLayout
            textSliderView
                    .description("")
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop);


            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }

        // mDemoSlider.setDrawingCacheBackgroundColor(Color.RED);//Spiner Color
        mDemoSlider.setBackgroundColor(activity.getResources().getColor(R.color.trnsparent));
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);

        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
    }
}

