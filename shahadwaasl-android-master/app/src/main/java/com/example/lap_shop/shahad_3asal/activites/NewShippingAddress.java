package com.example.lap_shop.shahad_3asal.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.adapters.SpinnerAdapter;
import com.example.lap_shop.shahad_3asal.models.CitesModel;
import com.example.lap_shop.shahad_3asal.models.CountriesModel;
import com.example.lap_shop.shahad_3asal.models.RegisteredAddresses;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.LogInManager;
import com.example.lap_shop.shahad_3asal.tools.ValidationManager;
import com.example.lap_shop.shahad_3asal.ui.LoadingManager;

import java.util.ArrayList;
import java.util.List;

public class NewShippingAddress extends AppCompatActivity {

    Toolbar toolbar;
    EditText et_fName, et_lName, et_email, et_fAddress, et_sAddress, et_area, et_pastcode;
    TextView tv_continue;
    Spinner sp_country, sp_city;
    LoadingManager loadingManager;
    List CountryName, CountryId, CityName, CityId;
    SpinnerAdapter citySpinnerAdapter, countrySpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shipping_address);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.TitleNewShippingAddress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_fName = (EditText) findViewById(R.id.tv_fName);
        et_lName = (EditText) findViewById(R.id.tv_lName);
        et_email = (EditText) findViewById(R.id.tv_email);
        et_fAddress = (EditText) findViewById(R.id.tv_faddress);
        et_sAddress = (EditText) findViewById(R.id.tv_saddress);
        et_area = (EditText) findViewById(R.id.sp_zone);
        et_pastcode = (EditText) findViewById(R.id.tv_postcode);

        sp_country = (Spinner) findViewById(R.id.sp_country);
        sp_city = (Spinner) findViewById(R.id.sp_city);

        loadingManager = new LoadingManager(this, findViewById(R.id.progressBar));

        tv_continue = (TextView) findViewById(R.id.tv_continue);
        countrySpinnerAdapter = new SpinnerAdapter(this, sp_country, R.layout.spinner_item_simple_header, R.string.country);
        citySpinnerAdapter = new SpinnerAdapter(this, sp_city, R.layout.spinner_item_simple_header, R.string.city);

        GetCountry();

        sp_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        tv_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (ValidationManager.validateEmptyFields(NewShippingAddress.this, et_fName, et_lName, et_email, et_area, et_fAddress, et_sAddress) &&
                        ValidationManager.isemptySpinner(NewShippingAddress.this, sp_country, sp_city) &&
                        ValidationManager.validatesmallFields(NewShippingAddress.this, et_fName, et_lName, et_email, et_area, et_fAddress, et_sAddress)
                            && ValidationManager.isValidPostCode(NewShippingAddress.this,et_pastcode)) {




                        APIManager.postNewAddresses(NewShippingAddress.this,LogInManager.getUserSession(NewShippingAddress.this),
                                et_fName.getText().toString(), et_lName.getText().toString(), et_fAddress.getText().toString()
                                , et_sAddress.getText().toString(), et_pastcode.getText().toString() ,et_area.getText().toString(),
                                sp_country.getSelectedItemId()+""
                                ,"company","company","12",sp_city.getSelectedItem().toString(),
                                new APIManager.ResponseListener<RegisteredAddresses>() {

                                    @Override
                                    public void done(RegisteredAddresses dataModel) {
                                        if (dataModel.isSuccess()){
                                            Toast.makeText(NewShippingAddress.this, "added successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent=new Intent(NewShippingAddress.this , AcceptOrder.class);
                                           // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(NewShippingAddress.this, "++"+ dataModel.getError() , Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void failed(boolean fromConnection) {
                                        Toast.makeText(NewShippingAddress.this, "can't add ", Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }else{
                        Intent intent=new Intent(NewShippingAddress.this , NewShippingAddress.class);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);


                }
            }
        });
}
    public void GetCountry() {

        APIManager.GetCountry(this, new APIManager.ResponseListener<CountriesModel>() {
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

        APIManager.Getcities(NewShippingAddress.this, CountryId.get(sp_country.getSelectedItemPosition()-1).toString(), new APIManager.ResponseListener<CitesModel>() {
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


