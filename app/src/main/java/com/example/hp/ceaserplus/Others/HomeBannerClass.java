package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/17/2017.
 */

public class HomeBannerClass implements Parcelable {

    public HomeBannerClass(String imgURL) {
        this.setImgURL(imgURL);
    }

    private String imgURL;

    protected HomeBannerClass(Parcel in) {
        imgURL = in.readString();
    }

    public static final Creator<HomeBannerClass> CREATOR = new Creator<HomeBannerClass>() {
        @Override
        public HomeBannerClass createFromParcel(Parcel in) {
            return new HomeBannerClass(in);
        }

        @Override
        public HomeBannerClass[] newArray(int size) {
            return new HomeBannerClass[size];
        }
    };

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imgURL);
    }
}
