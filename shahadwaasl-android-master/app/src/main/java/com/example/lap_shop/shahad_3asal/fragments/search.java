package com.example.lap_shop.shahad_3asal.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.activites.ProductDetails;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.models.ProductsModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;
import com.example.lap_shop.shahad_3asal.viewholder.ProductViewholder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class search extends Fragment {
    View rootview;
    SearchView searchView;

    private static Fragment fragment;
    private RecyclerView recyclerView;
    private LoadingManager loadingManager;

    public static Fragment newInstance() {

        fragment = new search();
        return fragment;
    }

    public search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        loadingManager = new LoadingManager(getActivity(), rootview.findViewById(R.id.progressBar));


        searchView = (SearchView) rootview.findViewById(R.id.searchView);

       // searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Search(query);
                rootview.findViewById(R.id.empty).setVisibility(View.GONE);
                loadingManager.showLoadingProgress();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //  Toast.makeText(getActivity(), newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });


        return rootview;
    }


    public void Search(String search) {


        APIManager.GetSearchProducts(getActivity(), search, new APIManager.ResponseListener<ProductsModel>() {
            @Override
            public void done(ProductsModel dataModel) {
                if (dataModel.getSuccess()) {


                    adddata(dataModel.getDataModelList());
                    rootview.findViewById(R.id.main).setVisibility(View.VISIBLE);


                } else {
                    rootview.findViewById(R.id.empty).setVisibility(View.VISIBLE);
                    ((TextView) rootview.findViewById(R.id.text)).setText(getString(R.string.nomatch));
                    rootview.findViewById(R.id.main).setVisibility(View.GONE);

                }

                loadingManager.hideLoadingProgress();
            }

            @Override
            public void failed(boolean fromConnection) {

            }
        });

    }

    private void adddata(List<ProductsModel.DataModel> data) {

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data, R.layout.productrow, ProductViewholder.class,
                new RecyclerAdapter.AdapterInterface<ProductsModel.DataModel, ProductViewholder>() {
                    @Override
                    public void fillData(final ProductsModel.DataModel dataModel, final ProductViewholder viewHolder) {
                        viewHolder.NameProduct().setText(Html.fromHtml(dataModel.getName()));
                        viewHolder.PriceProduct().setText(Html.fromHtml(dataModel.getPrice_formated()));
                        Glide.with(getActivity())

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
                                startActivityWithCategoryId(getActivity(), dataModel.getId());

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
    public void onResume() {
        super.onResume();
//        InputMethodManager inputMethodManager = (InputMethodManager)
//                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
   //     inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onPause() {
        super.onPause();
//        InputMethodManager inputMethodManager = (InputMethodManager)
//                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        InputMethodManager inputMethodManager = (InputMethodManager)
//                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }



}



