package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Salamony on 6/8/2017.
 */

public class AcceptOrderModel implements Parcelable {

   /* {
        "success": true,
            "data":{
        "products":[
        {
                "key":"360",
                "product_id":"34",
                "name":"iPod Shuffle",
                "model":"Product 7",
                "option":[],
                "recurring":"",
                "quantity":"1",
                "subtract":"1",
                "price":"$122.00",
                "total":"$122.00",
                "href":""
        }
        ],
    }
    */
    ///////////////////////////////////////////////////
/*
    "totals":[
    {
        "title":"Sub-Total",
            "text":"$400.00"
    },
    {
        "title":"Eco Tax (-2.00)",
            "text":"$6.00"
    },
    {
        "title":"VAT (20%)",
            "text":"$80.00"
    },
    {
        "title":"Total",
            "text":"$486.00"
    }
    ],
    */







    private boolean success;
    @SerializedName("data")
    private AcceptOrderModel.DataModel data;

    public static final Creator<AcceptOrderModel> CREATOR = new Creator<AcceptOrderModel>() {
        @Override
        public AcceptOrderModel createFromParcel(Parcel in) {
            return new AcceptOrderModel(in);
        }

        @Override
        public AcceptOrderModel[] newArray(int size) {
            return new AcceptOrderModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    protected  AcceptOrderModel(Parcel in){
        this.success = in.readByte() != 0;
        this.data = in.readParcelable(AcceptOrderModel.DataModel.class.getClassLoader());
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
        dest.writeParcelable(this.data, flags);
    }

    public static class DataModel implements Parcelable {
        @SerializedName("products")
        private List<AcceptOrderModel.DataModel.productsModel> productsModels;
        @SerializedName("totals")
        private List<AcceptOrderModel.DataModel.TotalModel> totalModels;

        public List<TotalModel> getTotalModels() {
            return totalModels;
        }

        public void setTotalModels(List<TotalModel> totalModels) {
            this.totalModels = totalModels;
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

        public List<productsModel> getProductsModels() {
            return productsModels;
        }

        public void setProductsModels(List<productsModel> productsModels) {
            this.productsModels = productsModels;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        protected DataModel(Parcel in) {
            this.productsModels = new ArrayList<AcceptOrderModel.DataModel.productsModel>();
            in.readList(this.productsModels, List.class.getClassLoader());
            this.totalModels= new ArrayList<AcceptOrderModel.DataModel.TotalModel>();
            in.readList(this.totalModels,List.class.getClassLoader());
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.productsModels);
            dest.writeList(this.totalModels);
        }

        public static class productsModel implements Parcelable {

           private String name,price,quantity,total;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            @Override
            public int describeContents() {
                return 0;
            }
            protected productsModel(Parcel in) {
                this.name=in.readString();
                this.quantity=in.readString();
                this.price=in.readString();
                this.total=in.readString();

            }
            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.name);
                dest.writeString(this.quantity);
                dest.writeString(this.price);
                dest.writeString(this.total);
            }
        }
        public static class TotalModel implements Parcelable {
        private String title,text;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.title);
                dest.writeString(this.text);
            }

            protected TotalModel(Parcel in) {
                this.title=in.readString();
                this.text=in.readString();
            }
        }

        }
    }
