package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP_SHOP on 25/04/2017.
 */

public class LatestProduct implements Parcelable {
    /*
    *  "success": true,
  "data": [*/
    private Boolean success;
    @SerializedName("data")
    private List<LatestProduct.ProductsListModel> productsListModels;

    public void setProductsListModels(List<ProductsListModel> productsListModels) {
        this.productsListModels = productsListModels;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ProductsListModel> getProductsListModels() {
        return productsListModels;
    }


    protected LatestProduct(Parcel in) {
        this.success = in.readByte() != 0;
        this.productsListModels = new ArrayList<LatestProduct.ProductsListModel>();
        in.readList(this.productsListModels, List.class.getClassLoader());


    }

    public static final Creator<LatestProduct> CREATOR = new Creator<LatestProduct>() {
        @Override
        public LatestProduct createFromParcel(Parcel in) {
            return new LatestProduct(in);
        }

        @Override
        public LatestProduct[] newArray(int size) {
            return new LatestProduct[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
        dest.writeList(this.productsListModels);
    }

    public static class ProductsListModel implements Parcelable {
        /*
        *
        *   {
      "product_id": "51",
      "thumb": "http://newapi2.opencart-api.com/image/cache/no_image-500x500.png",
      "name": "test product",
      "price_excluding_tax": "1,000.00",
      "price": "1,202.00",
      "price_formated": "$1,202.00",
      "special": false,
      "special_excluding_tax": false,
      "special_formated": false,
      "discounts": [],
      "rating": 0,
      "description": "test product description.."
    }
        *
        * */
        private String product_id, thumb, name, price_excluding_tax, price, price_formated, description;
        private Boolean special, special_excluding_tax;
        private int rating;
        @SerializedName("discountsModel")
        private List<ProductsListModel.discountsModel> discountsModelList;

        public String getName() {
            return name;
        }

        public Boolean getSpecial() {
            return special;
        }

        public Boolean getSpecial_excluding_tax() {
            return special_excluding_tax;
        }

        public String getDescription() {
            return description;
        }

        public String getPrice() {
            return price;
        }

        public int getRating() {
            return rating;
        }

        public List<discountsModel> getDiscountsModelList() {
            return discountsModelList;
        }

        public String getPrice_excluding_tax() {
            return price_excluding_tax;
        }

        public String getPrice_formated() {
            return price_formated;
        }

        public String getProduct_id() {
            return product_id;
        }

        public String getThumb() {
            return thumb;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setDiscountsModelList(List<discountsModel> discountsModelList) {
            this.discountsModelList = discountsModelList;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPrice_excluding_tax(String price_excluding_tax) {
            this.price_excluding_tax = price_excluding_tax;
        }

        public void setPrice_formated(String price_formated) {
            this.price_formated = price_formated;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public void setSpecial(Boolean special) {
            this.special = special;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setSpecial_excluding_tax(Boolean special_excluding_tax) {
            this.special_excluding_tax = special_excluding_tax;
        }


        protected ProductsListModel(Parcel in) {
            this.product_id = in.readString();
            this.thumb = in.readString();
            this.price_excluding_tax = in.readString();
            this.price = in.readString();
            this.price_formated = in.readString();
            this.special = in.readByte() != 0;
            this.special_excluding_tax = in.readByte() != 0;
            this.discountsModelList = new ArrayList<ProductsListModel.discountsModel>();
            in.readList(this.discountsModelList, List.class.getClassLoader());
            this.rating=in.readInt();
            this.description = in.readString();
        }
        public static final Creator<ProductsListModel> CREATOR = new Creator<ProductsListModel>() {
            @Override
            public ProductsListModel createFromParcel(Parcel in) {
                return new ProductsListModel(in);
            }

            @Override
            public ProductsListModel[] newArray(int size) {
                return new ProductsListModel[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.product_id);
            dest.writeString(this.thumb);
            dest.writeString(this.price_excluding_tax);
            dest.writeString(this.price);
            dest.writeString(this.price_formated);
            dest.writeByte((byte) (this.special ? 1 : 0));
            dest.writeByte((byte) (this.special_excluding_tax ? 1 : 0));
            dest.writeList(this.discountsModelList);
            dest.writeInt(this.rating);
            dest.writeString(this.description);


        }

        public static class discountsModel implements Parcelable {

            protected discountsModel(Parcel in) {
            }

            public static final Creator<discountsModel> CREATOR = new Creator<discountsModel>() {
                @Override
                public discountsModel createFromParcel(Parcel in) {
                    return new discountsModel(in);
                }

                @Override
                public discountsModel[] newArray(int size) {
                    return new discountsModel[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
            }
        }
    }
}
