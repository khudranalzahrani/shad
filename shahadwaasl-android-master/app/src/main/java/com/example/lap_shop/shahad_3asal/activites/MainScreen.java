package com.example.lap_shop.shahad_3asal.activites;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.adapters.NavigationAdapter;
import com.example.lap_shop.shahad_3asal.adapters.RecyclerAdapter;
import com.example.lap_shop.shahad_3asal.dialogs.CustomDialog;
import com.example.lap_shop.shahad_3asal.fragments.Cart;
import com.example.lap_shop.shahad_3asal.fragments.Catigory_fragment;
import com.example.lap_shop.shahad_3asal.fragments.Home;
import com.example.lap_shop.shahad_3asal.fragments.aboutus;
import com.example.lap_shop.shahad_3asal.fragments.contactus;
import com.example.lap_shop.shahad_3asal.fragments.latestorder;
import com.example.lap_shop.shahad_3asal.fragments.nearestStores;
import com.example.lap_shop.shahad_3asal.fragments.search;
import com.example.lap_shop.shahad_3asal.fragments.wishlist;
import com.example.lap_shop.shahad_3asal.models.AddToCartModel;
import com.example.lap_shop.shahad_3asal.models.LoginModel;
import com.example.lap_shop.shahad_3asal.models.NavigationModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.LanguageManager;
import com.example.lap_shop.shahad_3asal.tools.LogInManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainScreen extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    RecyclerView recyclerView;
    List<NavigationModel> list;
    private boolean isMainFragment;
    int back = 0;
    int i = -1;
    String userName;
    public static final String MyPREFERENCES = "MyPrefs";
    ImageView log_in_out;
    TextView navname;

    ImageView cart;
    TextView cart_count;
    Float total;
    int items = 0;
    private LoadingManager loadingManager;
    public static int cart_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageManager.setAppLanguage(getApplicationContext());

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_screen);
        if (LanguageManager.getLanguage(getApplicationContext()).equals("ar")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("font.TTF")
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        } else {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("en.TTF")
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Log.e("MainActivity", "onCreate");


        navname = findViewById(R.id.navname);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String name = prefs.getString("userName", "No name defined");
        navname.setText(name);

        recyclerView = findViewById(R.id.recycler);
        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(mLayoutManager);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cart = findViewById(R.id.cart);
        cart_count = findViewById(R.id.cart_number);
        getcartlist();
        setActionBarTitle(R.string.empty);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(drawerView.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        };
        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.nav_view);

        list = new ArrayList<NavigationModel>();
        if (getIntent().getExtras() != null) {
            Navigation(getIntent().getIntExtra("where", 0));
            ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(getIntent().getStringExtra("Title"));
            isMainFragment = false;
            if (getIntent().getIntExtra("where",0)==66){
                ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(R.string.home);
                userName=getIntent().getStringExtra("userName");
                navname.setText(userName);
                openFragment(Home.newInstance());
            }
            if (getIntent().getIntExtra("where", 0) == 4) {
                ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(R.string.wishlist);
                openFragment(wishlist.newInstance());
            }else if (getIntent().getIntExtra("where",0)== 5){
                ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(R.string.search);
                openFragment(search.newInstance());
            }else if (getIntent().getIntExtra("where",0) == 6) {
                ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(R.string.contactus);
                openFragment(contactus.newInstance());
            }
            else if (getIntent().getIntExtra("where",0) == 7) {
                ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(R.string.aboutus);
                openFragment(aboutus.newInstance());
            }
            else if (getIntent().getIntExtra("where",0) == 8) {
                ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(R.string.catigores);
                openFragment(Catigory_fragment.newInstance());
            }
            else if (getIntent().getIntExtra("where",0) == 9) {
                ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(R.string.nearststores);
                openFragment(nearestStores.newInstance());
            }
            else if (getIntent().getIntExtra("where",0) == 10) {
                ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(R.string.lastorder);
                openFragment(latestorder.newInstance());
            }
            else if (getIntent().getIntExtra("where", 0) == 6) {
                openFragment(Home.newInstance());
            } else if (getIntent().getIntExtra("where" , 0) == 100){
                //getcartlist();
                openFragment(Cart.newInstance());
            }



        }

        else {

            isMainFragment = true;
            openFragment(Home.newInstance());
            Navigation(0);
            ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(R.string.home);
        }
        (toolbar.findViewById(R.id.cart)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LogInManager.isUserLoggedIn(MainScreen.this, true, false)) {

                    toolbar.findViewById(R.id.cart).setVisibility(View.INVISIBLE);
                    cart_count.setVisibility(View.INVISIBLE);

                    openFragment(Cart.newInstance());
                    ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(R.string.cart);
                    isMainFragment = false;

                }
            }
        });
        findViewById(R.id.imageView1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openFragment(Settings.newInstance());
                isMainFragment = false;
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        log_in_out = findViewById(R.id.imageView3);
        log_in_out.setImageResource(R.drawable.ic_login);



    }

    public void filldata(final List<NavigationModel> data, final int where) {
        recyclerView.setAdapter(new RecyclerAdapter(data, R.layout.navigationrow, NavigationAdapter.class,
                new RecyclerAdapter.AdapterInterface<NavigationModel, NavigationAdapter>() {
                    @Override
                    public void fillData(final NavigationModel dataModel, final NavigationAdapter viewHolder) {
                        viewHolder.gettextView().setText(dataModel.getText());
                        viewHolder.getimgView().setImageDrawable(getResources().getDrawable(dataModel.getImage()));
                        if (viewHolder.getAdapterPosition() == where) {
                            i = where;
                            viewHolder.getmainView().setBackgroundDrawable(getResources().getDrawable(R.drawable.highlit));
                            viewHolder.gettextView().setTextColor(getResources().getColor(R.color.colorPrimary));
                            viewHolder.getimgView().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
                        }
                        viewHolder.getmainView().setOnClickListener(new View.OnClickListener() {


                            @Override
                            public void onClick(View view) {
                                if (i != -1) {
                                    RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(i);
                                    holder.itemView.findViewById(R.id.Navigationmain).setBackgroundColor(getResources().getColor(R.color.trnsparent));
                                    ((TextView) (holder.itemView.findViewById(R.id.Navigationtext))).setTextColor(getResources().getColor(R.color.colorWhite));
                                    ((ImageView) (holder.itemView.findViewById(R.id.icoon))).setColorFilter(getResources().getColor(R.color.colorWhite),
                                            PorterDuff.Mode.SRC_IN);
                                }
                                viewHolder.getmainView().setBackgroundDrawable(getResources().getDrawable(R.drawable.highlit));
                                viewHolder.gettextView().setTextColor(getResources().getColor(R.color.colorPrimary));
                                viewHolder.getimgView().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
                                ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(dataModel.getText());
                                switch (dataModel.getFragmentName()) {
                                    case R.layout.fragment_home:
                                        getcartlist();
                                        openFragment(Home.newInstance());
                                        isMainFragment = true;
                                        cart_back=2;
                                        break;
                                    case R.layout.fragment_cart:
                                        /*if (Cart.cart_count){
                                            getcartlist();
                                            openFragment(Cart.newInstance());
                                        }*/
                                      //  getcartlist();
                                        openFragment(Cart.newInstance());
                                        isMainFragment = false;
                                        break;

                                    case R.layout.fragment_catigory_fragment:
                                        getcartlist();
                                        openFragment(Catigory_fragment.newInstance());
                                        isMainFragment = false;
                                        cart_back=3;
                                        break;
                                    case R.layout.fragment_latestorder:
                                        getcartlist();
                                        openFragment(latestorder.newInstance());
                                        isMainFragment = false;
                                        cart_back=4;
                                        break;
                                    case R.layout.fragment_nearest_stores:
                                        startActivity(new Intent(MainScreen.this, TestMapsActivity.class));
//                                        getcartlist();
//                                        openFragment(nearestStores.newInstance());
//                                        isMainFragment = false;
//                                        cart_back=5;
                                        break;
                                    case R.layout.fragment_wishlist:
                                        getcartlist();
                                        if (LogInManager.isUserLoggedIn(MainScreen.this, true, false)) {
                                            openFragment(wishlist.newInstance());
                                            isMainFragment = false;
                                            cart_back=6;
                                        }

                                        break;
                                    case R.layout.fragment_search:
                                        getcartlist();
                                        openFragment(search.newInstance());
                                        isMainFragment = false;
                                        cart_back=7;
                                        break;
                                    case R.layout.fragment_instant_sizes:
//                                        getcartlist();
//                                        openFragment(search.newInstance());
//                                        isMainFragment = false;
//                                        cart_back=8;
//                                        AlertDialog.Builder builder;
//                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                            builder = new AlertDialog.Builder(MainScreen.this, android.R.style.Theme_Material_Dialog_Alert);
//                                        } else {
//                                            builder = new AlertDialog.Builder(MainScreen.this);
//                                        }
//                                        builder.setTitle(getString(R.string.mgas_service))
//                                                .setMessage(getString(R.string.call_us_txt))
//                                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                                    public void onClick(DialogInterface dialog, int which) {
//                                                        // continue with delete
//                                                        Intent intent = new Intent(Intent.ACTION_DIAL);
//                                                        intent.setData(Uri.parse("tel:+966531356800"));
//                                                        startActivity(intent);
//                                                    }
//                                                })
//                                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                                                    public void onClick(DialogInterface dialog, int which) {
//                                                        // do nothing
//                                                    }
//                                                })
//                                                .setIcon(android.R.drawable.ic_dialog_dialer)
//                                                .show();
//                                        break;
                                    case R.layout.fragment_contactus:
                                        getcartlist();
                                        openFragment(contactus.newInstance());
                                        isMainFragment = false;
                                        cart_back = 9;
                                        break;
                                    case R.layout.fragment_aboutus:
                                        getcartlist();
                                        openFragment(aboutus.newInstance());
                                        isMainFragment = false;
                                        cart_back = 10;
                                        break;
                                }
                                i = viewHolder.getPosition();
                                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                                drawer.closeDrawer(GravityCompat.START);
                            }
                        });
                    }
                }));
    }
    private void setActionBarTitle(int title) {
        getSupportActionBar().setTitle(getString(title));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "onStart");
