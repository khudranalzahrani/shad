package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Salamony on 6/28/2017.
 */

public class latestordersModel implements Parcelable {

  /*  "success": true,
            "data": [
    {
            "order_id": "153",
            "name": "ahmed ahmed",
            "status": "Pending",
            "date_added": "2017-06-28 12:02:05",
            "products": 1,
            "total": "100.0000",
            "currency_code": null,
            "currency_value": "1.00000000"
    },*/

    private boolean success;
    @SerializedName("data")
    private List<DataModel> dataModelList;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataModel> getDataModelList() {
        return dataModelList;
    }

    public void setDataModelList(List<DataModel> dataModelList) {
        this.dataModelList = dataModelList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected latestordersModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.dataModelList = new ArrayList<DataModel>();
        in.readList(this.dataModelList, List.class.getClassLoader());
    }

    public static final Creator<latestordersModel> CREATOR = new Creator<latestordersModel>() {
        @Override
        public latestordersModel createFromParcel(Parcel in) {
            return new latestordersModel(in);
        }

        @Override
        public latestordersModel[] newArray(int size) {
            return new latestordersModel[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
        dest.writeList(this.dataModelList);
    }


    public static class DataModel implements Parcelable{

        private  String status,date_added,total;
        private  int products;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDate_added() {
            return date_added;
        }

        public void setDate_added(String date_added) {
            this.date_added = date_added;
        }

        public int getProducts() {
            return products;
        }

        public void setProducts(int products) {
            this.products = products;
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

        protected DataModel(Parcel in) {
            this.status=in.readString();
            this.date_added=in.readString();
            this.products=in.readInt();
            this.total=in.readString();
        }

        public static final Creator<latestordersModel.DataModel> CREATOR = new Creator<latestordersModel.DataModel>() {
            @Override
            public latestordersModel.DataModel createFromParcel(Parcel in) {
                return new latestordersModel.DataModel(in);
            }

            @Override
            public latestordersModel.DataModel[] newArray(int size) {
                return new latestordersModel.DataModel[size];
            }
        };

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.status);
            dest.writeString(this.date_added);
            dest.writeInt(this.products);
            dest.writeString(this.total);
        }
    }
}
