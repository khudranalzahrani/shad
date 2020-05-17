package com.example.lap_shop.shahad_3asal.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.activites.ProductsList;
import com.example.lap_shop.shahad_3asal.activites.SubCatigory;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.models.CatigoryModel;
import com.example.lap_shop.shahad_3asal.models.SliderModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;
import com.example.lap_shop.shahad_3asal.viewholder.CatigoresHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment implements BaseSliderView.OnSliderClickListener{
    private View rootview;
    private static Fragment fragment;
    private SliderLayout mDemoSlider;
    private static RecyclerView recyclerView;
    private LoadingManager loadingManager;

    public static Fragment newInstance() {

        fragment = new Home();
        return fragment;
    }

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootview = inflater.inflate(R.layout.fragment_home, container, false);

        mDemoSlider = (SliderLayout) rootview.findViewById(R.id.slider);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler);
        loadingManager = new LoadingManager(getActivity(), rootview.findViewById(R.id.progressBar));
        loadingManager.showLoadingProgress();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//        recyclerView.setNestedScrollingEnabled(false);

        getProducts();


        getslider();


        return rootview;
    }

    public void getslider() {
        APIManager.GetAllSlider(getActivity(), new APIManager.ResponseListener<SliderModel>() {
            @Override
            public void done(SliderModel dataModel) {
                List<SliderModel.DataModel.BannersModel> data1 = new ArrayList<SliderModel.DataModel.BannersModel>();
                for (int i = 0; i < dataModel.getDataModelList().size(); i++) {
                    SliderModel.DataModel d = dataModel.getDataModelList().get(i);
                    for (int j = 0; j < d.getBannersModelList().size(); j++) {
                        data1.add(i, d.getBannersModelList().get(j));
                    }
                }
                addslider(data1);
            }
            @Override
            public void failed(boolean fromConnection) {
            }
        });
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        mDemoSlider.startAutoCycle();
    }

    private void addslider(final List<SliderModel.DataModel.BannersModel> data) {

        HashMap<String, String> url_maps = new HashMap<String, String>();

        for (int i = 0; i < data.size(); i++) {

            url_maps.put(data.get(i).getTitle() + "", data.get(i).getImage());
        }
        mDemoSlider.stopAutoCycle();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDemoSlider.startAutoCycle();
            }
        }, 4000);

        for (String name : url_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description("")
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);

        }



        // mDemoSlider.setDrawingCacheBackgroundColor(Color.RED);//Spiner Color
        mDemoSlider.setBackgroundColor(getResources().getColor(R.color.trnsparent));//hide the plack line
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);

        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.setCustomIndicator((PagerIndicator) rootview.findViewById(R.id.custom_indicator));

    }

    public void getProducts() {

        APIManager.GetALLCatigory(getActivity(), new APIManager.ResponseListener<CatigoryModel>() {
            @Override
            public void done(CatigoryModel dataModel) {
                List<CatigoryModel.DataModel> data1 = new ArrayList<CatigoryModel.DataModel>();
                for (int i = 0; i < dataModel.getDataModel().size(); i++) {
                    data1.add(i, dataModel.getDataModel().get(i));
                }
                adddata(data1);
                loadingManager.hideLoadingProgress();
                rootview.findViewById(R.id.Homeview).setVisibility(View.VISIBLE);
            }

            @Override
            public void failed(boolean fromConnection) {

            }
        });


    }

    private void adddata(final List<CatigoryModel.DataModel> data) {

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data, R.layout.catogres_row, CatigoresHolder.class,
                new RecyclerAdapter.AdapterInterface<CatigoryModel.DataModel, CatigoresHolder>() {
                    @Override
                    public void fillData(final CatigoryModel.DataModel dataModel, CatigoresHolder viewHolder) {
                        viewHolder.getTextView().setText(Html.fromHtml(dataModel.getName()));

                        Glide.with(Home.this)
                                .load(dataModel.getImage()).crossFade()
                                .placeholder(R.drawable.muzdan_logo1)
                                .into(viewHolder.getImgeView());
                        viewHolder.getMain().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(dataModel.getCategoriesModelList()!=null){

                                startActivityWithSubCatigoriesList(getActivity(),dataModel.getCategoriesModelList(),dataModel.getName());}else {
                                    startActivityWithCategoryId(getActivity(),dataModel.getCategory_id(),dataModel.getName());
                                }
                            }
                        });
                    }
                });
        recyclerView.setAdapter(recyclerAdapter);


    }

    public static void startActivityWithSubCatigoriesList(Context context, List<CatigoryModel.DataModel.categoriesModel> subcatogrieslist,String name) {
        Intent intent = new Intent(context, SubCatigory.class);
        intent.putParcelableArrayListExtra("Catigories_list", (ArrayList<? extends Parcelable>) subcatogrieslist);
        intent.putExtra("Name",name);
        context.startActivity(intent);
    }

    public static void startActivityWithCategoryId(Context context, String name,String Kind) {
        Intent intent = new Intent(context, ProductsList.class);

        intent.putExtra("Category_id", name);
        intent.putExtra("Name",Kind );
        intent.putExtra("Kind",Kind);
        context.startActivity(intent);
    }


//    public void getProducts() {
//
//        APIManager.GetLatestProduct(getActivity(), new APIManager.ResponseListener<LatestProduct>() {
//            @Override
//            public void done(LatestProduct dataModel) {
//                List<LatestProduct.ProductsListModel> data1 = new ArrayList<LatestProduct.ProductsListModel>();
//                for (int i = 0; i < dataModel.getProductsListModels().size(); i++) {
//                    data1.add(i, dataModel.getProductsListModels().get(i));
//                }
//                adddata(data1);
//                loadingManager.hideLoadingProgress();
//                rootview.findViewById(R.id.Homeview).setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void failed(boolean fromConnection) {
//
//            }
//        });
//    }
//
//    private void adddata(List<LatestProduct.ProductsListModel> data) {
//
//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data, R.layout.productrow, ProductViewholder.class,
//                new RecyclerAdapter.AdapterInterface<LatestProduct.ProductsListModel, ProductViewholder>() {
//                    @Override
//                    public void fillData(final LatestProduct.ProductsListModel dataModel, final ProductViewholder viewHolder) {
//                        viewHolder.NameProduct().setText(Html.fromHtml(dataModel.getName()));
//                        viewHolder.PriceProduct().setText(Html.fromHtml(dataModel.getPrice_formated()));
//                        Glide.with(Home.this)
//                                .load(dataModel.getThumb())
//                                .placeholder(R.drawable.smallicon)
//
//                                .into(viewHolder.GetImageProduct());
//
//                        ImageView[] rateImageViewsArray = {viewHolder.getStar1(), viewHolder.getStar2(),
//                                viewHolder.getStar3(), viewHolder.getStar4(), viewHolder.getStar5()};
//
//                        for (int i = 0; i < rateImageViewsArray.length; i++) {
//                            if (dataModel.getRating() > i) {
//                                rateImageViewsArray[i].setImageResource(R.drawable.starfill);
//                            } else {
//                                rateImageViewsArray[i].setImageResource(R.drawable.star);
//                            }
//                        }
//                        viewHolder.getLatest().setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                startActivityWithCategoryId(getActivity(), dataModel.getProduct_id());
//                            }
//                        });
//                    }
//                });
//        recyclerView.setAdapter(recyclerAdapter);
//    }
//
//    public static void startActivityWithCategoryId(Context context, String id) {
//        Intent intent = new Intent(context, ProductDetails.class);
//        intent.putExtra("Category_id", id);
//        intent.putExtra("where", 1);
//
//        context.startActivity(intent);
//    }

}
