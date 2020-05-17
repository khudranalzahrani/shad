package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LAP_SHOP on 20/04/2017.
 */

public class RegistrationModel implements Parcelable {
    /*
    *
    * {
  "success": true,
  "data": {
    "address_1": "Test street 88",
    "city": "Berlin",
    "country_id": "97",
    "email": "testtesttest@test.com",
    "fax": "1",
    "firstname": "Test",
    "lastname": "User",
    "postcode": "1111",
    "telephone": "+36306668884",
    "zone_id": "1433",
    "customer_id": 46
  }
}
    *
    *
    * */

    private boolean success;
    @SerializedName("data")
    private DataModel data;
    /*private String error;*/

    public DataModel getData() {
        return data;
    }
/*
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }*/

    public void setData(DataModel data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    protected RegistrationModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = in.readParcelable(RegistrationModel.DataModel.class.getClassLoader());
       // this.error = in.readString();

    }

    public static final Creator<RegistrationModel> CREATOR = new Creator<RegistrationModel>() {
        @Override
        public RegistrationModel createFromParcel(Parcel in) {
            return new RegistrationModel(in);
        }

        @Override
        public RegistrationModel[] newArray(int size) {
            return new RegistrationModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
        dest.writeParcelable(this.data, flags);
      //  dest.writeString(this.error);
    }

    public static class DataModel implements Parcelable {

        /*
        * {
    "address_1": "Test street 88",
    "city": "Berlin",
    "country_id": "97",
    "email": "testtesttest@test.com",
    "fax": "1",
    "firstname": "Test",
    "lastname": "User",
    "postcode": "1111",
    "telephone": "+36306668884",
    "zone_id": "1433",
    "customer_id": 46*/
        private String address_1,address_2, city, country_id, email, fax, firstname, lastname, postcode, telephone, zone_id,confirm,password,agree,new_password,phone;
        private int customer_id;

        public DataModel(String email,String new_password) {
            this.phone = email;
            this.new_password = new_password;
        }

        public DataModel(String mobile, String pass, String address, String address_2, String city, String country, String email, String fax, String firstname, String lastname, String postcode
                , String zone, String confirm, String agree) {
            this.telephone = mobile;
            this.address_1 = address;
            this.address_2 = address_2;
            this.city=city;
            this.country_id=country;
            this.email=email;
            this.fax=fax;
            this.firstname=firstname;
            this.lastname=lastname;
            this.postcode=postcode;
            this.zone_id=zone;
            this.confirm=confirm;
            this.password=pass;
            this.agree=agree;

        }
        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public void setCustomer_id(int customer_id) {
            this.customer_id = customer_id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public void setZone_id(String zone_id) {
            this.zone_id = zone_id;
        }

        public String getEmail() {
            return email;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public String getAddress_1() {
            return address_1;
        }

        public String getCity() {
            return city;
        }

        public String getCountry_id() {
            return country_id;
        }

        public String getFax() {
            return fax;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public String getPostcode() {
            return postcode;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getZone_id() {
            return zone_id;
        }

        protected DataModel(Parcel in) {
            this.address_1 = in.readString();
            this.city = in.readString();
            this.country_id = in.readString();
            this.email = in.readString();
            this.fax = in.readString();
            this.firstname = in.readString();
            this.lastname = in.readString();
            this.postcode = in.readString();
            this.telephone = in.readString();
            this.zone_id = in.readString();
            this.customer_id = in.readInt();
        }

        public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
            @Override
            public DataModel createFromParcel(Parcel in) {
                return new DataModel(in);
            }

            @Override
            public DataModel[] newArray(int size) {
                return new DataModel[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.address_1);
            dest.writeString(this.city);
            dest.writeString(this.country_id);
            dest.writeString(this.email);
            dest.writeString(this.fax);
            dest.writeString(this.firstname);
            dest.writeString(this.lastname);
            dest.writeString(this.postcode);
            dest.writeString(this.telephone);
            dest.writeString(this.zone_id);
            dest.writeInt(this.customer_id);

        }
    }
}
