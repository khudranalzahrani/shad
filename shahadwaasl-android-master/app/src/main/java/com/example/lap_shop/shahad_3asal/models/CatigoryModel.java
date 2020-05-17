package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP_SHOP on 25/04/2017.
 */

public class CatigoryModel implements Parcelable {
    /* {
  "success": true,
    "data": []*/
    private Boolean success;

    @SerializedName("data")
    private List<CatigoryModel.DataModel> dataModel;

    public Boolean getSuccess() {
        return success;
    }

    public void setDataModel(List<CatigoryModel.DataModel> dataModel) {
        this.dataModel = dataModel;
    }

    public List<CatigoryModel.DataModel> getDataModel() {
        return dataModel;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    protected CatigoryModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.dataModel = new ArrayList<CatigoryModel.DataModel>();
        in.readList(this.dataModel, List.class.getClassLoader());

    }

    public static final Creator<CatigoryModel> CREATOR = new Creator<CatigoryModel>() {
        @Override
        public CatigoryModel createFromParcel(Parcel in) {
            return new CatigoryModel(in);
        }

        @Override
        public CatigoryModel[] newArray(int size) {
            return new CatigoryModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
        dest.writeList(this.dataModel);
    }

    public static class DataModel implements Parcelable {
        /*
        *  {
              "category_id": "20",
              "parent_id": "0",
              "name": "Desktops",
              "image": "http://newapi2.opencart-api.com/image/cache/catalog/demo/compaq_presario-500x500.jpg",
              "original_image": "http://newapi2.opencart-api.com/image/catalog/demo/compaq_presario.jpg",
              "filters": {
                "filter_groups": []
              }, "categories": [{},{}]
        *
        * */
        private String category_id, parent_id, name, image, original_image;
        @SerializedName("filters")
        private filtersModel filtersModelList;
        @SerializedName("categories")
        private List<categoriesModel> categoriesModelList;

        public void setCategoriesModelList(List<categoriesModel> categoriesModelList) {
            this.categoriesModelList = categoriesModelList;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public void setFiltersModelList(filtersModel filtersModelList) {
            this.filtersModelList = filtersModelList;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setOriginal_image(String original_image) {
            this.original_image = original_image;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public static Creator<DataModel> getCREATOR() {
            return CREATOR;
        }

        public List<categoriesModel> getCategoriesModelList() {
            return categoriesModelList;
        }

        public filtersModel getFiltersModelList() {
            return filtersModelList;
        }

        public String getCategory_id() {
            return category_id;
        }

        public String getImage() {
            return image;
        }

        public String getName() {
            return name;
        }

        public String getOriginal_image() {
            return original_image;
        }

        public String getParent_id() {
            return parent_id;
        }

        protected DataModel(Parcel in) {
            this.category_id = in.readString();
            this.parent_id = in.readString();
            this.name = in.readString();
            this.image = in.readString();
            this.original_image = in.readString();
            this.filtersModelList = in.readParcelable(filtersModel.class.getClassLoader());

            this.categoriesModelList = new ArrayList<categoriesModel>();
            in.readList(this.categoriesModelList, List.class.getClassLoader());
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
            dest.writeString(this.category_id);
            dest.writeString(this.parent_id);
            dest.writeString(this.name);
            dest.writeString(this.image);
            dest.writeString(this.original_image);
            dest.writeParcelable(this.filtersModelList, flags);
            dest.writeList(this.categoriesModelList);
        }



            public static class categoriesModel implements Parcelable {
                /* {
                          "category_id": "64",
                          "parent_id": "20",
                          "name": "DEMO FOR SAM",
                          "image": null,
                          "original_image": "http://newapi2.opencart-api.com/image/no_image.jpg",
                          "filters": {
                            "filter_groups": []
                          }*/
                private String category_id, parent_id, name, image, original_image;

                public String getParent_id() {
                    return parent_id;
                }

                public String getOriginal_image() {
                    return original_image;
                }

                public String getName() {
                    return name;
                }

                public String getImage() {
                    return image;
                }

                public String getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(String category_id) {
                    this.category_id = category_id;
                }

                public void setOriginal_image(String original_image) {
                    this.original_image = original_image;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public void setParent_id(String parent_id) {
                    this.parent_id = parent_id;
                }

                protected categoriesModel(Parcel in) {
                    this.category_id = in.readString();
                    this.parent_id = in.readString();
                    this.name = in.readString();
                    this.image = in.readString();
                    this.original_image = in.readString();
                }

                public static final Creator<categoriesModel> CREATOR = new Creator<categoriesModel>() {
                    @Override
                    public categoriesModel createFromParcel(Parcel in) {
                        return new categoriesModel(in);
                    }

                    @Override
                    public categoriesModel[] newArray(int size) {
                        return new categoriesModel[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.category_id);
                    dest.writeString(this.parent_id);
                    dest.writeString(this.name);
                    dest.writeString(this.image);
                    dest.writeString(this.original_image);
                }
            }


        public static class filtersModel implements Parcelable {

            protected filtersModel(Parcel in) {
            }

            public static final Creator<filtersModel> CREATOR = new Creator<filtersModel>() {
                @Override
                public filtersModel createFromParcel(Parcel in) {
                    return new filtersModel(in);
                }

                @Override
                public filtersModel[] newArray(int size) {
                    return new filtersModel[size];
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
