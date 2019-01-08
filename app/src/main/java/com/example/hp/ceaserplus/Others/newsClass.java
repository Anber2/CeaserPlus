package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/9/2017.
 */

public class newsClass implements Parcelable {
    private String title;
    private String date;
    private String teaser;
    private String description;
    private String thumbnail;
    private String moreDetailsLink;
    private String neverExpired;

    public newsClass(){

    }

    public newsClass(String title, String date, String teaser, String description, String thumbnail, String moreDetailsLink, String neverExpired) {
        this.setTitle(title);
        this.setDate(date);
        this.setTeaser(teaser);
        this.setDescription(description);
        this.setThumbnail(thumbnail);
        this.setMoreDetailsLink(moreDetailsLink);
        this.setNeverExpired(neverExpired);
    }


    protected newsClass(Parcel in) {
        title = in.readString();
        date = in.readString();
        teaser = in.readString();
        description = in.readString();
        thumbnail = in.readString();
        moreDetailsLink = in.readString();
        neverExpired = in.readString();
    }

    public static final Creator<newsClass> CREATOR = new Creator<newsClass>() {
        @Override
        public newsClass createFromParcel(Parcel in) {
            return new newsClass(in);
        }

        @Override
        public newsClass[] newArray(int size) {
            return new newsClass[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getMoreDetailsLink() {
        return moreDetailsLink;
    }

    public void setMoreDetailsLink(String moreDetailsLink) {
        this.moreDetailsLink = moreDetailsLink;
    }

    public String getNeverExpired() {
        return neverExpired;
    }

    public void setNeverExpired(String neverExpired) {
        this.neverExpired = neverExpired;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(teaser);
        dest.writeString(description);
        dest.writeString(thumbnail);
        dest.writeString(moreDetailsLink);
        dest.writeString(neverExpired);
    }
}