//        Glide.with(MainScreen.this).load(R.drawable.icon).asBitmap().centerCrop()
        //      .into(new BitmapImageViewTarget(((ImageView) findViewById(R.id.imageView2))) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(MainScreen.this.getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                ((ImageView) findViewById(R.id.imageView2)).setImageDrawable(circularBitmapDrawable);
//            }
//        });

        if (LogInManager.isUserLoggedIn(this, false, false)) {

            log_in_out.setImageResource(R.drawable.ic_logout);
            log_in_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DrawerLayout drawer = findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    final CustomDialog customDialog = new CustomDialog(MainScreen.this, getString(R.string.msg_logout));
                    customDialog.show();
                    customDialog.setOkButtonClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            customDialog.dismiss();

                            APIManager.LogoutUser(MainScreen.this, LogInManager.getUserSession(MainScreen.this), new APIManager.ResponseListener<LoginModel>() {
                                @Override
                                public void done(LoginModel dataModel) {
                                    if (dataModel.getSuccess()) {

                                        SharedPreferences settings = MainScreen.this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                                        settings.edit().remove("userName").commit();
                                        navname.setText("No name defined");
                                        cart_count.setVisibility(View.GONE);


                                        LogInManager.setUserLoggedOut(MainScreen.this);
                                        log_in_out.setImageResource(R.drawable.ic_login);
                                        log_in_out.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                Intent mainIntent = new Intent(MainScreen.this, LoginRegister.class);
                                                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(mainIntent);
                                            }
                                        });
                                    }else{
                                   //     Toast.makeText(MainScreen.this, dataModel.getError() +" t", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void failed(boolean fromConnection) {

                                }
                            });

