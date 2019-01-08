package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/9/2017.
 */

public class ourClincDataClass implements Parcelable {

    private  String imgLink;

    public ourClincDataClass(String imgLink, String title, String workingHour, String address, String specialistName, String contactDetails, String latiTitud, String longTitud, String place) {
        this.setImgLink(imgLink);
        this.setTitle(title);
        this.setWorkingHour(workingHour);
        this.setAddress(address);
        this.setSpecialistName(specialistName);
        this.setContactDetails(contactDetails);
        this.setLatiTitud(latiTitud);
        this.setLongTitud(longTitud);
        this.setPlace(place);


    }

    private  String title;
    private  String workingHour;
    private  String address;
    private  String specialistName;
    private  String contactDetails;
    private  String latiTitud;
    private  String longTitud;
    private  String place;



    protected ourClincDataClass(Parcel in) {
        imgLink = in.readString();
        title = in.readString();
        workingHour = in.readString();
        address = in.readString();
        specialistName = in.readString();
        contactDetails = in.readString();
        latiTitud = in.readString();
        longTitud = in.readString();
    }

    public static final Creator<ourClincDataClass> CREATOR = new Creator<ourClincDataClass>() {
        @Override
        public ourClincDataClass createFromParcel(Parcel in) {
            return new ourClincDataClass(in);
        }

        @Override
        public ourClincDataClass[] newArray(int size) {
            return new ourClincDataClass[size];
        }
    };

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(String workingHour) {
        this.workingHour = workingHour;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getLatiTitud() {
        return latiTitud;
    }

    public void setLatiTitud(String latiTitud) {
        this.latiTitud = latiTitud;
    }

    public String getLongTitud() {
        return longTitud;
    }

    public void setLongTitud(String longTitud) {
        this.longTitud = longTitud;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imgLink);
        dest.writeString(title);
        dest.writeString(workingHour);
        dest.writeString(address);
        dest.writeString(specialistName);
        dest.writeString(contactDetails);
        dest.writeString(latiTitud);
        dest.writeString(longTitud);
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
