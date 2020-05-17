package com.example.lap_shop.shahad_3asal.activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.dialogs.AddReview;
import com.example.lap_shop.shahad_3asal.dialogs.DispalyViewPager;
import com.example.lap_shop.shahad_3asal.dialogs.ReviewViewDialog;
import com.example.lap_shop.shahad_3asal.models.AddToCartModel;
import com.example.lap_shop.shahad_3asal.models.ProductDetailsModel;
import com.example.lap_shop.shahad_3asal.models.WishlistModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.DataManager;
import com.example.lap_shop.shahad_3asal.tools.LanguageManager;
import com.example.lap_shop.shahad_3asal.tools.LogInManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductDetails extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {
    private SliderLayout mDemoSlider;
    private LoadingManager loadingManager;
    Toolbar toolbar;
    int rate = 0;
    public static String category;
    public static int flag_addedToCart=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageManager.setAppLanguage(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        setContentView(R.layout.activity_product_details);
        if (LanguageManager.getLanguage(getApplicationContext()).equals("ar")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("font.TTF")
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.empty));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        loadingManager = new LoadingManager(ProductDetails.this, findViewById(R.id.progressBar));
        loadingManager.showLoadingProgress();
        ((TextView) findViewById(R.id.description)).setMovementMethod(new ScrollingMovementMethod());
        ((TextView) findViewById(R.id.description)).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ((TextView) findViewById(R.id.description)).getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        if (getIntent().getExtras() != null) {
            filldata(getIntent().getStringExtra("Category_id"));
             category=getIntent().getStringExtra("Category_id");
        }

        findViewById(R.id.add_to_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LogInManager.isUserLoggedIn(ProductDetails.this, true, false)) {
                    addCart(getIntent().getStringExtra("Category_id"));
                    //////
                    }}
        });
        findViewById(R.id.add_to_wishlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LogInManager.isUserLoggedIn(ProductDetails.this, true, false)) {
                    addwishlist(Integer.parseInt(getIntent().getStringExtra("Category_id")));
                }}
        });
        findViewById(R.id.addreview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LogInManager.isUserLoggedIn(ProductDetails.this, true, false)) {
                    new AddReview(ProductDetails.this, Integer.parseInt(getIntent().getStringExtra("Category_id")), getIntent().getIntExtra("where", 0)).show();
                }}
        });
   flag_addedToCart=0;
    }


    private void filldata(String id) {
        APIManager.GetAllProductsDetails(ProductDetails.this, id, new APIManager.ResponseListener<ProductDetailsModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void done(final ProductDetailsModel dataModel) {
                if (dataModel.getSuccess()) {
                    loadingManager.hideLoadingProgress();

                    Log.e("description",dataModel.getData().getDescription());
                    ((TextView) findViewById(R.id.description)).setText(Html.fromHtml(Html.fromHtml(dataModel.getData().getDescription()).toString()));
                    ((TextView) (toolbar.findViewById(R.id.toolbar_title))).setText(Html.fromHtml(dataModel.getData().getName()));

                    List<String> data = new ArrayList<String>();
                    data = dataModel.getData().getImageModelList();
                    addslider(data);
                    String out = "";
                    for (int i = 0; i < dataModel.getData().getCategoryModelList().size(); i++) {
                        if (out.equals("")) {
                            out = dataModel.getData().getCategoryModelList().get(i).getName();
                        } else {
                            out += " , " + dataModel.getData().getCategoryModelList().get(i).getName();
                        }

                    }
                    rate = dataModel.getData().getRating();
                    ((TextView) findViewById(R.id.categories)).setText(out);
                    ((TextView) findViewById(R.id.name)).setText(Html.fromHtml(dataModel.getData().getName()));
                    ((TextView) findViewById(R.id.price)).setText(dataModel.getData().getPrice_formated());

                    ImageView[] rateImageViewsArray = {(ImageView) findViewById(R.id.star1), (ImageView) findViewById(R.id.star2),
                            (ImageView) findViewById(R.id.star3), (ImageView) findViewById(R.id.star4), (ImageView) findViewById(R.id.star5)};
                    for (int i = 0; i < rateImageViewsArray.length; i++) {

                        if (dataModel.getData().getRating() > i) {
                            rateImageViewsArray[i].setImageResource(R.drawable.starfill);
                        } else {
                            rateImageViewsArray[i].setImageResource(R.drawable.star);
                        }
                    }
                    findViewById(R.id.main).setVisibility(View.VISIBLE);
                    findViewById(R.id.viewreviews).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new ReviewViewDialog(ProductDetails.this, dataModel.getData().getReviewsModell().getReviewsdetailsModelList()).show();
                        }
                    });
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
            DefaultSliderView textSliderView = new DefaultSliderView(ProductDetails.this);
            // initialize a SliderLayout
            textSliderView
                    .description("")
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop)
                    .setOnSliderClickListener(this);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            mDemoSlider.addSlider(textSliderView);
        }
        // mDemoSlider.setDrawingCacheBackgroundColor(Color.RED);//Spiner Color
        mDemoSlider.setBackgroundColor(getResources().getColor(R.color.trnsparent));
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));

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

        if (getIntent().getExtras().size() != 2) {
            if (getIntent().getIntExtra("where", 0) == 2 && rate != 0) {
                //  ProductsList.updaterow(getIntent().getIntExtra("Position", 0), rate);
            } else if (rate != 0) {
                //   Home.updaterow(getIntent().getIntExtra("Position", 0), rate);
            }
        }

        Intent i=new Intent(ProductDetails.this,MainScreen.class);
        startActivity(i);
        finish();
    }

    public void addwishlist(int id) {
        APIManager.AddUserWishlist(ProductDetails.this, LogInManager.getUserSession(ProductDetails.this), id, new APIManager.ResponseListener<WishlistModel>() {
            @Override
            public void done(WishlistModel dataModel) {
                if (dataModel.getSuccess()) {
                    startActivityWithCategoryId(ProductDetails.this);
                }
            }

            @Override
            public void failed(boolean fromConnection) {

            }
        });
    }


    public void addCart(String id) {

        APIManager.AddCart(ProductDetails.this,LogInManager.getUserSession(ProductDetails.this), id, "1", new APIManager.ResponseListener<AddToCartModel>() {

            @Override
            public void done(AddToCartModel dataModel) {

                if (dataModel.getSuccess()) {

                 //   dataModel.getData().getProductsModelList().get(1).getOrignal_quantity();

                    Log.e("laptop", dataModel.getSuccess() + "");

                        startActivityWithcart(ProductDetails.this);

                        DataManager.setStringSetting(ProductDetails.this,
                                "Back_Category_id", getIntent().getStringExtra("Category_id"));
                        // DataManager.setIntSetting(ProductDetails.this,"fromProductDetails",1);
                   /* Intent i=new Intent(ProductDetails.this,MainScreen.class);
                    i.putExtra("where",100);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);*/

                        flag_addedToCart = 1;
                }else {
                    loadingManager.hideLoadingProgress();
                    Toast.makeText(ProductDetails.this, R.string.msg_not_add_cart, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void failed(boolean fromConnection) {
            }
        });
    }

    public static void startActivityWithCategoryId(Activity context) {
        Intent intent = new Intent(context, MainScreen.class);
        intent.putExtra("where", 4);
        intent.putExtra("Title", context.getString(R.string.wishlist));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void startActivityWithcart(Activity context) {
        Intent intent = new Intent(context, MainScreen.class);
        intent.putExtra("where", 100);
        intent.putExtra("Title", context.getString(R.string.cart));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        Toast.makeText(context, "startActivityWithcart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {
        //new DisplayImgDialog(ProductDetails.this).show();
        new DispalyViewPager(ProductDetails.this).show();
    }
}
