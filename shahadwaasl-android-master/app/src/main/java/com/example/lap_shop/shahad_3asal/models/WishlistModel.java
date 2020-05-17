package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP_SHOP on 04/05/2017.
 */

public class WishlistModel implements Parcelable {
    /*
    *
    * {
  "success": true,
  "data": {
    "products": [
      {
        "product_id": "47",
        "thumb": "http://appstest.xyz/shary/image/cache/catalog/demo/hp_1-500x500.jpg",
        "name": "HP LP3065",
        "model": "Product 21",
        "stock": "In Stock",
        "price": "100.00 SAR",
        "special": false
      }
    ]
  }
}
    *
    * */
    Boolean success;
    @SerializedName("data")
    DataModel data;

    public Boolean getSuccess() {
        return success;
    }

    public void setData(DataModel data) {
        this.data = data;
    }

    public DataModel getData() {
        return data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    protected WishlistModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = in.readParcelable(LoginModel.DataModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
        dest.writeParcelable(this.data, flags);
    }

    public static final Creator<WishlistModel> CREATOR = new Creator<WishlistModel>() {
        @Override
        public WishlistModel createFromParcel(Parcel in) {
            return new WishlistModel(in);
        }

        @Override
        public WishlistModel[] newArray(int size) {
            return new WishlistModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public static class DataModel implements Parcelable {
        /*
        *  "products": [
              {
                "product_id": "47",
                "thumb": "http://appstest.xyz/shary/image/cache/catalog/demo/hp_1-500x500.jpg",
                "name": "HP LP3065",
                "model": "Product 21",
                "stock": "In Stock",
                "price": "100.00 SAR",
                "special": false
              }
            ]
        *
        * */
        @SerializedName("products")
        List<producrswishlistModel> producrswishlistModelList;

        public List<producrswishlistModel> getProducrswishlistModelList() {
            return producrswishlistModelList;
        }

        public void setProducrswishlistModelList(List<producrswishlistModel> producrswishlistModelList) {
            this.producrswishlistModelList = producrswishlistModelList;
        }


        protected DataModel(Parcel in) {
            this.producrswishlistModelList = new ArrayList<producrswishlistModel>();
            in.readList(this.producrswishlistModelList, List.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

            dest.writeList(this.producrswishlistModelList);
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


        public static class producrswishlistModel implements Parcelable {
            /*
            *
            *    {
                "product_id": "47",
                "thumb": "http://appstest.xyz/shary/image/cache/catalog/demo/hp_1-500x500.jpg",
                "name": "HP LP3065",
                "model": "Product 21",
                "stock": "In Stock",
                "price": "100.00 SAR",
                "special": false
              }*/

            private String product_id, thumb, name, model, stock, price;

            public String getModel() {
                return model;
            }

            public String getName() {
                return name;
            }

            public String getPrice() {
                return price;
            }

            public String getProduct_id() {
                return product_id;
            }

            public String getStock() {
                return stock;
            }

            public String getThumb() {
                return thumb;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            protected producrswishlistModel(Parcel in) {
                this.product_id=in.readString();
                this.thumb=in.readString();
                this.name=in.readString();
                this.model=in.readString();
                this.stock=in.readString();
                this.price=in.readString();
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.product_id);
                dest.writeString(this.thumb);
                dest.writeString(this.name);
                dest.writeString(this.model);
                dest.writeString(this.stock);
                dest.writeString(this.price);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<producrswishlistModel> CREATOR = new Creator<producrswishlistModel>() {
                @Override
                public producrswishlistModel createFromParcel(Parcel in) {
                    return new producrswishlistModel(in);
                }

                @Override
                public producrswishlistModel[] newArray(int size) {
                    return new producrswishlistModel[size];
                }
            };
        }
    }


}
