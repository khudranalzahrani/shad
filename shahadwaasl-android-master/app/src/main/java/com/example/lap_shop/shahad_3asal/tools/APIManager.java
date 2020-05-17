package com.example.lap_shop.shahad_3asal.tools;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;


import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.dialogs.DialogManager;
import com.example.lap_shop.shahad_3asal.models.AcceptOrderModel;
import com.example.lap_shop.shahad_3asal.models.AddReviewModel;
import com.example.lap_shop.shahad_3asal.models.AddToCartModel;
import com.example.lap_shop.shahad_3asal.models.CatigoryModel;
import com.example.lap_shop.shahad_3asal.models.CitesModel;
import com.example.lap_shop.shahad_3asal.models.ContactModel;
import com.example.lap_shop.shahad_3asal.models.CountriesModel;
import com.example.lap_shop.shahad_3asal.models.LatestProduct;
import com.example.lap_shop.shahad_3asal.models.LoginModel;
import com.example.lap_shop.shahad_3asal.models.ProductDetailsModel;
import com.example.lap_shop.shahad_3asal.models.ProductsModel;
import com.example.lap_shop.shahad_3asal.models.RegisteredAddresses;
import com.example.lap_shop.shahad_3asal.models.RegistrationModel;
import com.example.lap_shop.shahad_3asal.models.SMSModel;
import com.example.lap_shop.shahad_3asal.models.SessionModel;
import com.example.lap_shop.shahad_3asal.models.SliderModel;
import com.example.lap_shop.shahad_3asal.models.WishlistModel;
import com.example.lap_shop.shahad_3asal.models.latestordersModel;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Eman on 4/1/2017.
 */

public class APIManager {
      // public static final String URL_BASE = "http://newapi2.opencart-api.com:80/api/rest/";
    // public static final String URL_BASE = "http://41.41.62.2/opencart/api/";
//    public static final String URL_BASE = "http://shary.store/api/";
    public static final String URL_BASE = "http://206.81.15.156/api/";
    public static final String API_Session = "X-Oc-Session";
    public static final String API_LANGUAGE = "X-Oc-Merchant-Language";
    public static final String TAG_ID = "id";
    public static final String URL_SESSION = URL_BASE + "session";
    public static final String URL_Country= URL_BASE + "countries";
    public static final String URL_City= URL_BASE + "countries/{" + TAG_ID + "}";
    public static final String URL_CONTACT = URL_BASE + "contact";

    public static final String URL_SEND_SMS = URL_BASE+"sms/send";

    public static final String URL_slideshows = URL_BASE + "slideshows";
    public static final String URL_ADD_TO_CART= URL_BASE + "cart";
    public static final String URL_ADD_DELETE_TO_CART= URL_BASE + "cart/update";
    public static final String URL_LatestProduct = URL_BASE + "latest";
    public static final String URL_Catigory = URL_BASE + "categories/level/2";

    public static final String TAG_SEARCH = "search";
    public static final String URL_PRODUCTS = URL_BASE + "products/category/{" + TAG_ID + "}";
    public static final String URL_SEARCH_PRODUCTS = URL_BASE + "products/search/{" + TAG_SEARCH + "}";
    public static final String URL_PRODUCTS_DETAILS = URL_BASE + "products/{" + TAG_ID + "}";
    public static final String URL_ADD_REVIEW = URL_BASE + "products/{" + TAG_ID + "}/review";
    public static final String URL_ADD_WishList = URL_BASE + "wishlist/{" + TAG_ID + "}";
    public static final String URL_GET_WISHLIST = URL_BASE + "wishlist";

    public static final String URL_GET_REGISTERED_ADDRESSES = URL_BASE + "paymentaddress";
    public static final String URL_SIMPLE_CONFIRM = URL_BASE + "simpleconfirm";
    public static final String URL_ZONES=URL_BASE +"countries/{" + TAG_ID + "}";
    public static final String URL_FORGETPASSWORD = URL_BASE + "forgotten_phone";
    public static final String URL_FORGETPASSWORD_PHONE = URL_BASE + "sms/reset";
    public static final String URL_LATEST_ORDERS = URL_BASE +"orders/user/{"+TAG_ID + "}";



