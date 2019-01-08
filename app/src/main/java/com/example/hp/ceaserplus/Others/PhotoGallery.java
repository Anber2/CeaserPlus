package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/6/2017.
 */

public class PhotoGallery implements Parcelable {
    private String photoGalleryId;
    private String photoGalleryTitle;
    private String photoGalleryPhoto;

    public PhotoGallery(String photoGalleryId, String photoGalleryTitle, String photoGalleryPhoto) {
        this.setPhotoGalleryId(photoGalleryId);
        this.setPhotoGalleryTitle(photoGalleryTitle);
        this.setPhotoGalleryPhoto(photoGalleryPhoto);
    }


    protected PhotoGallery(Parcel in) {
        photoGalleryId = in.readString();
        photoGalleryTitle = in.readString();
        photoGalleryPhoto = in.readString();
    }

    public static final Creator<PhotoGallery> CREATOR = new Creator<PhotoGallery>() {
        @Override
        public PhotoGallery createFromParcel(Parcel in) {
            return new PhotoGallery(in);
        }

        @Override
        public PhotoGallery[] newArray(int size) {
            return new PhotoGallery[size];
        }
    };

    public String getPhotoGalleryId() {
        return photoGalleryId;
    }

    public void setPhotoGalleryId(String photoGalleryId) {
        this.photoGalleryId = photoGalleryId;
    }

    public String getPhotoGalleryTitle() {
        return photoGalleryTitle;
    }

    public void setPhotoGalleryTitle(String photoGalleryTitle) {
        this.photoGalleryTitle = photoGalleryTitle;
    }

    public String getPhotoGalleryPhoto() {
        return photoGalleryPhoto;
    }

    public void setPhotoGalleryPhoto(String photoGalleryPhoto) {
        this.photoGalleryPhoto = photoGalleryPhoto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photoGalleryId);
        dest.writeString(photoGalleryTitle);
        dest.writeString(photoGalleryPhoto);
    }
}
