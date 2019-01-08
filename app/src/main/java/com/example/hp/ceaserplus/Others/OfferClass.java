package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/19/2017.
 */

public class OfferClass implements Parcelable{

    private String offerId;
    private String title;
    private String imageUrl;
    private String date;
    private String shortDesc;
    private String description;

    public OfferClass(String offerId, String title, String imageUrl, String date, String shortDesc, String description) {
        this.setOfferId(offerId);
        setTitle(title);
        setImageUrl(imageUrl);
        setDate(date);
        setShortDesc(shortDesc);
        setDescription(description);
    }

    protected OfferClass(Parcel in) {
        offerId = in.readString();
        title = in.readString();
        imageUrl = in.readString();
        date = in.readString();
        shortDesc = in.readString();
        description = in.readString();
    }

    public static final Creator<OfferClass> CREATOR = new Creator<OfferClass>() {
        @Override
        public OfferClass createFromParcel(Parcel in) {
            return new OfferClass(in);
        }

        @Override
        public OfferClass[] newArray(int size) {
            return new OfferClass[size];
        }
    };

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(offerId);
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeString(date);
        dest.writeString(shortDesc);
        dest.writeString(description);
    }
}
