package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/10/2017.
 */

public class MembershipBenefit implements Parcelable {
    private String membershipBenefitName;
    private String membershipBenefitId;

    public MembershipBenefit(String membershipBenefitName, String membershipBenefitId) {
        this.setMembershipBenefitName(membershipBenefitName);
        this.setMembershipBenefitId(membershipBenefitId);
    }

    protected MembershipBenefit(Parcel in) {
        membershipBenefitName = in.readString();
        membershipBenefitId = in.readString();
    }

    public static final Creator<MembershipBenefit> CREATOR = new Creator<MembershipBenefit>() {
        @Override
        public MembershipBenefit createFromParcel(Parcel in) {
            return new MembershipBenefit(in);
        }

        @Override
        public MembershipBenefit[] newArray(int size) {
            return new MembershipBenefit[size];
        }
    };

    public String getMembershipBenefitName() {
        return membershipBenefitName;
    }

    public void setMembershipBenefitName(String membershipBenefitName) {
        this.membershipBenefitName = membershipBenefitName;
    }

    public String getMembershipBenefitId() {
        return membershipBenefitId;
    }

    public void setMembershipBenefitId(String membershipBenefitId) {
        this.membershipBenefitId = membershipBenefitId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(membershipBenefitName);
        dest.writeString(membershipBenefitId);
    }
}
