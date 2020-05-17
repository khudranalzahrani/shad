package com.example.lap_shop.shahad_3asal.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LAP_SHOP on 24/04/2017.
 */

public class SliderModel implements Parcelable {
    /*
    * {
  "success": true,
  "data": [
    {
      "module_id": "27",
      "name": "Home Page",
      "banner_id": "7",
      "width": "1140",
      "height": "380",
      "status": "1",
      "banners": [
        {
          "title": "iPhone 6",
          "link": "index.php?route=product/product&amp;path=57&amp;product_id=49",
          "image": "http://newapi2.opencart-api.com/image/cache/catalog/demo/banners/iPhone6-1140x380.jpg"
        },
        {
          "title": "MacBookAir",
          "link": "",
          "image": "http://newapi2.opencart-api.com/image/cache/catalog/demo/banners/MacBookAir-1140x380.jpg"
        }
      ]
    }
  ]
}
    *
    * */
    private Boolean success;
    @SerializedName("data")
    private List<SliderModel.DataModel> dataModelList;

    public void setDataModelList(List<DataModel> dataModelList) {
        this.dataModelList = dataModelList;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public List<DataModel> getDataModelList() {
        return dataModelList;
    }

    protected SliderModel(Parcel in) {
        this.success = in.readByte() != 0;
        this.dataModelList = new ArrayList<DataModel>();
        in.readList(this.dataModelList, List.class.getClassLoader());
    }

    public static final Creator<SliderModel> CREATOR = new Creator<SliderModel>() {
        @Override
        public SliderModel createFromParcel(Parcel in) {
            return new SliderModel(in);
        }

        @Override
        public SliderModel[] newArray(int size) {
            return new SliderModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.success ? 1 : 0));
        dest.writeList(this.dataModelList);
    }

    public static class DataModel implements Parcelable {
        /*
        *  "data": [
    {
      "module_id": "27",
      "name": "Home Page",
      "banner_id": "7",
      "width": "1140",
      "height": "380",
      "status": "1",
      "banners": [
        {
          "title": "iPhone 6",
          "link": "index.php?route=product/product&amp;path=57&amp;product_id=49",
          "image": "http://newapi2.opencart-api.com/image/cache/catalog/demo/banners/iPhone6-1140x380.jpg"
        },
        {
          "title": "MacBookAir",
          "link": "",
          "image": "http://newapi2.opencart-api.com/image/cache/catalog/demo/banners/MacBookAir-1140x380.jpg"
        }
      ]
    }*/
        private String module_id, name, banner_id, width, height, status;
        @SerializedName("banners")
        private List<BannersModel> bannersModelList;

        public List<BannersModel> getBannersModelList() {
            return bannersModelList;
        }

        public String getBanner_id() {
            return banner_id;
        }

        public String getHeight() {
            return height;
        }

        public String getModule_id() {
            return module_id;
        }

        public String getName() {
            return name;
        }

        public String getStatus() {
            return status;
        }

        public String getWidth() {
            return width;
        }

        public void setBanner_id(String banner_id) {
            this.banner_id = banner_id;
        }

        public void setBannersModelList(List<BannersModel> bannersModelList) {
            this.bannersModelList = bannersModelList;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public void setModule_id(String module_id) {
            this.module_id = module_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        protected DataModel(Parcel in) {
            this.module_id = in.readString();
            this.name = in.readString();
            this.banner_id = in.readString();
            this.width = in.readString();
            this.height = in.readString();
            this.status = in.readString();

            this.bannersModelList = new ArrayList<BannersModel>();
            in.readList(this.bannersModelList, List.class.getClassLoader());
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
            dest.writeString(this.module_id);
            dest.writeString(this.name);
            dest.writeString(this.banner_id);
            dest.writeString(this.width);
            dest.writeString(this.height);
            dest.writeString(this.status);
            dest.writeList(this.bannersModelList);

        }


        public static class BannersModel implements Parcelable {
            /*
            * "title": "iPhone 6",
          "link": "index.php?route=product/product&amp;path=57&amp;product_id=49",
          "image": "http://newapi2.opencart-api.com/image/cache/catalog/demo/banners/iPhone6-1140x380.jpg"*/
            private String title, link, image;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            protected BannersModel(Parcel in) {
                this.title=in.readString();
                this.link=in.readString();
                this.image=in.readString();

            }

            public static final Creator<BannersModel> CREATOR = new Creator<BannersModel>() {
                @Override
                public BannersModel createFromParcel(Parcel in) {
                    return new BannersModel(in);
                }

                @Override
                public BannersModel[] newArray(int size) {
                    return new BannersModel[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.title);
                dest.writeString(this.link);
                dest.writeString(this.image);
            }
        }
    }
}
