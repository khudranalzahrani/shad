package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LAP_SHOP on 09/05/2017.
 */

public class ContactModel implements Parcelable {
    private String email, name, enquiry;
    private Boolean success;

    public ContactModel(String email, String name, String enquiry) {
        this.email = email;
        this.name = name;
        this.enquiry = enquiry;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnquiry(String enquiry) {
        this.enquiry = enquiry;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getEmail() {
        return email;
    }

    public String getEnquiry() {
        return enquiry;
    }


    protected ContactModel(Parcel in) {
        this.success = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContactModel> CREATOR = new Creator<ContactModel>() {
        @Override
        public ContactModel createFromParcel(Parcel in) {
            return new ContactModel(in);
        }

        @Override
        public ContactModel[] newArray(int size) {
            return new ContactModel[size];
        }
    };
}
