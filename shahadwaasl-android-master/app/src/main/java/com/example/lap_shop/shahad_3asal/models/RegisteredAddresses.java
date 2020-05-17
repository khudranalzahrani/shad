package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Salamony on 6/6/2017.
 */

public class RegisteredAddresses implements Parcelable {

    /* {
            "success": true,
                "data": {
            "address_id": "213",
                    "addresses": [
            {
                    "address_id": "204",
                    "firstname": "Test",
                    "lastname": "User 2",
                    "company": "company",
                    "address_1": "Kossuth Lajos út 88",
                    "address_2": "test",
                    "postcode": "1111",
                    "city": "Budapest",
                    "zone_id": "1433",
                    "zone": "",
                    "zone_code": "",
                    "country_id": "97",
                    "country": "Hungary",
                    "iso_code_2": "HU",
                    "iso_code_3": "HUN",
                    "address_format": "",
                    "custom_field": null
            },
            ]}*/

    private boolean success;
    @SerializedName("data")
    private DataModel data;
    private String error;



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataModel getData() {
        return data;
    }

    public void setData(DataModel data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected RegisteredAddresses(Parcel in) {
        this.success = in.readByte() != 0;
        this.error   = in.readString();
        this.data = in.readParcelable(RegisteredAddresses.DataModel.class.getClassLoader());
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
        dest.writeString(this.error);
        dest.writeParcelable(this.data, flags);
    }

    public static final Creator<RegisteredAddresses> CREATOR = new Creator<RegisteredAddresses>() {
        @Override
        public RegisteredAddresses createFromParcel(Parcel in) {
            return new RegisteredAddresses(in);
        }

        @Override
        public RegisteredAddresses[] newArray(int size) {
            return new RegisteredAddresses[size];
        }
    };
   public static class DataModel implements Parcelable{
       private String address_id;
       @SerializedName("addresses")
       private List<RegisteredAddresses.DataModel.AddressesModel> addressesModel;

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

       public String getAddress_id() {
           return address_id;
       }

       public void setAddress_id(String address_id) {
           this.address_id = address_id;
       }

       public List<AddressesModel> getAddressesModel() {
           return addressesModel;
       }

       public void setAddressesModel(List<AddressesModel> addressesModel) {
           this.addressesModel = addressesModel;
       }

       @Override
       public int describeContents() {
           return 0;
       }

       protected DataModel(Parcel in) {
            this.address_id = in.readString();
            this.addressesModel = new ArrayList<RegisteredAddresses.DataModel.AddressesModel>();
            in.readList(this.addressesModel, List.class.getClassLoader());
       }


       @Override
       public void writeToParcel(Parcel dest, int flags) {
           dest.writeString(this.address_id);
           dest.writeList(this.addressesModel);
       }

       public static class AddressesModel implements Parcelable{

           private String firstname,lastname,postcode,city,zone,country,address_1,address_2,country_id,
                   company_id,company,tax_id,zone_id;

           public String getCompany_id() {
               return company_id;
           }

           public void setCompany_id(String company_id) {
               this.company_id = company_id;
           }

           public String getCompany() {
               return company;
           }

           public void setCompany(String company) {
               this.company = company;
           }

           public String getTax_id() {
               return tax_id;
           }

           public void setTax_id(String tax_id) {
               this.tax_id = tax_id;
           }

           public String getZone_id() {
               return zone_id;
           }

           public void setZone_id(String zone_id) {
               this.zone_id = zone_id;
           }

           public String getFirstname() {
               return firstname;
           }

           public void setFirstname(String firstname) {
               this.firstname = firstname;
           }

           public String getLastname() {
               return lastname;
           }

           public void setLastname(String lastname) {
               this.lastname = lastname;
           }

           public String getPostcode() {
               return postcode;
           }

           public void setPostcode(String postcode) {
               this.postcode = postcode;
           }

           public String getCity() {
               return city;
           }

           public void setCity(String city) {
               this.city = city;
           }

           public String getZone() {
               return zone;
           }

           public void setZone(String zone) {
               this.zone = zone;
           }

           public String getCountry() {
               return country;
           }

           public void setCountry(String country) {
               this.country = country;
           }

           public static final Creator<AddressesModel> CREATOR = new Creator<AddressesModel>() {
               @Override
               public AddressesModel createFromParcel(Parcel in) {
                   return new AddressesModel(in);
               }

               @Override
               public AddressesModel[] newArray(int size) {
                   return new AddressesModel[size];
               }
           };

           public String getAddress_1() {
               return address_1;
           }

           public void setAddress_1(String address_1) {
               this.address_1 = address_1;
           }

           public String getAddress_2() {
               return address_2;
           }

           public void setAddress_2(String address_2) {
               this.address_2 = address_2;
           }

           public String getCountry_id() {
               return country_id;
           }

           public void setCountry_id(String country_id) {
               this.country_id = country_id;
           }

           @Override
           public int describeContents() {
               return 0;
           }
// company_id,company,tax_id,zone_id
           @Override
           public void writeToParcel(Parcel dest, int flags) {
               dest.writeString(this.firstname);
               dest.writeString(this.lastname);
               dest.writeString(this.address_1);
               dest.writeString(this.address_2);
               dest.writeString(this.postcode);
               dest.writeString(this.city);
               dest.writeString(this.zone);
               dest.writeString(this.country_id);
               dest.writeString(this.country);
               dest.writeString(this.company_id);
               dest.writeString(this.company);
               dest.writeString(this.tax_id);
               dest.writeString(this.zone_id);

           }
           protected AddressesModel(Parcel in) {
               this.firstname  = in.readString();
               this.lastname   = in.readString();
               this.address_1  = in.readString();
               this.address_2  = in.readString();
               this.postcode   = in.readString();
               this.city       = in.readString();
               this.zone       = in.readString();
               this.country_id = in.readString();
               this.country    = in.readString();
               this.company_id = in.readString();
               this.company    = in.readString();
               this.tax_id     = in.readString();
               this.zone_id    = in.readString();
           }

           public AddressesModel(String firstname, String lastname, String postcode, String city, String address_1,
                                 String address_2, String country_id, String company_id, String company, String tax_id, String zone_id) {
               this.firstname = firstname;
               this.lastname = lastname;
               this.postcode = postcode;
               this.city = city;
               this.address_1 = address_1;
               this.address_2 = address_2;
               this.country_id = country_id;
               this.company_id = company_id;
               this.company = company;
               this.tax_id = tax_id;
               this.zone_id = zone_id;
           }
       }
   }
}