//                    Intent mainIntent = new Intent(MainScreen.this, Loginregister.class);
//                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(mainIntent);
                        }
                    });
                }
            });

        } else {
            ((ImageView) findViewById(R.id.imageView3)).setImageResource(R.drawable.ic_login);
            findViewById(R.id.imageView3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent mainIntent = new Intent(MainScreen.this, LoginRegister.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                }
            });
        }
    }

    private Class currentFragmentClass;

    private void openFragment(Fragment fragment) {
        if (currentFragmentClass != fragment.getClass()) {
            currentFragmentClass = fragment.getClass();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_container_layout, fragment)
                    .commit();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        back = 0;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!isMainFragment) {



            isMainFragment = true;


            openFragment(Home.newInstance());
            i = 0;
            Navigation(0);
            ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(R.string.home);
//            RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder)
//                    recyclerView.findViewHolderForAdapterPosition(0);
//            holder.itemView.findViewById(R.id.nav_view).setBackgroundDrawable(getResources().getDrawable(R.drawable.highlit));

        }
        else {
            if (back == 0) {
                Toast.makeText(MainScreen.this, R.string.leave, Toast.LENGTH_LONG).show();
                back++;
            } else if (back == 1) {
                moveTaskToBack(true);
            }
        }
    }

    public String setTextToolbar(String value){
        return value;
    }

    public void Navigation(int where) {
        list.clear();
        list.add(0, new NavigationModel(getString(R.string.home), R.layout.fragment_home, R.drawable.home));
        list.add(1, new NavigationModel(getString(R.string.catigores), R.layout.fragment_catigory_fragment, R.drawable.category));
        list.add(2, new NavigationModel(getString(R.string.nearststores), R.layout.fragment_nearest_stores, R.drawable.near));
        list.add(3, new NavigationModel(getString(R.string.lastorder), R.layout.fragment_latestorder, R.drawable.cart));
        list.add(4, new NavigationModel(getString(R.string.wishlist), R.layout.fragment_wishlist, R.drawable.fillheart));
        list.add(5, new NavigationModel(getString(R.string.search), R.layout.fragment_search, R.drawable.search));
//        list.add(6, new NavigationModel(getString(R.string.mgas_service), R.layout.fragment_instant_sizes, R.drawable.search));
        list.add(6, new NavigationModel(getString(R.string.contactus), R.layout.fragment_contactus, R.drawable.ic_sms));
        list.add(7, new NavigationModel(getString(R.string.aboutus), R.layout.fragment_aboutus, R.drawable.ic_information));

        filldata(list, where);
    }




    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    ////////////////////////////
    public  void getcartlist() {

        APIManager.GetCart(MainScreen.this, LogInManager.getUserSession(MainScreen.this), new APIManager.ResponseListener<AddToCartModel>() {
            @Override
            public void done(AddToCartModel dataModel) {
                if (dataModel.getSuccess()) {

                    if (dataModel.getData().getProductsModelList().size() != 0) {

                        total = Float.parseFloat(dataModel.getData().getTotal_raw());
                        items = Integer.parseInt(dataModel.getData().getTotal_product_count());
                        if (items != 0) {
                            //cart_count.setVisibility(View.VISIBLE);
                            System.out.println("failed failed"+items);
                            cart_count.setText(items+"");
                            toolbar.findViewById(R.id.cart).setVisibility(View.VISIBLE);
                            cart_count.setVisibility(View.VISIBLE);

                    } else {
                            cart_count.setVisibility(View.GONE);
                        }}}}

            @Override
            public void failed(boolean fromConnection) {

            }
        });
    }

}
