package com.example.lap_shop.shahad_3asal.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.models.latestordersModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.DataManager;
import com.example.lap_shop.shahad_3asal.tools.LogInManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;
import com.example.lap_shop.shahad_3asal.viewholder.latestorderViewHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class latestorder extends Fragment {
    private static Fragment fragment;
    private RecyclerView recyclerView;
    View rootview;
    private LoadingManager loadingManager;

    public static Fragment newInstance() {

        fragment = new latestorder();
        return fragment;
    }

    public latestorder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_latestorder, container, false);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        loadingManager = new LoadingManager(getActivity(), rootview.findViewById(R.id.progressBar));
        loadingManager.showLoadingProgress();
        displayLatestOrders();
        return rootview;
    }

    public void displayLatestOrders(){

        DataManager.getStringSetting(getActivity(),"customerID","default");
        APIManager.getLatestOrders(getActivity(), DataManager.getStringSetting(getActivity(),"customerID","default") ,LogInManager.getUserSession(getActivity()), new APIManager.ResponseListener<latestordersModel>() {
            @Override
            public void done(latestordersModel dataModel) {
                if (dataModel.isSuccess()) {
                    loadingManager.hideLoadingProgress();
                    if (dataModel.getDataModelList().size() != 0) {
                        adddata(dataModel.getDataModelList());
                    }
                } else {
                    loadingManager.hideLoadingProgress();
                    Toast.makeText(getActivity(), "not success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failed(boolean fromConnection) {
                Toast.makeText(getActivity(), R.string.msg_login, Toast.LENGTH_SHORT).show();
             loadingManager.hideLoadingProgress();
            }
        });
    }


    public void adddata(final List<latestordersModel.DataModel> data) {

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data, R.layout.latest_orders_row, latestorderViewHolder.class,
                new RecyclerAdapter.AdapterInterface<latestordersModel.DataModel, latestorderViewHolder>() {
                    @Override
                    public void fillData(final latestordersModel.DataModel dataModel, final latestorderViewHolder viewHolder) {
                        viewHolder.getTv_status().setText(Html.fromHtml(dataModel.getStatus()));
                        viewHolder.getTv_date().setText(Html.fromHtml(dataModel.getDate_added()));
                        viewHolder.getTv_products().setText(Html.fromHtml(String.valueOf((dataModel.getProducts()))));
                        viewHolder.getTv_total().setText(Html.fromHtml(dataModel.getTotal()));
                    }
                });
        recyclerView.setAdapter(recyclerAdapter);
    }

}
