package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/6/2017.
 */

public class PhotoGalleryDetails implements Parcelable {

    private String photoGalleryDetailsId;
    private String photoGalleryDetailsTitle;
    private String photoGalleryDetailsImage;
    private String photoGalleryDetailsOrder;

    public PhotoGalleryDetails(String photoGalleryDetailsId, String photoGalleryDetailsTitle, String photoGalleryDetailsImage, String photoGalleryDetailsOrder) {
        this.setPhotoGalleryDetailsId(photoGalleryDetailsId);
        this.setPhotoGalleryDetailsTitle(photoGalleryDetailsTitle);
        this.setPhotoGalleryDetailsImage(photoGalleryDetailsImage);
        this.setPhotoGalleryDetailsOrder(photoGalleryDetailsOrder);
    }


    protected PhotoGalleryDetails(Parcel in) {
        photoGalleryDetailsId = in.readString();
        photoGalleryDetailsTitle = in.readString();
        photoGalleryDetailsImage = in.readString();
        photoGalleryDetailsOrder = in.readString();
    }

    public static final Creator<PhotoGalleryDetails> CREATOR = new Creator<PhotoGalleryDetails>() {
        @Override
        public PhotoGalleryDetails createFromParcel(Parcel in) {
            return new PhotoGalleryDetails(in);
        }

        @Override
        public PhotoGalleryDetails[] newArray(int size) {
            return new PhotoGalleryDetails[size];
        }
    };

    public String getPhotoGalleryDetailsId() {
        return photoGalleryDetailsId;
    }

    public void setPhotoGalleryDetailsId(String photoGalleryDetailsId) {
        this.photoGalleryDetailsId = photoGalleryDetailsId;
    }

    public String getPhotoGalleryDetailsTitle() {
        return photoGalleryDetailsTitle;
    }

    public void setPhotoGalleryDetailsTitle(String photoGalleryDetailsTitle) {
        this.photoGalleryDetailsTitle = photoGalleryDetailsTitle;
    }

    public String getPhotoGalleryDetailsImage() {
        return photoGalleryDetailsImage;
    }

    public void setPhotoGalleryDetailsImage(String photoGalleryDetailsImage) {
        this.photoGalleryDetailsImage = photoGalleryDetailsImage;
    }

    public String getPhotoGalleryDetailsOrder() {
        return photoGalleryDetailsOrder;
    }

    public void setPhotoGalleryDetailsOrder(String photoGalleryDetailsOrder) {
        this.photoGalleryDetailsOrder = photoGalleryDetailsOrder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photoGalleryDetailsId);
        dest.writeString(photoGalleryDetailsTitle);
        dest.writeString(photoGalleryDetailsImage);
        dest.writeString(photoGalleryDetailsOrder);
    }
}
