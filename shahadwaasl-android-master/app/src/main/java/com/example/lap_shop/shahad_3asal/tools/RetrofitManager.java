package com.example.lap_shop.shahad_3asal.tools;

import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Mohamed on 7/19/2016.
 */
public class RetrofitManager {



    public static Retrofit retrofit;
    private static Retrofit getRetrofit(String baseURL) {
      if(retrofit==null){
//            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
//                    .connectTimeout(60, TimeUnit.SECONDS)
//                    .readTimeout(60, TimeUnit.SECONDS)
//                    .writeTimeout(60, TimeUnit.SECONDS)
//                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                  //  .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
      }
        return retrofit;
    }

    public static APIManager.APIsInterface getAPIBuilder(String baseUrl){
        APIManager.APIsInterface apIsInterface = getRetrofit(baseUrl).create(APIManager.APIsInterface.class);
        return apIsInterface;
    }

    public static abstract class APICallBack<M> implements Callback<M>{
        @Override
        public void onResponse(Call<M> call, Response<M> response) {
            Log.e("StatusCode", response.code()+"");
            if(response.isSuccessful()){
                onSuccess(response.body());
            }else{
                int statusCode=response.code();
                ResponseBody errorResponseBody=response.errorBody();
                onFailed(false);
            }
        }
        @Override
        public void onFailure(Call<M> call, Throwable t) {
            try{Log.e("onFailure", t.getMessage());}catch(Exception e){}
            onFailed(true);
        }
        public abstract void onSuccess(M data);
        public abstract void onFailed(boolean fromConnection);
    }
}
