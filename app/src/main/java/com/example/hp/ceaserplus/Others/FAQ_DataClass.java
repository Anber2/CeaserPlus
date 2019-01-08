package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/9/2017.
 */

public class FAQ_DataClass implements Parcelable
{
    private String question;
    private String answer;

    public FAQ_DataClass(String question, String answer) {
        this.setQuestion(question);
        this.setAnswer(answer);
    }

    protected FAQ_DataClass(Parcel in) {
        question = in.readString();
        answer = in.readString();
    }

    public static final Creator<FAQ_DataClass> CREATOR = new Creator<FAQ_DataClass>() {
        @Override
        public FAQ_DataClass createFromParcel(Parcel in) {
            return new FAQ_DataClass(in);
        }

        @Override
        public FAQ_DataClass[] newArray(int size) {
            return new FAQ_DataClass[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answer);
    }
}
