package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LAP_SHOP on 20/04/2017.
 */

public class SessionModel implements Parcelable {

    /*
    *
    * {
  "success": true,
  "data": {
    "session": "peraababdus8308pel0u0pkeq7"
  }
}*/
    Boolean success;
    @SerializedName("data")
    dataModel data;

    public Boolean getSuccess() {
        return success;
    }

    public void setData(dataModel data) {
        this.data = data;
    }

    public dataModel getData() {
        return data;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    protected SessionModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = in.readParcelable(SessionModel.dataModel.class.getClassLoader());
    }

    public static final Creator<SessionModel> CREATOR = new Creator<SessionModel>() {
        @Override
        public SessionModel createFromParcel(Parcel in) {
            return new SessionModel(in);
        }

        @Override
        public SessionModel[] newArray(int size) {
            return new SessionModel[size];
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
    }

    public static class dataModel implements Parcelable {
        private String session;

        public void setSession(String session) {
            this.session = session;
        }

        public String getSession() {
            return session;
        }

        protected dataModel(Parcel in) {
            this.session=in.readString();
        }

        public static final Creator<dataModel> CREATOR = new Creator<dataModel>() {
            @Override
            public dataModel createFromParcel(Parcel in) {
                return new dataModel(in);
            }

            @Override
            public dataModel[] newArray(int size) {
                return new dataModel[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.session);
        }
    }
}