    public static final String URL_LOGIN = URL_BASE + "login";
    public static final String URL_LOGOUT = URL_BASE + "logout";



    public static final String URL_Registration = URL_BASE + "register";

    public static final String TAG_Product1d = "id";



    public interface APIsInterface {

        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @GET(URL_SESSION)
        Call<SessionModel> getsession(@Header(API_LANGUAGE) String Language);

        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @GET(URL_Country)
        Call<CountriesModel> getcountry(@Header(API_LANGUAGE) String Language);

        @Headers({"X-Oc-Merchant-Id: 123","X-Oc-Currency: SAR"})
        @GET(URL_City)
        Call<CitesModel> getcites(@Path(TAG_Product1d) String id, @Header(API_LANGUAGE) String Language);

        @Headers({"X-Oc-Merchant-Id: 123","X-Oc-Currency: SAR"})
        @GET(URL_ZONES)
        Call<CitesModel> getZones(@Path(TAG_Product1d) String id, @Header(API_LANGUAGE) String Language);




        @Headers({"X-Oc-Merchant-Id: 123","X-Oc-Currency: SAR"})
        @GET(URL_PRODUCTS)
        Call<ProductsModel> getProducts(@Path(TAG_Product1d) String id,@Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123","X-Oc-Currency: SAR"})
        @GET(URL_SEARCH_PRODUCTS)
        Call<ProductsModel> getProductssearch(@Path(TAG_SEARCH) String search,@Header(API_LANGUAGE) String Language);

        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @GET(URL_PRODUCTS_DETAILS)
        Call<ProductDetailsModel> getProductsdetails(@Path(TAG_Product1d) String id,@Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @GET(URL_slideshows)
        Call<SliderModel> getSlider(@Header(API_LANGUAGE) String Language);

        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @GET(URL_LatestProduct)
        Call<LatestProduct> getProduct(@Header(API_LANGUAGE) String Language);

        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR","level: 2"})
        @GET(URL_Catigory)
        Call<CatigoryModel> getCatigory(@Header(API_LANGUAGE) String Language);



//
//        @GET(URL_PLAYGROUNDS)
//        Call<PlaygroundModel> GetPlaygrounds(@Query(TAG_api_token) String api_token);
//
//        @GET(URL_PG_INFO)
//        Call<PlaygroundInfoModel> GetPlaygroundsInfo(@Path(TAG_ID) String id, @Query(TAG_api_token) String api_token);
//


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @POST(URL_Registration)
        Call<RegistrationModel> RegisterUser(@Body RegistrationModel.DataModel body, @Header(API_Session) String sassion,@Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @POST(URL_FORGETPASSWORD_PHONE)
        Call<RegistrationModel> forget_password(@Body RegistrationModel.DataModel body,@Header(API_Session) String session,@Header(API_LANGUAGE) String Language);

        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @POST(URL_LOGIN)
        Call<LoginModel> LoginUser(@Body LoginModel body, @Header(API_Session) String sassion,@Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @POST(URL_CONTACT)
        Call<ContactModel> ContactUser(@Body ContactModel body, @Header(API_Session) String sassion,@Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @POST(URL_LOGOUT)
        Call<LoginModel> LogoutUser(@Header(API_Session) String sassion,@Header(API_LANGUAGE) String Language);

        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @GET(URL_GET_WISHLIST)
        Call<WishlistModel> GetWishList(@Header(API_Session) String sassion,@Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @POST(URL_ADD_WishList)
        Call<WishlistModel> AddWishList(@Header(API_Session) String sassion, @Path(TAG_Product1d) int id,@Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @DELETE(URL_ADD_WishList)
        Call<WishlistModel> DeletFromWishList(@Header(API_Session) String sassion, @Path(TAG_Product1d) int id,@Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @POST(URL_ADD_REVIEW)
        Call<AddReviewModel> AddReview(@Body AddReviewModel body, @Path(TAG_Product1d) int id,@Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @POST(URL_ADD_TO_CART)
        Call<AddToCartModel> AddToCart(@Body AddToCartModel body, @Header(API_Session) String sassion,@Header(API_LANGUAGE) String Language);

        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @HTTP(method = "DELETE", path = URL_ADD_TO_CART, hasBody = true)
        Call<AddToCartModel> DeletCart(@Body AddToCartModel body, @Header(API_Session) String sassion,@Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @HTTP(method = "PUT", path = URL_ADD_DELETE_TO_CART, hasBody = true)
        Call<AddToCartModel> UpdateCart(@Body AddToCartModel body, @Header(API_Session) String sassion,@Header(API_LANGUAGE) String Language);

        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @GET(URL_ADD_TO_CART)
        Call<AddToCartModel> GetCart(@Header(API_Session) String sassion,@Header(API_LANGUAGE) String Language);


        @POST(URL_SEND_SMS)
        Call<SMSModel> SendSMS(@Body SMSModel body);


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @GET(URL_GET_REGISTERED_ADDRESSES)
        Call<RegisteredAddresses> GetRegAddresses(@Header(API_Session) String session, @Header(API_LANGUAGE) String Language);
////////////////////////////////////////////////////////////////////////////

        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @GET(URL_LATEST_ORDERS)
        Call<latestordersModel> GetLatestOrder(@Header(API_Session) String session,@Path(TAG_Product1d) String id, @Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123"
               , "X-Oc-Currency: SAR"
        })
        @POST(URL_GET_REGISTERED_ADDRESSES)
        Call<RegisteredAddresses> PostRegAddresses(@Header(API_Session) String session,@Body RegisteredAddresses.DataModel.AddressesModel body,
                                                   @Header(API_LANGUAGE) String Language);


        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @POST(URL_SIMPLE_CONFIRM)
        Call<AcceptOrderModel> getSimpleConfirm(@Header(API_Session) String session, @Header(API_LANGUAGE) String Language);

        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @HTTP(method = "PUT" , path = URL_SIMPLE_CONFIRM,hasBody = true)
        Call<AcceptOrderModel> put_SimpleConfirm( @Header(API_Session) String sassion,@Header(API_LANGUAGE) String Language);


/*
        @Headers({"X-Oc-Merchant-Id: 123",
                "X-Oc-Currency: SAR"})
        @HTTP(method = "DELETE", path = URL_EMPITY_CART, hasBody = true)
        Call<AddToCartModel> empityCart(@Body AddToCartModel body,@Header(API_Session) String sassion,@Header(API_LANGUAGE) String Language);

*/

    }


    public static void AddSession(Context context, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).getsession(language(context));
        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }


    public static void GetCountry(Context context, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).getcountry(language(context));
        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }



    public static void Getcities(Context context, String id, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).getcites(id,language(context));
        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void GetZones(Context context, String id, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).getZones(id,language(context));
        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }


    public static void GetAllProducts(Context context, String id, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).getProducts(id,language(context));
        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void GetSearchProducts(Context context, String id, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).getProductssearch(id,language(context));
        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void GetAllProductsDetails(Context context, String id, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).getProductsdetails(id,language(context));
        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void GetAllSlider(Context context, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).getSlider(language(context));
        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void GetLatestProduct(Context context, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).getProduct(language(context));
        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }


    public static void GetALLCatigory(Context context, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).getCatigory(language(context));
        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void RegisterUser(Context context, String mobile, String pass, String address,String address2,
                                    String city, String country, String email, String fax, String firstname, String lastname,String postcode
            , String zone, String confirm, String agree, String session, final ResponseListener responseListener)
    {
        RegistrationModel.DataModel x = new RegistrationModel.DataModel(mobile, pass, address,address2, city, country, email, fax, firstname,
                lastname,postcode, zone, confirm, agree);
        Log.e("Response", x + "");
        Call call = RetrofitManager.getAPIBuilder(URL_BASE).RegisterUser(x, session,language(context));
        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void forgetPassword(Context context,String email,String new_password,String session,final ResponseListener responseListener){
        RegistrationModel.DataModel x = new RegistrationModel.DataModel (email, new_password);
        Call call = RetrofitManager.getAPIBuilder(URL_BASE).forget_password(x, session,language(context));
        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void LoginUser(Context context, String email, String password, String session, final ResponseListener responseListener) {
        LoginModel x = new LoginModel(email, password);
        Call call = RetrofitManager.getAPIBuilder(URL_BASE).LoginUser(x, session,language(context));
        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }



    public static void SendContact(Context context, String name, String email,String comment, String session, final ResponseListener responseListener) {

        ContactModel x= new ContactModel(email,name,comment);

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).ContactUser(x, session,language(context));
        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }

    public static void AddReviews(Context context, String name, String text, String rating, int id, final ResponseListener responseListener) {
        Log.e("onSuccess", name + text + rating);

        AddReviewModel x = new AddReviewModel(name, text, rating);
        Call call = RetrofitManager.getAPIBuilder(URL_BASE).AddReview(x, id,language(context));
        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }

    public static void AddCart(Context context, String session,String product_id
            , String quantity, final ResponseListener responseListener) {

        AddToCartModel x = new AddToCartModel(product_id, quantity,1);
        Call call = RetrofitManager.getAPIBuilder(URL_BASE).AddToCart(x,session,language(context));

        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }

    public static void Updatecart(Context context, String product_id, String quantity,String session, final ResponseListener responseListener) {

        AddToCartModel x = new AddToCartModel(product_id, quantity,2);
        Call call = RetrofitManager.getAPIBuilder(URL_BASE).UpdateCart(x, session,language(context));

        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }

    public static void DeletCart(Context context, int product_id,String session, final ResponseListener responseListener) {

        AddToCartModel x = new AddToCartModel(product_id);
        Call call = RetrofitManager.getAPIBuilder(URL_BASE).DeletCart(x, session,language(context));

        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }

    public static void SendSMS(Context context,String phone,final ResponseListener responseListener) {
        SMSModel x = new SMSModel(phone);
        Call call = RetrofitManager.getAPIBuilder(URL_BASE).SendSMS(x);

        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }

    public static void GetCart(Context context,String session, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).GetCart( session,language(context));

        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }

    public static void LogoutUser(Context context, String session, final ResponseListener responseListener) {


        Call call = RetrofitManager.getAPIBuilder(URL_BASE).LogoutUser(session,language(context));

        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }
    public static void GetUserWishlist(Context context, String session, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).GetWishList(session,language(context));

        boolean showLoadingDialog = false;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }

    public static void AddUserWishlist(Context context, String session,int id, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).AddWishList(session,id,language(context));

        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }


    public static void DeleteUserWishlist(Context context, String session,int id, final ResponseListener responseListener) {

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).DeletFromWishList(session,id,language(context));

        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }


    public static void getRegisteredAddresses(Context context,String session,final  ResponseListener responseListener){

        Call call=RetrofitManager.getAPIBuilder(URL_BASE).GetRegAddresses(session,language(context));
        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void postNewAddresses(Context context,String session,String fname,String lname,String address_1,String address_2,String postcode,String city
            ,String country_id,String company_id,String company,String tax_id,String zone_id,final  ResponseListener responseListener){

        RegisteredAddresses.DataModel.AddressesModel x =
                new RegisteredAddresses.DataModel.AddressesModel(fname, lname, address_1,address_2, postcode, city, country_id,company_id,company,tax_id,zone_id);

        Call call=RetrofitManager.getAPIBuilder(URL_BASE).PostRegAddresses(session,x,language(context));
        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }



    public static  void get_SimpleConfirm(Context context,String session,final ResponseListener responseListener){
        Call call=RetrofitManager.getAPIBuilder(URL_BASE).getSimpleConfirm(session,language(context));
        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }


 /*   public static void empity_Cart(Context context,int product_id,String session,final ResponseListener responseListener){

        AddToCartModel x = new AddToCartModel(product_id);
        Call call = RetrofitManager.getAPIBuilder(URL_BASE).DeletCart(x, session,language(context));
        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }
*/

    public static void putSimpleConfirm(Context context, String session, final ResponseListener responseListener){

        Call call = RetrofitManager.getAPIBuilder(URL_BASE).put_SimpleConfirm( session,language(context));
        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);
    }

    public static void getLatestOrders(Context context, String id, String session, final ResponseListener responseListener){
        Call call = RetrofitManager.getAPIBuilder(URL_BASE).GetLatestOrder( session,id,language(context));
        boolean showLoadingDialog = true;
        performNormalRequest(context, call, showLoadingDialog, responseListener);

    }


//
//    public static void Reservation(Context context,int id,String time,int player_id, final ResponseListener responseListener) {
//        Call call=RetrofitManager.getAPIBuilder(URL_BASE).Reservation( id,time,player_id);
//        boolean showLoadingDialog=true;
//        performNormalRequest(context, call, showLoadingDialog, responseListener);
//    }
//
//    public static void LogInApp(Context context,String mobile,String password, final ResponseListener responseListener) {
//        Call call=RetrofitManager.getAPIBuilder(URL_BASE).LoginUser( mobile,password,API_TOKEN);
//        boolean showLoadingDialog=true;
//        performNormalRequest(context, call, showLoadingDialog, responseListener);
//    }
//    public static void GetPlaygroundList(Context context, final ResponseListener responseListener) {
//        Call call=RetrofitManager.getAPIBuilder(URL_BASE).GetPlaygrounds(API_TOKEN);
//        boolean showLoadingDialog=true;
//        performNormalRequest(context, call, showLoadingDialog, responseListener);
//    }
//
//
//    public static void GetPlaygroundInfo(Context context,String ID, final ResponseListener responseListener) {
//        Call call=RetrofitManager.getAPIBuilder(URL_BASE).GetPlaygroundsInfo(ID,API_TOKEN);
//        boolean showLoadingDialog=true;
//        performNormalRequest(context, call, showLoadingDialog, responseListener);
//    }


    private static ProgressDialog showLoadingDialog(Context context, final Call call) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(context.getString(R.string.msg_loading));
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                try {
                    call.cancel();
                } catch (Exception e) {
                }
            }
        });
        return progressDialog;
    }

    private static void closeLoadingDialog(ProgressDialog progressDialog) {
        try {
            progressDialog.dismiss();
        } catch (Exception e) {
        }
    }

    public interface ResponseListener<M> {
        void done(M dataModel);

        void failed(boolean fromConnection);
    }

    private static void performNormalRequest(final Context context, final Call call, final boolean showLoading, final ResponseListener responseListener) {
        Log.e("URL", call.request().url().toString());
        if (call.request().body() != null)
            Log.e("onSuccess", new Gson().toJson(call.request().body()));

        ProgressDialog progressDialog = null;
        if (showLoading) {
            progressDialog = showLoadingDialog(context, call);
        }
        final ProgressDialog tempProgressDialog = progressDialog;
        call.enqueue(new RetrofitManager.APICallBack() {
            @Override
            public void onSuccess(Object data) {
                String response = new Gson().toJson(data);
                Log.e("Result", response);
                if (showLoading) closeLoadingDialog(tempProgressDialog);
                try {
//                    JSONObject responseJsonObject=new JSONObject(response);
//                    int isResultHasData=responseJsonObject.getInt(TAG_IS_RESULT_HAS_DATA);
//                    if(isResultHasData>0){
                    responseListener.done(data);
//                    }else{
//                        Toast.makeText(context, context.getString(R.string.msg_couldnt_perform_operation), Toast.LENGTH_SHORT).show();
//                    }
                } catch (Exception e1) {
                    try {
                        responseListener.done(data);
                    } catch (Exception e) {
                    }
                }
            }

            @Override
            public void onFailed(boolean fromConnection) {
                Log.e("onFailed", fromConnection + "");
                if (showLoading) closeLoadingDialog(tempProgressDialog);
                String msg;
                if (fromConnection) msg = context.getString(R.string.msg_check_connection);
                else msg = context.getString(R.string.msg_check_server);
                if (context instanceof Activity) {
                    if (context != null) {
                        DialogManager.showConfirmDialog((Activity) context, msg, context.getString(R.string.label_retry), context.getString(R.string.label_cancel), new DialogManager.Confirm() {
                            @Override
                            public void ok() {
                                performNormalRequest(context, call.clone(), showLoading, responseListener);
                            }
                        }, new DialogManager.Confirm() {
                            @Override
                            public void ok() {
//                                if(call.request().url().toString().equals(URL_BASE+URL_GET_SALON_DATA) && context instanceof SalonDetailsActivity){
//                                    ((Activity)context).finish();
//                                }
                            }
                        });
                    }
                } else DialogManager.showToast(context, msg);
                responseListener.failed(fromConnection);
            }
        });
    }

public static String language(Context context){
    if(LanguageManager.getUsedLanguage(context).equals(LanguageManager.LANGUAGE_ARABIC))return "ar";
    else return "en-gb";

}
}
