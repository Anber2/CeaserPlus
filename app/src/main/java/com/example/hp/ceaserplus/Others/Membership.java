package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/10/2017.
 */

public class Membership implements Parcelable {

    private String membershipName;
    private String membershipId;

    public Membership(String membershipName, String membershipId) {
        this.setMembershipName(membershipName);
        this.setMembershipId(membershipId);
    }

    protected Membership(Parcel in) {
        membershipName = in.readString();
        membershipId = in.readString();
    }

    public static final Creator<Membership> CREATOR = new Creator<Membership>() {
        @Override
        public Membership createFromParcel(Parcel in) {
            return new Membership(in);
        }

        @Override
        public Membership[] newArray(int size) {
            return new Membership[size];
        }
    };

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(membershipName);
        dest.writeString(membershipId);
    }
}
