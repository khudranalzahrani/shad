package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP_SHOP on 10/05/2017.
 */

public class CountriesModel implements Parcelable {
    /*"success": true,
  "data": [
    {
      "country_id": "244",
      "name": "Aaland Islands"*/
    private Boolean success;
    @SerializedName("data")
    private List<DataModel> dataModelList;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<DataModel> getDataModelList() {
        return dataModelList;
    }

    public void setDataModelList(List<DataModel> dataModelList) {
        this.dataModelList = dataModelList;
    }

    protected CountriesModel(Parcel in) {

        this.success = in.readByte() != 0;
        this.dataModelList = new ArrayList<DataModel>();
        in.readList(this.dataModelList, List.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
        dest.writeList(this.dataModelList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CountriesModel> CREATOR = new Creator<CountriesModel>() {
        @Override
        public CountriesModel createFromParcel(Parcel in) {
            return new CountriesModel(in);
        }

        @Override
        public CountriesModel[] newArray(int size) {
            return new CountriesModel[size];
        }
    };

    public static class DataModel implements Parcelable {
        /*"country_id": "244",
      "name": "Aaland Islands"*/
        private String country_id, name;

        public String getName() {
            return name;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry_id() {
            return country_id;
        }

        protected DataModel(Parcel in) {
            this.country_id=in.readString();
            this.name=in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.country_id);
            dest.writeString(this.name);
        }

        @Override
        public int describeContents() {
            return 0;
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
    }
}
