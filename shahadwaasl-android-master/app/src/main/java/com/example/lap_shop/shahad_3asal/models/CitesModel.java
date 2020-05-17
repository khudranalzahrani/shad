package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP_SHOP on 10/05/2017.
 */

public class CitesModel implements Parcelable {

    /*success": true,
  "data": {}*/

    private Boolean success;
    @SerializedName("data")
    private DataModel data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DataModel getData() {
        return data;
    }

    public void setData(DataModel data) {
        this.data = data;
    }

    protected CitesModel(Parcel in) {
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

    public static final Creator<CitesModel> CREATOR = new Creator<CitesModel>() {
        @Override
        public CitesModel createFromParcel(Parcel in) {
            return new CitesModel(in);
        }

        @Override
        public CitesModel[] newArray(int size) {
            return new CitesModel[size];
        }
    };

    public static class DataModel implements Parcelable {
        /**
         * "zone": [
         */

        @SerializedName("zone")
        private List<ZoneModel> zoneModelList;
        protected DataModel(Parcel in) {
            this.zoneModelList = new ArrayList<ZoneModel>();
            in.readList(this.zoneModelList, List.class.getClassLoader());

        }

        public void setZoneModelList(List<ZoneModel> zoneModelList) {
            this.zoneModelList = zoneModelList;
        }

        public List<ZoneModel> getZoneModelList() {
            return zoneModelList;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.zoneModelList);
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

        public static class ZoneModel implements Parcelable {
            /*"country_id": "244",
          "name": "Aaland Islands"*/
            private String zone_id, name;

            public String getName() {
                return name;
            }

            public void setZone_id(String zone_id) {
                this.zone_id = zone_id;
            }

            public String getZone_id() {
                return zone_id;
            }

            public void setName(String name) {
                this.name = name;
            }



            protected ZoneModel(Parcel in) {
                this.zone_id=in.readString();
                this.name=in.readString();
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.zone_id);
                dest.writeString(this.name);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<CitesModel.DataModel.ZoneModel> CREATOR = new Creator<CitesModel.DataModel.ZoneModel>() {
                @Override
                public CitesModel.DataModel.ZoneModel createFromParcel(Parcel in) {
                    return new CitesModel.DataModel.ZoneModel(in);
                }

                @Override
                public CitesModel.DataModel.ZoneModel[] newArray(int size) {
                    return new CitesModel.DataModel.ZoneModel[size];
                }
            };
        }
    }
}
