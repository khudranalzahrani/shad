package com.example.lap_shop.shahad_3asal.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.activites.ProductsList;
import com.example.lap_shop.shahad_3asal.activites.SubCatigory;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.models.CatigoryModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;
import com.example.lap_shop.shahad_3asal.viewholder.CatigoresHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Catigory_fragment extends Fragment {
    private RecyclerView recyclerView;
    private LoadingManager loadingManager;
    private static Fragment fragment;
    View rootview;

    public static Fragment newInstance() {

        fragment = new Catigory_fragment();
        return fragment;
    }

    public Catigory_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_catigory_fragment, container, false);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler);
        loadingManager = new LoadingManager(getActivity(), rootview.findViewById(R.id.progressBar));
        loadingManager.showLoadingProgress();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        getProducts();
        return rootview;
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
                rootview.findViewById(R.id.recycler).setVisibility(View.VISIBLE);
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

                        Glide.with(Catigory_fragment.this)
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


}
