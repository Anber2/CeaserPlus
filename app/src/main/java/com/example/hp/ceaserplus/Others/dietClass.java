package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/10/2017.
 */

public class dietClass implements Parcelable {

    private String mealName;
    private String mealCalories;

    public dietClass(String mealName, String mealCalories) {
        this.setMealName(mealName);
        this.setMealCalories(mealCalories);
    }

    protected dietClass(Parcel in)
    {
        setMealName(in.readString());
        setMealCalories(in.readString());
    }

    public static final Creator<dietClass> CREATOR = new Creator<dietClass>() {
        @Override
        public dietClass createFromParcel(Parcel in) {
            return new dietClass(in);
        }

        @Override
        public dietClass[] newArray(int size) {
            return new dietClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getMealName());
        dest.writeString(getMealCalories());
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealCalories() {
        return mealCalories;
    }

    public void setMealCalories(String mealCalories) {
        this.mealCalories = mealCalories;
    }
}
