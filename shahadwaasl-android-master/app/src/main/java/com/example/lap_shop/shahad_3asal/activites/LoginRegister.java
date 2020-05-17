package com.example.lap_shop.shahad_3asal.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.adapters.SpinnerAdapter;
import com.example.lap_shop.shahad_3asal.models.CitesModel;
import com.example.lap_shop.shahad_3asal.models.CountriesModel;
import com.example.lap_shop.shahad_3asal.models.LoginModel;
import com.example.lap_shop.shahad_3asal.models.RegistrationModel;
import com.example.lap_shop.shahad_3asal.models.SMSModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.DataManager;
import com.example.lap_shop.shahad_3asal.tools.FacebookLoginManager;
import com.example.lap_shop.shahad_3asal.tools.JParser;
import com.example.lap_shop.shahad_3asal.tools.LanguageManager;
import com.example.lap_shop.shahad_3asal.tools.LogInManager;
import com.example.lap_shop.shahad_3asal.tools.ValidationManager;
import com.google.gson.Gson;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginRegister extends AppCompatActivity {
    RelativeLayout view;
    int MoDE = 0;
    int LOGINMODE = 1;
    int REGISTERMODE = 2;
    int REGISTERMODE2 = 4;
    int REGISTERMODECODE = 6;
    int FORGETPASSWORDNEW = 7;
    int LOGINREGISTER = 3;
    int FORGETPASSWORD = 5;
    private Spinner country, city;
    List CountryName, CountryId, CityName, CityId;
    View login, register, registerCode, register2, loginregister, ForgetPassword, F_fram_forgetpassword_code;
    Animation up, dowm;
    EditText nameregister, passregister, phoneregister, emailregister, loginname, loginpass, lastname, address1, address2, zone, postcode, f_email, verification_code, forget_new_password_code;
    SpinnerAdapter citySpinnerAdapter, countrySpinnerAdapter;
    private FacebookLoginManager facebookLoginManager;
    TextView tv_forgetpass, btnPasswordRecovery;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "userName";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public static String customerID;
    ImageView crosslogin;
    int typeOfScreen = 0;
    String phonePass = "";

    JParser jParser = new JParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LanguageManager.setAppLanguage(getApplicationContext());
        facebookLoginManager = new FacebookLoginManager(getApplicationContext()).init();
        super.onCreate(savedInstanceState);
        if (LanguageManager.getLanguage(getApplicationContext()).equals("ar")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("font.TTF")
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
        setContentView(R.layout.activity_login_register);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        nameregister = findViewById(R.id.registername);
        passregister = findViewById(R.id.registerpass);
        phoneregister = findViewById(R.id.registerphone);
        verification_code = findViewById(R.id.verification_code);
        forget_new_password_code = findViewById(R.id.forget_new_password_code);
        emailregister = findViewById(R.id.registeremail);
        loginname = findViewById(R.id.loginname);
        loginpass = findViewById(R.id.loginpass);
//        lastname = findViewById(R.id.lastname);
//        address1 = findViewById(R.id.address1);
//        address2 = findViewById(R.id.address2);
//        postcode = findViewById(R.id.postcode);
        zone = findViewById(R.id.zone);
        f_email = findViewById(R.id.F_loginmail);
        crosslogin = findViewById(R.id.crosslogin);

        view = findViewById(R.id.view);
        country = findViewById(R.id.country);
        city = findViewById(R.id.city);

        tv_forgetpass = findViewById(R.id.tv_forgetpass);
        btnPasswordRecovery = findViewById(R.id.F_passwordrecovery);

        btnPasswordRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidationManager.validateEmptyFields(LoginRegister.this, f_email) &&
                        ValidationManager.validatesmallFields(LoginRegister.this, f_email)
                ) {
                    typeOfScreen = 2;
                    Log.e("f_email", " onClick: " + f_email.getText().toString());
                    new SendSMS().execute(f_email.getText().toString()); //TODO
                }
            }
        });

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    GetCity();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        countrySpinnerAdapter = new SpinnerAdapter(this, country, R.layout.spinner_item_simple_header, R.string.country);
        citySpinnerAdapter = new SpinnerAdapter(this, city, R.layout.spinner_item_simple_header, R.string.city);

        register = findViewById(R.id.registerview);
        registerCode = findViewById(R.id.registerviewcode);
        register2 = findViewById(R.id.registerview2);
        loginregister = findViewById(R.id.LoginRegister);
        login = findViewById(R.id.loginview);
        ForgetPassword = findViewById(R.id.F_fram_forgetpassword);
        F_fram_forgetpassword_code = findViewById(R.id.F_fram_forgetpassword_code);
        final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_down);
        findViewById(R.id.view).clearAnimation();
        findViewById(R.id.view).startAnimation(animation);
        MoDE = LOGINREGISTER;

        dowm = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_out_down);
        up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_down_no_offest);

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                animation(loginregister, register, dowm, up);
                MoDE = REGISTERMODE;

            }
        });


        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                animation(loginregister, login, dowm, up);
                MoDE = LOGINMODE;

            }
        });


        findViewById(R.id.F_crosslogin1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation(ForgetPassword, login, dowm, up);
                MoDE = LOGINMODE;
            }
        });

        tv_forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation(login, ForgetPassword, dowm, up);
                MoDE = FORGETPASSWORD;
            }
        });

        crosslogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation(login, loginregister, dowm, up);
                MoDE = LOGINREGISTER;

            }
        });

        findViewById(R.id.crossregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation(register, loginregister, dowm, up);
                MoDE = LOGINREGISTER;
            }
        });

        findViewById(R.id.crossregiste_forget_rcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation(F_fram_forgetpassword_code, ForgetPassword, dowm, up);
                MoDE = FORGETPASSWORD;
            }
        });

        findViewById(R.id.crossregistercode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(typeOfScreen == 1) {
                    animation(registerCode, register, dowm, up);
                    MoDE = REGISTERMODE;
                } else {
                    animation(registerCode, ForgetPassword, dowm, up);
                    MoDE = FORGETPASSWORD;
                }
            }
        });

        findViewById(R.id.crossregister2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation(register2, register, dowm, up);
                MoDE = REGISTERMODE;
            }
        });

        findViewById(R.id.finalregistration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ValidationManager.validateEmptyFields(LoginRegister.this, zone)//lastname, address1, address2
                        && ValidationManager.isemptySpinner(LoginRegister.this, city, country)
                        && ValidationManager.validatesmallFields(LoginRegister.this, zone)//, lastname, address1, address2
//                        && ValidationManager.isValidPostCode(LoginRegister.this, postcode)
                ) {
                    registration();
                }

            }
        });
        findViewById(R.id.Registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidationManager.isValidName(LoginRegister.this, nameregister)
//                        && ValidationManager.isAlpha(LoginRegister.this, nameregister, nameregister.getText().toString())
//                        && ValidationManager.validateEmptyFields(LoginRegister.this, phoneregister, emailregister)
//                        && ValidationManager.isValidEmail(LoginRegister.this, emailregister)
                        && ValidationManager.isValidRegisterPass(LoginRegister.this, passregister) &&
                        ValidationManager.validatesmallFields(LoginRegister.this, phoneregister)//, emailregister
                ) {
                    typeOfScreen = 1;
                    new SendSMS().execute(phoneregister.getText().toString()); //TODO
                }
            }
        });

        findViewById(R.id.RegistrationCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidationManager.isValidVerificationCode(LoginRegister.this, verification_code)) {
                    if (DataManager.getStringSetting(LoginRegister.this, "verify_code", "0000").equalsIgnoreCase(verification_code.getText().toString())) {
                        if (typeOfScreen == 1) {
                            animation(registerCode, register2, dowm, up);
                            MoDE = REGISTERMODE2;
                            GetCountry();
                        } else if (typeOfScreen == 2) {
                            animation(registerCode, F_fram_forgetpassword_code, dowm, up);
                            MoDE = FORGETPASSWORDNEW;
                        }
                    } else {
                        Toast.makeText(LoginRegister.this, "Wrong verification code", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        findViewById(R.id.forgotPasswordButtonCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidationManager.isValidPassWord(LoginRegister.this, forget_new_password_code)) { // TODO
                    new UpdatePassword().execute(phonePass,forget_new_password_code.getText().toString()); //TODO
//                    APIManager.forgetPassword(LoginRegister.this, phonePass, forget_new_password_code.getText().toString(), LogInManager.getUserSession(LoginRegister.this)
//                            , new APIManager.ResponseListener<RegistrationModel>() {
//                                @Override
//                                public void done(RegistrationModel dataModel) {
//
//                                }
//
//                                @Override
//                                public void failed(boolean fromConnection) {
//
//                                }
//                            });
                }
            }
        });

        findViewById(R.id.loginbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidationManager.validateEmptyFields(LoginRegister.this, loginname) && ValidationManager.isValidLoginPass(LoginRegister.this, loginpass)) {
                    APIManager.LoginUser(LoginRegister.this, loginname.getText().toString(), loginpass.getText().toString(),
                            LogInManager.getUserSession(LoginRegister.this), new APIManager.ResponseListener<LoginModel>() {
                                @Override
                                public void done(LoginModel dataModel) {

                                    if (dataModel.getSuccess()) {
                                        LogInManager.setUserLoggedIn(LoginRegister.this, true,
                                                (dataModel.getData().getCustomer_id()) + "", dataModel.getData().getFirstname(), dataModel.getData().getTelephone(),
                                                "");


                                        Log.e("customerID", dataModel.getData().getCustomer_id() + " ");
                                        customerID = dataModel.getData().getCustomer_id();
                                        DataManager.setStringSetting(LoginRegister.this, "customerID", dataModel.getData().getCustomer_id());


                                        editor.putString(Name, dataModel.getData().getFirstname());
                                        editor.commit();

                                        Intent intent = new Intent(LoginRegister.this, MainScreen.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginRegister.this, R.string.msg_invalid_login, Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void failed(boolean fromConnection) {

                                }
                            });


                }

            }
        });

        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginRegister.this, MainScreen.class);
                editor.putString(Name, getString(R.string.visitor));
                editor.commit();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        });


        facebookLoginManager.setCustomButton(findViewById(R.id.facebooklogin));
        facebookLoginManager.setup(findViewById(R.id.login_facebook_button), new FacebookLoginManager.FacebookResponseListener() {


            @Override
            public void onSuccess(final Bundle bundle) {
                // facebook(bundle.getString("idFacebook"), bundle.getString("email"), bundle.getString("profile_pic"), bundle.getString("first_name") + bundle.getString("last_name"), 1, bundle.getString("email"));
                String facebook_id = bundle.getString("idFacebook");
                String name_U = bundle.getString("first_name") + " " + bundle.getString("last_name");
                String email = bundle.getString("email");
                String profile = bundle.getString("profile_pic");
                LogInManager.setUserLoggedIn(LoginRegister.this, true, facebook_id, name_U, "", "");


                editor.putString(Name, name_U);
                editor.commit();


                Intent intent = new Intent(LoginRegister.this, MainScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }

            @Override
            public void onFailed() {
                Log.e("Name", "failed");
            }
        });
        //////////////////////////////////////////////////////////////////////////////////
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.lap_shop.shary", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        view = (RelativeLayout) this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookLoginManager.onActivityResult(requestCode, resultCode, data);
    }

    public void animation(final View from, final View to, final Animation animation, final Animation animation1) {
        findViewById(R.id.view).clearAnimation();
        findViewById(R.id.view).startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                from.setVisibility(View.GONE);
                to.setVisibility(View.VISIBLE);
                findViewById(R.id.view).clearAnimation();
                findViewById(R.id.view).startAnimation(animation1);


            }
        }, 500);

    }


    @Override
    public void onBackPressed() {

        if (MoDE == REGISTERMODE) {
            animation(register, loginregister, dowm, up);
            MoDE = LOGINREGISTER;

        } else if (MoDE == LOGINMODE) {
            animation(login, loginregister, dowm, up);
            MoDE = LOGINREGISTER;
        } else if (MoDE == REGISTERMODE2) {
            animation(register2, register, dowm, up);
            MoDE = REGISTERMODE;
        } else if (MoDE == FORGETPASSWORD) {
            animation(ForgetPassword, login, dowm, up);
            MoDE = LOGINMODE;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void registration() {

        APIManager.RegisterUser(LoginRegister.this,
                phoneregister.getText() + "",
                passregister.getText() + "",
                "none none",//address1.getText().toString(),
                "none none",//address2.getText().toString(),
                CityName.get(city.getSelectedItemPosition() - 1).toString(),
                CountryId.get(country.getSelectedItemPosition() - 1).toString(),
                emailregister.getText().toString().isEmpty() ? "example@example.com" : emailregister.getText() + "",
                "t",
                nameregister.getText() + "",
                "none none",//lastname.getText().toString(),
                "181614",//postcode.getText().toString(),
                CityId.get(city.getSelectedItemPosition() - 1).toString(),
                passregister.getText() + "",
                "1",
                LogInManager.getUserSession(LoginRegister.this),

                new APIManager.ResponseListener<RegistrationModel>() {
                    @Override
                    public void done(RegistrationModel dataModel) {
                        if (dataModel.isSuccess()) {
                            LogInManager.setUserLoggedIn(LoginRegister.this, true,
                                    (dataModel.getData().getCustomer_id()) + "", dataModel.getData().getFirstname(), dataModel.getData().getTelephone(),
                                    "");

                            Intent intent = new Intent(LoginRegister.this, MainScreen.class);

                            intent.putExtra("userName", dataModel.getData().getFirstname());
                            Toast.makeText(LoginRegister.this, R.string.success_register, Toast.LENGTH_SHORT).show();
                            intent.putExtra("where", 66);

                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);
                        } else {
                            //     Toast.makeText(LoginRegister.this, dataModel.getError()+ " ", Toast.LENGTH_SHORT).show();

                            //   Toast.makeText(LoginRegister.this, getString(R.string.msg_enter_valid_email_or_phone), Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void failed(boolean fromConnection) {

                    }
                });
    }

    /**
     * Background Async Task to Load all product by making HTTP Request
     */
    class SendSMSS extends AsyncTask<String, String, String> {
        String phone = "";

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * getting All products from url
         */
        protected String doInBackground(String... args) {

            phone = args[0];
            Log.e("phone", "doInBackground: " + phone);

            phone = "249906844174";
            phonePass = phone;

//            Random rnd = new Random();
//            Double next = rnd.nextDouble() * 10000;
//            while (next < 1000) {
//                next *= 10;
//            }
//            String code = next.intValue()+"";


//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (typeOfScreen == 1) {
//                        phone = phoneregister.getText().toString();
//                    } else if (typeOfScreen == 2){
//                        phone = f_email.getText().toString();
//                    }
//                }
//            });

            // Building Parameters
            List<NameValuePair> update_params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject update_json = jParser.getJSONFromUrl("http://46.101.232.11:81/muzdan/api/sms/send?phone=" + phone, update_params);

            try {
                if (update_json.getInt("success") == 1) {

                    String code = update_json.getString("vcode");
                    DataManager.setStringSetting(LoginRegister.this, "verify_code", code);
                    Log.e("SendSMS ", "SendSMS: code " + code);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginRegister.this, "Verification code sent to you via SMS", Toast.LENGTH_SHORT).show();

                            if (typeOfScreen == 1) {
                                animation(register, registerCode, dowm, up);
                                MoDE = REGISTERMODECODE;
                            } else if (typeOfScreen == 2) {
                                animation(ForgetPassword, registerCode, dowm, up);
                                MoDE = REGISTERMODECODE;
                            }

                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
        }
    }

    public class SendSMS extends AsyncTask<Object, Void, String> {

        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        URL url;
        String content = null;
        int success;
        Request request;
        Response response;
        RequestBody body;
        Gson gson = new Gson();

        @Override
        protected String doInBackground(Object... objects) {
            phonePass = objects[0] + "";
            try {
                url = new URL(APIManager.URL_SEND_SMS);
                Map<String, String> mRequest = new HashMap<>();
                mRequest.put("phone", objects[0] + "");
                body = RequestBody.create(JSON, gson.toJson(mRequest));
                request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                response = client.newCall(request).execute();
                content = response.body().string();

                Log.e("content ", "doInBackground: "+content );

                JSONObject jsonObject = new JSONObject(content);

                success = jsonObject.getInt("success");
                if (success == 1) {
                    String vcode = jsonObject.getString("vcode");
                    DataManager.setStringSetting(LoginRegister.this, "verify_code", vcode);
                    Log.e("SendSMS ", "SendSMS: code " + vcode);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(LoginRegister.this, "Verification code sent to you via SMS", Toast.LENGTH_SHORT).show();

                            if (typeOfScreen == 1) {
                                animation(register, registerCode, dowm, up);
                                MoDE = REGISTERMODECODE;
                            } else if (typeOfScreen == 2) {
                                animation(ForgetPassword, registerCode, dowm, up);
                                MoDE = REGISTERMODECODE;
                            }

                        }
                    });
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class UpdatePassword extends AsyncTask<Object, Void, String> {

        public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        URL url;
        String content = null;
        boolean success;
        Request request;
        Response response;
        RequestBody body;
        Gson gson = new Gson();

        @Override
        protected String doInBackground(Object... objects) {

            try {
                url = new URL(APIManager.URL_FORGETPASSWORD_PHONE);
                Map<String, String> mRequest = new HashMap<>();
                mRequest.put("phone", objects[0] + "");
                mRequest.put("new_password", objects[1] + "");
                body = RequestBody.create(JSON, gson.toJson(mRequest));
                request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                response = client.newCall(request).execute();
                content = response.body().string();

                Log.e("content ", "doInBackground: "+content );

                JSONObject jsonObject = new JSONObject(content);

                success = jsonObject.getBoolean("success");
                if (success) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginRegister.this, " Password Updated Successfully ", Toast.LENGTH_SHORT).show();
                            animation(F_fram_forgetpassword_code, login, dowm, up);
                            MoDE = LOGINMODE;
                        }
                    });
                } else {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void SendSMS(String phone) { // TODO SMS
        Log.e("phone", "doInBackground: " + phone);

        phone = "249906844174";
        phonePass = phone;

        APIManager.SendSMS(LoginRegister.this, phone, new APIManager.ResponseListener<SMSModel>() {
            @Override
            public void done(SMSModel dataModel) {
                if (dataModel.getSuccess()) {

                    String code = dataModel.getVcode();
                    DataManager.setStringSetting(LoginRegister.this, "verify_code", code);
                    Log.e("SendSMS ", "SendSMS: code " + code);

                    Toast.makeText(LoginRegister.this, "Verification code sent to you via SMS", Toast.LENGTH_SHORT).show();

                    if (typeOfScreen == 1) {
                        animation(register, registerCode, dowm, up);
                        MoDE = REGISTERMODECODE;
                    } else if (typeOfScreen == 2) {
                        animation(ForgetPassword, registerCode, dowm, up);
                        MoDE = REGISTERMODECODE;
                    }

                } else {
                    Toast.makeText(LoginRegister.this, "Verification code failed to send !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failed(boolean fromConnection) {

            }
        });

    }

    public void GetCountry() {

        APIManager.GetCountry(LoginRegister.this, new APIManager.ResponseListener<CountriesModel>() {
            @Override
            public void done(CountriesModel dataModel) {
                if (dataModel.getSuccess()) {
                    CountryName = new ArrayList<>();
                    CountryId = new ArrayList<>();

                    for (int i = 0; i < dataModel.getDataModelList().size(); i++) {
                        CountryName.add(dataModel.getDataModelList().get(i).getName());
                        CountryId.add(dataModel.getDataModelList().get(i).getCountry_id());


                    }

                    countrySpinnerAdapter.setDataList(CountryName);
                    // country.setSelection(0);
                }

            }

            @Override
            public void failed(boolean fromConnection) {

            }
        });

    }

    public void GetCity() {

        APIManager.Getcities(LoginRegister.this, CountryId.get(country.getSelectedItemPosition() - 1).toString(), new APIManager.ResponseListener<CitesModel>() {
            @Override
            public void done(CitesModel dataModel) {
                if (dataModel.getSuccess()) {
                    CityName = new ArrayList<>();
                    CityId = new ArrayList<>();
                    for (int i = 0; i < dataModel.getData().getZoneModelList().size(); i++) {
                        CityName.add(dataModel.getData().getZoneModelList().get(i).getName());
                        CityId.add(dataModel.getData().getZoneModelList().get(i).getZone_id());


                    }

                    citySpinnerAdapter.setDataList(CityName);
                    //  city.setSelection(0);
                }
            }

            @Override
            public void failed(boolean fromConnection) {

            }
        });

    }


}
