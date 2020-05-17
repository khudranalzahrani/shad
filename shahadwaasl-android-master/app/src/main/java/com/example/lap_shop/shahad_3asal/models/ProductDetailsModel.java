package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP_SHOP on 30/04/2017.
 */

public class ProductDetailsModel implements Parcelable {
    Boolean success;
    @SerializedName("data")
    DataModel data;

    protected ProductDetailsModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.data = in.readParcelable(DataModel.class.getClassLoader());
    }

    public void setData(DataModel data) {
        this.data = data;
    }

    public DataModel getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public static final Creator<ProductDetailsModel> CREATOR = new Creator<ProductDetailsModel>() {
        @Override
        public ProductDetailsModel createFromParcel(Parcel in) {
            return new ProductDetailsModel(in);
        }

        @Override
        public ProductDetailsModel[] newArray(int size) {
            return new ProductDetailsModel[size];
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

    public static class DataModel implements Parcelable {
        /*
        * "id": "42",
        *  "images": [
      "http://newapi2.opencart-api.com/image/cache/catalog/demo/canon_eos_5d_1-500x500.jpg",
    ],
    "price_formated": "$239.60",
    "rating": 0,
     "description": "", "special": "110.00",
    "special_excluding_tax": "90.00",
    "special_formated": "$110.00",
      "category": [
      {
        "name": "Desktops",
        "id": "20"
      },
      {
        "name": "Monitors",
        "id": "28"
      }
    ],

     "reviews": {
      "review_total": "5",
      "reviews": [
        {
          "author": "emanz",
          "text": "test",
          "rating": 1,
          "date_added": "03pm31UTC_f2017Wed, 03 May 2017 15:00:06 +000005pm31_06032017Wed, 03 May 2017 15:00:06 +000031"
        },


        * */
        private String id, price_formated, description, special, special_excluding_tax, special_formated, name;
        private int rating;
        @SerializedName("images")
        private List<String> imageModelList;
        @SerializedName("category")
        private List<categoryModel> categoryModelList;
        @SerializedName("reviews")
        private reviewsModel reviewsModell;

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getImageModelList() {
            return imageModelList;
        }

        public void setImageModelList(List<String> imageModelList) {
            this.imageModelList = imageModelList;
        }

        public reviewsModel getReviewsModell() {
            return reviewsModell;
        }

        public void setReviewsModell(reviewsModel reviewsModell) {
            this.reviewsModell = reviewsModell;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setCategoryModelList(List<categoryModel> categoryModelList) {
            this.categoryModelList = categoryModelList;
        }

        public void setDescription(String description) {
            this.description = description;
        }


        public void setPrice_formated(String price_formated) {
            this.price_formated = price_formated;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public void setSpecial(String special) {
            this.special = special;
        }

        public void setSpecial_excluding_tax(String special_excluding_tax) {
            this.special_excluding_tax = special_excluding_tax;
        }

        public void setSpecial_formated(String special_formated) {
            this.special_formated = special_formated;
        }

        public String getSpecial_excluding_tax() {
            return special_excluding_tax;
        }

        public String getId() {
            return id;
        }

        public int getRating() {
            return rating;
        }

        public List<categoryModel> getCategoryModelList() {
            return categoryModelList;
        }

        public static Creator<DataModel> getCREATOR() {
            return CREATOR;
        }


        public String getDescription() {
            return description;
        }

        public String getPrice_formated() {
            return price_formated;
        }

        public String getSpecial() {
            return special;
        }

        public String getSpecial_formated() {
            return special_formated;
        }

        protected DataModel(Parcel in) {
            this.id = in.readString();
            this.price_formated = in.readString();
            this.description = in.readString();
            this.special = in.readString();
            this.special_excluding_tax = in.readString();
            this.special_formated = in.readString();
            this.name = in.readString();
            this.rating = in.readInt();
            this.categoryModelList = new ArrayList<categoryModel>();
            in.readList(this.categoryModelList, List.class.getClassLoader());
            this.imageModelList = new ArrayList<String>();
            in.readList(this.imageModelList, List.class.getClassLoader());
            this.reviewsModell = in.readParcelable(reviewsModel.class.getClassLoader());
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
            dest.writeString(this.price_formated);
            dest.writeString(this.description);
            dest.writeString(this.special);
            dest.writeString(this.special_excluding_tax);
            dest.writeString(this.special_formated);
            dest.writeString(this.name);
            dest.writeInt(this.rating);
            dest.writeList(this.categoryModelList);
            dest.writeList(this.imageModelList);
            dest.writeParcelable(this.reviewsModell, flags);
        }


        public static class categoryModel implements Parcelable {
            /*
            * "name": "Desktops",
        "id": "20"*/
            private String name, id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }


            protected categoryModel(Parcel in) {
                this.name = in.readString();
                this.id = in.readString();
            }

            public static final Creator<categoryModel> CREATOR = new Creator<categoryModel>() {
                @Override
                public categoryModel createFromParcel(Parcel in) {
                    return new categoryModel(in);
                }

                @Override
                public categoryModel[] newArray(int size) {
                    return new categoryModel[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.name);
                dest.writeString(this.id);
            }
        }

        public static class reviewsModel implements Parcelable {
            /*
            * "review_total": "5",
      "reviews": [
        {
          "author": "emanz",
          "text": "test",
          "rating": 1,
          "date_added": "03pm31UTC_f2017Wed, 03 May 2017 15:00:06 +000005pm31_06032017Wed, 03 May 2017 15:00:06 +000031"
        },
            *
            * */
            private String review_total;

            @SerializedName("reviews")
            private List<reviewsdetailsModel> reviewsdetailsModelList;

            public void setReview_total(String review_total) {
                this.review_total = review_total;
            }

            public List<reviewsdetailsModel> getReviewsdetailsModelList() {
                return reviewsdetailsModelList;
            }

            public void setReviewsdetailsModelList(List<reviewsdetailsModel> reviewsdetailsModelList) {
                this.reviewsdetailsModelList = reviewsdetailsModelList;
            }

            public String getReview_total() {
                return review_total;
            }


            protected reviewsModel(Parcel in) {
                this.review_total = in.readString();
                this.reviewsdetailsModelList = new ArrayList<reviewsdetailsModel>();
                in.readList(this.reviewsdetailsModelList, List.class.getClassLoader());

            }

            public static final Creator<reviewsModel> CREATOR = new Creator<reviewsModel>() {
                @Override
                public reviewsModel createFromParcel(Parcel in) {
                    return new reviewsModel(in);
                }

                @Override
                public reviewsModel[] newArray(int size) {
                    return new reviewsModel[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.review_total);
                dest.writeList(this.reviewsdetailsModelList);
            }

            public static class reviewsdetailsModel implements Parcelable {
                /*
                *
                * "author": "emanz",
          "text": "test",
          "rating": 1,
          "date_added": "0
                *
                * */
                private String author,text,date_added;
                private int rating;

                public void setText(String text) {
                    this.text = text;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public void setDate_added(String date_added) {
                    this.date_added = date_added;
                }

                public void setRating(int rating) {
                    this.rating = rating;
                }

                public String getText() {
                    return text;
                }

                public int getRating() {
                    return rating;
                }

                public String getAuthor() {
                    return author;
                }

                public String getDate_added() {
                    return date_added;
                }


                protected reviewsdetailsModel(Parcel in) {
                    this.author=in.readString();
                    this.text=in.readString();
                    this.date_added=in.readString();
                    this.rating=in.readInt();

                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.author);
                    dest.writeString(this.text);
                    dest.writeString(this.date_added);
                    dest.writeInt(this.rating);

                }

                @Override
                public int describeContents() {
                    return 0;
                }

                public static final Creator<reviewsdetailsModel> CREATOR = new Creator<reviewsdetailsModel>() {
                    @Override
                    public reviewsdetailsModel createFromParcel(Parcel in) {
                        return new reviewsdetailsModel(in);
                    }

                    @Override
                    public reviewsdetailsModel[] newArray(int size) {
                        return new reviewsdetailsModel[size];
                    }
                };
            }
        }

    }
}
