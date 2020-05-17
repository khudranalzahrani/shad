package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP_SHOP on 07/05/2017.
 */

public class AddToCartModel implements Parcelable {
    /*
    *  "success": true,
  "data": {}
    *
    *
    * */

    private Boolean success;
    @SerializedName("data")
    private DataModel data;
    private String product_id, key;
    private String quantity;
    //private  String error;

  /*  public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
*/

    public AddToCartModel(String product_id, String quantity, int i) {
        if (i == 1) {
            this.product_id = product_id;
            this.quantity = quantity;
        } else if (i == 2) {


            this.key = product_id;
            this.quantity = quantity;
        }
    }

    public AddToCartModel(int product_id) {
        this.product_id = String.valueOf(product_id);

    }

    /*public AddToCartModel(){}*/

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public DataModel getData() {
        return data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setData(DataModel data) {
        this.data = data;
    }

    protected AddToCartModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = in.readParcelable(LoginModel.DataModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
        dest.writeParcelable(this.data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddToCartModel> CREATOR = new Creator<AddToCartModel>() {
        @Override
        public AddToCartModel createFromParcel(Parcel in) {
            return new AddToCartModel(in);
        }

        @Override
        public AddToCartModel[] newArray(int size) {
            return new AddToCartModel[size];
        }
    };


    public static class DataModel implements Parcelable {
        /*
        * products:[],
        *  "vouchers": [],
    "coupon_status": "1",
    "coupon": "",
    "voucher_status": "1",
    "voucher": "",
    "reward_status": false,
    "reward": "",
    "totals": [
      {}],
       "total": "1 item(s) - 100.00 SAR",
    "total_raw": 100,
    "total_product_count": 1
    */
        private String total, total_raw, total_product_count;
        @SerializedName("products")
        private List<productsModel> productsModelList;

        public List<productsModel> getProductsModelList() {
            return productsModelList;
        }

        public void setProductsModelList(List<productsModel> productsModelList) {
            this.productsModelList = productsModelList;
        }

        public String getTotal() {
            return total;
        }

        public String getTotal_product_count() {
            return total_product_count;
        }

        public String getTotal_raw() {
            return total_raw;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public void setTotal_product_count(String total_product_count) {
            this.total_product_count = total_product_count;
        }

        public void setTotal_raw(String total_raw) {
            this.total_raw = total_raw;
        }

        protected DataModel(Parcel in) {
            this.total = in.readString();
            this.total_raw = in.readString();
            this.total_product_count = in.readString();
            this.productsModelList = new ArrayList<productsModel>();
            in.readList(this.productsModelList, List.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.total);
            dest.writeString(this.total_raw);
            dest.writeString(this.total_product_count);
            dest.writeList(this.productsModelList);
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

        public static class productsModel implements Parcelable {

            /*  "key": "34",
        "thumb": "http://appstest.xyz/shary/image/cache/catalog/demo/ipod_shuffle_1-500x500.jpg",
        "name": "iPod Shuffle",
        "product_id": "34",
        "model": "Product 7",
        "option": [],
        "quantity": "1",
        "stock": true,
        "reward": "",
        "price": "100.00 SAR",
        "total": "100.00 SAR"*/
            private String key, thumb, name, product_id, model, quantity, price;
            private Boolean stock;
            private int orignal_quantity;

            public int getOrignal_quantity() {
                return orignal_quantity;
            }

            public void setOrignal_quantity(int orignal_quantity) {
                this.orignal_quantity = orignal_quantity;
            }

            public String getKey() {
                return key;
            }

            public Boolean getStock() {
                return stock;
            }

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

            public String getQuantity() {
                return quantity;
            }

            public String getThumb() {
                return thumb;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public void setKey(String key) {
                this.key = key;
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

            public void setStock(Boolean stock) {
                this.stock = stock;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            protected productsModel(Parcel in) {
                this.key = in.readString();
                this.thumb = in.readString();
                this.product_id = in.readString();
                this.model = in.readString();
                this.quantity = in.readString();
                this.price = in.readString();
                this.stock = in.readByte() != 0;
                this.orignal_quantity=in.readInt();
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.key);
                dest.writeString(this.thumb);
                dest.writeString(this.product_id);
                dest.writeString(this.model);
                dest.writeString(this.quantity);
                dest.writeString(this.price);
                dest.writeByte((byte) (this.stock ? 1 : 0));
                dest.writeInt(this.orignal_quantity);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<productsModel> CREATOR = new Creator<productsModel>() {

                @Override
                public productsModel createFromParcel(Parcel in) {
                    return new productsModel(in);
                }

                @Override
                public productsModel[] newArray(int size) {
                    return new productsModel[size];
                }
            };
        }
    }
}
