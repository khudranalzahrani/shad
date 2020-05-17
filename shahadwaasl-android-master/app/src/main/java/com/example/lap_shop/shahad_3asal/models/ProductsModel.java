package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP_SHOP on 26/04/2017.
 */

public class ProductsModel implements Parcelable {
  /*  {
        "success": true,
            "data": []}*/

    private Boolean success;

    @SerializedName("data")
    private List<DataModel> dataModelList;

    public Boolean getSuccess() {
        return success;
    }

    public void setDataModelList(List<DataModel> dataModelList) {
        this.dataModelList = dataModelList;
    }

    public List<DataModel> getDataModelList() {
        return dataModelList;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }


    protected ProductsModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.dataModelList = new ArrayList<DataModel>();
        in.readList(this.dataModelList, List.class.getClassLoader());
    }

    public static final Creator<ProductsModel> CREATOR = new Creator<ProductsModel>() {
        @Override
        public ProductsModel createFromParcel(Parcel in) {
            return new ProductsModel(in);
        }

        @Override
        public ProductsModel[] newArray(int size) {
            return new ProductsModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
        dest.writeList(this.dataModelList);
    }

    public static class DataModel implements Parcelable {
        /*
        *{
        * "id": "41",
        *  "name": "iMac",
        *   "image": "http://newapi2.opencart-api.com/image/cache/catalog/demo/imac_1-500x500.jpg",
        *      "price": "122.00",
        *     "price_formated": "$122.00",
        *      "rating": 0}
        *
        * */
        private String id,name,image,price,price_formated;
        private int rating;

        public int getRating() {
            return rating;
        }

        public String getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }

        public String getPrice_formated() {
            return price_formated;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPrice_formated(String price_formated) {
            this.price_formated = price_formated;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        protected DataModel(Parcel in) {
            this.id=in.readString();
            this.name=in.readString();
            this.image=in.readString();
            this.price=in.readString();
            this.price_formated=in.readString();
            this.rating=in.readInt();

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
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.image);
            dest.writeString(this.price);
            dest.writeString(this.price_formated);
            dest.writeInt(this.rating);
        }
    }
}
