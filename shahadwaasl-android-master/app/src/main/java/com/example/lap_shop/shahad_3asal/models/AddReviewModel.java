package com.example.lap_shop.shahad_3asal.models;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LAP_SHOP on 03/05/2017.
 */

public class AddReviewModel implements Parcelable {

    private String name, text, rating,success;


    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setText(String text) {
        this.text = text;
    }


    public AddReviewModel(String name, String text, String rating) {
        this.name = name;
        this.text = text;
        this.rating = rating;
    }


    protected AddReviewModel(Parcel in) {
        this.success=in.readString();
    }

    public static final Creator<AddReviewModel> CREATOR = new Creator<AddReviewModel>() {
        @Override
        public AddReviewModel createFromParcel(Parcel in) {
            return new AddReviewModel(in);
        }

        @Override
        public AddReviewModel[] newArray(int size) {
            return new AddReviewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.success);    }
}