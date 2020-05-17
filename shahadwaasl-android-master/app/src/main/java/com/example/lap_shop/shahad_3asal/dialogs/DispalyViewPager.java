package com.example.lap_shop.shahad_3asal.dialogs;

/**
 * Created by Ahmed Salamony on 6/21/2017.
 */
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.activites.ProductDetails;
import com.example.lap_shop.shahad_3asal.adapters.SlidingImage_Adapter;
import com.example.lap_shop.shahad_3asal.models.ProductDetailsModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;


public class DispalyViewPager extends Dialog {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    List<String> data;
   // private static final Integer[] IMAGES= {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.five};
    private List<String> ImagesArray = new ArrayList<String>();
    LoadingManager loadingManager;
    Activity activity;

    public DispalyViewPager(Activity activity) {
        super(activity);
        setContentView(R.layout.displayviewpager);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.activity = activity;
        loadingManager = new LoadingManager(activity, findViewById(R.id.progressBar));
        loadingManager.showLoadingProgress();
        filldata(ProductDetails.category);

       // ImageView img=(ImageView)findViewById(R.id.close_pager);
        /*img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
*/
    }

    private void filldata(String id) {
        APIManager.GetAllProductsDetails(activity, id, new APIManager.ResponseListener<ProductDetailsModel>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void done(final ProductDetailsModel dataModel) {
                if (dataModel.getSuccess()) {
                    loadingManager.hideLoadingProgress();

                    data  = new ArrayList<String>();
                    data = dataModel.getData().getImageModelList();

                    init(data);

                }
            }

            @Override
            public void failed(boolean fromConnection) {

            }

        });
    }

    private void init(final List<String> data) {


        for(int i=0;i<data.size();i++) {
            ImagesArray.add(data.get(i));
            Log.e("pager", ImagesArray.get(i) + "");
        }
        mPager = (ViewPager) findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(activity,ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = activity.getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =data.size();

        // Auto start of viewpager
       /* final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);*/

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
}
