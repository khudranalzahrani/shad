package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LAP_SHOP on 23/04/2017.
 */

public class LoginModel implements Parcelable {
    String password,email;
    Boolean success;
    @SerializedName("data")
    DataModel data;
   /* String error;*/

    /*public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }*/
    public void setData(DataModel data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public DataModel getData() {
        return data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
//sssds
    /*
                *
                * {
                  "success": true,
                  "data": {
                    "customer_id": "58",
                    "customer_group_id": "1",
                    "store_id": "0",
                    "language_id": "1",
                    "firstname": "Test",
                    "lastname": "User",
                    "email": "e@yahoo.com",
                    "telephone": "+36306668884",
                    "fax": "1",
                    "cart": null,
                    "wishlist": null,
                    "newsletter": "0",
                    "address_id": "121",
                    "ip": "41.32.204.48",
                    "status": "1",
                    "approved": "1",
                    "safe": "0",
                    "code": "",
                    "date_added": "2017-04-23 07:53:37",
                    "session": "vs1job50t9attbcbotm760uar4",
                    "custom_fields": [],
                    "account_custom_field": null
                  }
                }
                * */
    public LoginModel(String email,String password){
        this.email=email;
        this.password=password;

    }

    protected LoginModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = in.readParcelable(LoginModel.DataModel.class.getClassLoader());
        //this.error=in.readString();
    }

    public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
        @Override
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        @Override
        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
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
       // dest.writeString(this.error);
    }

    public static class DataModel implements Parcelable{

        /*  "data": {
    "customer_id": "58",
    "customer_group_id": "1",
    "store_id": "0",
    "language_id": "1",
    "firstname": "Test",
    "lastname": "User",
    "email": "e@yahoo.com",
    "telephone": "+36306668884",
    "fax": "1",
    "cart": null,
    "wishlist": null,
    "newsletter": "0",
    "address_id": "121",
    "ip": "41.32.204.48",
    "status": "1",
    "approved": "1",
    "safe": "0",
    "code": "",
    "date_added": "2017-04-23 07:53:37",
    "session": "vs1job50t9attbcbotm760uar4",
    "custom_fields": [],
    "account_custom_field": null
  }*/
        String customer_id,customer_group_id,store_id,language_id,firstname,lastname,email,telephone;

        public String getTelephone() {
            return telephone;
        }

        public String getLastname() {
            return lastname;
        }

        public String getCustomer_group_id() {
            return customer_group_id;
        }

        public String getCustomer_id() {
            return customer_id;
        }

        public String getEmail() {
            return email;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLanguage_id() {
            return language_id;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setLanguage_id(String language_id) {
            this.language_id = language_id;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public void setCustomer_group_id(String customer_group_id) {
            this.customer_group_id = customer_group_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        protected DataModel(Parcel in) {
            this.customer_id=in.readString();
            this.customer_group_id=in.readString();
            this.store_id=in.readString();
            this.language_id=in.readString();
            this.firstname=in.readString();
            this.lastname=in.readString();
            this.email=in.readString();
            this.telephone=in.readString();
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
            dest.writeString(this.customer_id);
            dest.writeString(this.customer_group_id);
            dest.writeString(this.store_id);
            dest.writeString(this.language_id);
            dest.writeString(this.firstname);
            dest.writeString(this.lastname);
            dest.writeString(this.email);
            dest.writeString(this.telephone);
        }
    }

}
