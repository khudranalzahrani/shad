package com.example.lap_shop.shahad_3asal.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.activites.MainScreen;
import com.example.lap_shop.shahad_3asal.activites.ProductDetails;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.dialogs.CustomDialog;
import com.example.lap_shop.shahad_3asal.models.AddToCartModel;
import com.example.lap_shop.shahad_3asal.models.WishlistModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.LogInManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;
import com.example.lap_shop.shahad_3asal.viewholder.WishlistHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class wishlist extends Fragment {
    private RecyclerView recyclerView;
    private static Fragment fragment;
    View rootview;
    Button btn_cart;
    private LoadingManager loadingManager;
    wishlist wishlist;
    public static Fragment newInstance() {

        fragment = new wishlist();
        return fragment;
    }

    public wishlist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_wishlist, container, false);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        btn_cart=(Button)rootview.findViewById(R.id.add_to_cart);
        loadingManager = new LoadingManager(getActivity(), rootview.findViewById(R.id.progressBar));

        loadingManager.showLoadingProgress();
        getwishlist();

        TextView tv=(TextView)rootview.findViewById(R.id.tv_continue_from_cart);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), MainScreen.class);
                i.putExtra("where",6);
                startActivity(i);
            }
        });

        return rootview;
    }

    public void addCart(String id) {
        APIManager.AddCart(getActivity(),LogInManager.getUserSession(getActivity()),
                id, "1", new APIManager.ResponseListener<AddToCartModel>() {
            @Override
            public void done(AddToCartModel dataModel) {
                if (dataModel.getSuccess()) {
                    Toast.makeText(getContext(), R.string.msg_add_cart, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),R.string.msg_not_add_cart, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void failed(boolean fromConnection) {

            }
        });
    }

    public void getwishlist() {
        APIManager.GetUserWishlist(getActivity(), LogInManager.getUserSession(getActivity()), new APIManager.ResponseListener<WishlistModel>() {
            @Override
            public void done(WishlistModel dataModel) {
                if (dataModel.getSuccess()) {
                    loadingManager.hideLoadingProgress();
                    if (dataModel.getData().getProducrswishlistModelList().size() != 0) {
                        adddata(dataModel.getData().getProducrswishlistModelList());
                        rootview.findViewById(R.id.main).setVisibility(View.VISIBLE);
                    } else {
                        rootview.findViewById(R.id.empty).setVisibility(View.VISIBLE);
                    }
                }else {
                    loadingManager.hideLoadingProgress();
                    rootview.findViewById(R.id.empty).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void failed(boolean fromConnection) {

            }
        });
    }




    public void deletfromwishlist(final int position, final List<WishlistModel.DataModel.producrswishlistModel> data, final String id) {
        APIManager.DeleteUserWishlist(getActivity(), LogInManager.getUserSession(getActivity()),Integer.parseInt(id), new APIManager.ResponseListener<WishlistModel>() {
            @Override
            public void done(WishlistModel dataModel) {
                if (dataModel.getSuccess()) {
                    removeItem(position,data);

                    Log.e("idforwishlist",id);
                    Toast.makeText(getActivity(),getString(R.string.deletitem),Toast.LENGTH_LONG).show();

                    APIManager.GetUserWishlist(getActivity(), LogInManager.getUserSession(getActivity()), new APIManager.ResponseListener<WishlistModel>() {
                        @Override
                        public void done(WishlistModel dataModel) {
                            if (dataModel.getSuccess()) {
                                loadingManager.hideLoadingProgress();
                                if (dataModel.getData().getProducrswishlistModelList().size() != 0) {
                                    adddata(dataModel.getData().getProducrswishlistModelList());
                                    rootview.findViewById(R.id.main).setVisibility(View.VISIBLE);
                                } else {
                                    rootview.findViewById(R.id.empty).setVisibility(View.VISIBLE);
                                }
                            }else {
                                loadingManager.hideLoadingProgress();
                                rootview.findViewById(R.id.empty).setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void failed(boolean fromConnection) {

                        }
                    });
                }

            }

            @Override
            public void failed(boolean fromConnection) {

            }
        });
    }

    public void adddata(final List<WishlistModel.DataModel.producrswishlistModel> data) {


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data, R.layout.row_wishlist, WishlistHolder.class,
                new RecyclerAdapter.AdapterInterface<WishlistModel.DataModel.producrswishlistModel, WishlistHolder>() {
                    @Override
                    public void fillData(final WishlistModel.DataModel.producrswishlistModel dataModel, final WishlistHolder viewHolder)
                    {
                        viewHolder.getTextView().setText(Html.fromHtml(dataModel.getName()));
                        viewHolder.getPrice().setText(Html.fromHtml(dataModel.getPrice()));
                        viewHolder.getModel().setText(Html.fromHtml(dataModel.getModel()));

                        viewHolder.getBtn_cart().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (LogInManager.isUserLoggedIn(getActivity(), true, false)) {
                                   // addCart(getActivity().getIntent().getStringExtra("Category_id"));
                                    addCart(ProductDetails.category);

                                }
                            }
                        });
                        Glide.with(getActivity())
                                .load(dataModel.getThumb()).centerCrop()
                                .placeholder(R.drawable.muzdan_logo)
                                .crossFade()
                                .into(viewHolder.getImageView());
                        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivityWithCategoryId(getActivity(), dataModel.getProduct_id());



                                //  SubCatigory.startActivityWithCategoryId(SubCatigory.this, dataModel.getCategory_id(),dataModel.getName());
                            }
                        });


                        viewHolder.getView().setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                               final CustomDialog x=new CustomDialog(getActivity(),getActivity().getString(R.string.delete));

                                x.show();
                                x.setOkButtonClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        deletfromwishlist(viewHolder.getAdapterPosition(),data,dataModel.getProduct_id());

                                        x.dismiss();

                                    }
                                });
                                return false;
                            }
                        });
                    }
                });



        recyclerView.setAdapter(recyclerAdapter);

    }


    public void removeItem(int position, List<WishlistModel.DataModel.producrswishlistModel> data) {
        data.remove(position);
       recyclerView.getAdapter().notifyItemRemoved(position);
    }

    public static void startActivityWithCategoryId(Context context, String id) {
        Intent intent = new Intent(context, ProductDetails.class);
        intent.putExtra("Category_id", id);
        intent.putExtra("where", 2);
        context.startActivity(intent);
    }
}
