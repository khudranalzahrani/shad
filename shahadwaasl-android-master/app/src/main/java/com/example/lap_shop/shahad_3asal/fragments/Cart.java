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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.activites.MainScreen;
import com.example.lap_shop.shahad_3asal.activites.ProductDetails;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.dialogs.CustomDialog;
import com.example.lap_shop.shahad_3asal.dialogs.PaymentDetailsDialog;
import com.example.lap_shop.shahad_3asal.models.AddToCartModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.LogInManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;
import com.example.lap_shop.shahad_3asal.viewholder.CartlistHolder;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Cart extends BackableFragment {

    private RecyclerView recyclerView;
    private static Fragment fragment;
    View rootview;
    Float total;
    int items = 0;
    private LoadingManager loadingManager;
    Button btn_checkout;
    private boolean stock = true;

    public static boolean cart_count = false;

    public static Fragment newInstance() {

        fragment = new Cart();
        return fragment;
    }

    public Cart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = rootview.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        loadingManager = new LoadingManager(getActivity(), rootview.findViewById(R.id.progressBar));
        loadingManager.showLoadingProgress();
        getcartlist();
        btn_checkout = rootview.findViewById(R.id.btn_checkout);
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LogInManager.isUserLoggedIn(getActivity(), true, false)) {
                    // new PaymentDetailsDialog(getActivity(), Integer.parseInt(getActivity().getIntent().getStringExtra("Category_id"))).show();
                    if (stock == true) {
                        new PaymentDetailsDialog(getActivity(), 1).show();
                    } else {
                        Toast.makeText(getActivity(), " out of stock ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), " please login  ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView tv = rootview.findViewById(R.id.tv_continue_from_cart);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainScreen.class);
                startActivity(i);
            }
        });
        return rootview;
    }

    public void getcartlist() {
        APIManager.GetCart(getActivity(), LogInManager.getUserSession(getActivity()), new APIManager.ResponseListener<AddToCartModel>() {
            @Override
            public void done(AddToCartModel dataModel) {
                if (dataModel.getSuccess()) {
                    loadingManager.hideLoadingProgress();
                    if (dataModel.getData().getProductsModelList().size() != 0) {
                        adddata(dataModel.getData().getProductsModelList());
                        rootview.findViewById(R.id.main).setVisibility(View.VISIBLE);
                        ((TextView) rootview.findViewById(R.id.total)).setText(dataModel.getData().getTotal());
                        total = Float.parseFloat(dataModel.getData().getTotal_raw());
                        items = Integer.parseInt(dataModel.getData().getTotal_product_count());
                    } else {
                        rootview.findViewById(R.id.empty).setVisibility(View.VISIBLE);
                    }
                } else {
                    loadingManager.hideLoadingProgress();
                    rootview.findViewById(R.id.empty).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void failed(boolean fromConnection) {
            }
        });
    }

    public void adddata(final List<AddToCartModel.DataModel.productsModel> data) {


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(data, R.layout.row_cartlist, CartlistHolder.class,
                new RecyclerAdapter.AdapterInterface<AddToCartModel.DataModel.productsModel, CartlistHolder>() {
                    @Override
                    public void fillData(final AddToCartModel.DataModel.productsModel dataModel, final CartlistHolder viewHolder) {
                        viewHolder.getTextView().setText(Html.fromHtml(dataModel.getName()));
                        viewHolder.getPrice().setText(Html.fromHtml(dataModel.getPrice()));
                        viewHolder.getQty().setText(getString(R.string.qty) + " " + dataModel.getQuantity());
                        if (!dataModel.getStock()) {
                            stock = dataModel.getStock();
                            viewHolder.getOut().setText(getString(R.string.outstock));
                            viewHolder.getPlus().setOnClickListener(null);
                            viewHolder.getPlus().setEnabled(false);
                            viewHolder.getPlus().setImageResource(R.drawable.plusdimmed);


                        }
                        Glide.with(getActivity())
                                .load(dataModel.getThumb())
                                .fitCenter()
                                .placeholder(R.drawable.muzdan_logo1)
                                .crossFade()
                                .into(viewHolder.getImageView());
                        viewHolder.getView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivityWithCategoryId(getActivity(), dataModel.getProduct_id());

                                //  SubCatigory.startActivityWithCategoryId(SubCatigory.this, dataModel.getCategory_id(),dataModel.getName());
                            }
                        });
                        viewHolder.getMines().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                updaterow(viewHolder.getAdapterPosition(), data, dataModel.getKey(),
                                        (Integer.parseInt(dataModel.getQuantity()) - 1 + ""));
                                total = total - Float.parseFloat(dataModel.getPrice().substring(0,
                                        dataModel.getPrice().length() - 4));
                                items = items - 1;
                                stock = true;

                                cart_count = true;


                            }
                        });

                        viewHolder.getPlus().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                updaterow(viewHolder.getAdapterPosition(), data, dataModel.getKey(), (Integer.parseInt(dataModel.getQuantity()) + 1 + ""));

                                total = total + Float.parseFloat(dataModel.getPrice().substring(0, dataModel.getPrice().length() - 4));
                                items = items + 1;
                                stock = true;

                                cart_count = true;


                            }
                        });

                        viewHolder.getView().setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                final CustomDialog x = new CustomDialog(getActivity(), getActivity().getString(R.string.delete));

                                x.show();
                                x.setOkButtonClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                        //   deletfromwishlist(viewHolder.getAdapterPosition(), data, Integer.parseInt(dataModel.getKey()));


                                        /////////////////////////////////////////////////////////////////////////////////
                                        updaterow(viewHolder.getAdapterPosition(), data, dataModel.getKey(),
                                                (Integer.parseInt(dataModel.getQuantity()) - 1 + ""));
                                        total = total - Float.parseFloat(dataModel.getPrice().substring(0,
                                                dataModel.getPrice().length() - 4));
                                        items = items - 1;
                                        stock = true;
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


    public void removeItem(int position, List<AddToCartModel.DataModel.productsModel> data) {
        data.remove(position);
        recyclerView.getAdapter().notifyItemRemoved(position);
    }

    public void update(int position, List<AddToCartModel.DataModel.productsModel> data, String quantity) {
        if (quantity.equals("0")) {
            data.remove(position);
            recyclerView.getAdapter().notifyItemRemoved(position);
        } else {
            data.get(position).setQuantity(quantity);
            recyclerView.getAdapter().notifyItemChanged(position);
        }

        if (items != 0) {
            ((TextView) rootview.findViewById(R.id.total)).setText(items + " " + getString(R.string.item) + " - " + total + " " + getString(R.string.sar));
        } else {
            rootview.findViewById(R.id.empty).setVisibility(View.VISIBLE);
            rootview.findViewById(R.id.main).setVisibility(View.GONE);
        }
    }

    public static void startActivityWithCategoryId(Context context, String id) {
        Intent intent = new Intent(context, ProductDetails.class);
        intent.putExtra("Category_id", id);
        intent.putExtra("where", 2);
        context.startActivity(intent);
    }

    public void updaterow(final int position, final List<AddToCartModel.DataModel.productsModel> data, String id, final String quantity) {
        APIManager.Updatecart(getActivity(), id, quantity, LogInManager.getUserSession(getActivity()), new APIManager.ResponseListener<AddToCartModel>() {
            @Override
            public void done(AddToCartModel dataModel) {
                if (dataModel.getSuccess()) {
                    update(position, data, quantity);
                }
            }

            @Override
            public void failed(boolean fromConnection) {

            }
        });

    }

    public void deletfromwishlist(final int position, final List<AddToCartModel.DataModel.productsModel> data, int id) {
        APIManager.DeletCart(getActivity(), id, LogInManager.getUserSession(getActivity()), new APIManager.ResponseListener<AddToCartModel>() {
            @Override
            public void done(AddToCartModel dataModel) {
                if (dataModel.getSuccess()) {
                    removeItem(position, data);
                    Toast.makeText(getActivity(), getString(R.string.deletitem), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failed(boolean fromConnection) {

            }
        });
    }


    @Override
    public void onBackButtonPressed() {
        if (ProductDetails.flag_addedToCart==1) {

            /*Intent intent = new Intent(getActivity(), ProductDetails.class);
            intent.putExtra("Category_id", DataManager.getStringSetting(getActivity(), "Back_Category_id", ""));
            intent.putExtra("where", 1);
            startActivity(intent);*/

        } else if (MainScreen.cart_back == 6) {
            Intent intent = new Intent(getActivity(), MainScreen.class);
            intent.putExtra("where", 4);
            startActivity(intent);
        } else if (MainScreen.cart_back == 7) {
            Intent intent = new Intent(getActivity(), MainScreen.class);
            intent.putExtra("where", 5);
            startActivity(intent);
        } else if (MainScreen.cart_back == 8) {
            Intent intent = new Intent(getActivity(), MainScreen.class);
            intent.putExtra("where", 6);
            startActivity(intent);
        } else if (MainScreen.cart_back == 9) {
            Intent intent = new Intent(getActivity(), MainScreen.class);
            intent.putExtra("where", 6);
            startActivity(intent);
        } else if (MainScreen.cart_back == 10) {
            Intent intent = new Intent(getActivity(), MainScreen.class);
            intent.putExtra("where", 7);
            startActivity(intent);
        } else if (MainScreen.cart_back == 3) {
            Intent intent = new Intent(getActivity(), MainScreen.class);
            intent.putExtra("where", 8);
            startActivity(intent);
        } else if (MainScreen.cart_back == 5) {
            Intent intent = new Intent(getActivity(), MainScreen.class);
            intent.putExtra("where", 9);
            startActivity(intent);
        } else if (MainScreen.cart_back == 4) {
            Intent intent = new Intent(getActivity(), MainScreen.class);
            intent.putExtra("where", 10);
            startActivity(intent);
        } else {
            Intent i = new Intent(getActivity(), MainScreen.class);
            startActivity(i);
            //   getActivity().onBackPressed();
        }
    }
}
