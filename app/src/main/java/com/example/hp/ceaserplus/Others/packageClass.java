package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/13/2017.
 */

public class packageClass  implements Parcelable{

    private String Id;
    private String Packages;
    private String ActiveFrom;
    private String ActiveTo;
    private String MembershipFee;
    private String PackageMode;

    protected packageClass(Parcel in) {
        Id = in.readString();
        Packages = in.readString();
        ActiveFrom = in.readString();
        ActiveTo = in.readString();
        MembershipFee = in.readString();
        PackageMode = in.readString();
    }

    public packageClass() {

    }

    public static final Creator<packageClass> CREATOR = new Creator<packageClass>() {
        @Override
        public packageClass createFromParcel(Parcel in) {
            return new packageClass(in);
        }

        @Override
        public packageClass[] newArray(int size) {
            return new packageClass[size];
        }
    };

    public String getId() {
        return Id;
    }

    public String getPackages() {
        return Packages;
    }

    public String getActiveFrom() {
        return ActiveFrom;
    }

    public String getActiveTo() {
        return ActiveTo;
    }

    public String getMembershipFee() {
        return MembershipFee;
    }

    public String getPackageMode() {
        return PackageMode;
    }

    public packageClass(String id, String packages, String activeFrom, String activeTo, String membershipFee, String packageMode) {
        Id = id;
        Packages = packages;
        ActiveFrom = activeFrom;
        ActiveTo = activeTo;
        MembershipFee = membershipFee;
        PackageMode = packageMode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Packages);
        dest.writeString(ActiveFrom);
        dest.writeString(ActiveTo);
        dest.writeString(MembershipFee);
        dest.writeString(PackageMode);
    }
}
