package com.example.hp.ceaserplus.Others;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP on 4/9/2017.
 */

public class AppointmentsClass implements Parcelable {

    private String appointmentId;
    private String appointmentStartDate;
    private String appointmentEndDate;
    private String appointmentDetails;
    private String appointmentStatus;
    private boolean appointmentSuccess;


    public AppointmentsClass(String appointmentId, String appointmentStartDate, String appointmentEndDate,
                             String appointmentDetails, String appointmentStatus, boolean appointmentSuccess) {
        this.setAppointmentId(appointmentId);
        this.setAppointmentStartDate(appointmentStartDate);
        this.setAppointmentEndDate(appointmentEndDate);
        this.setAppointmentDetails(appointmentDetails);
        this.setAppointmentStatus(appointmentStatus);
        this.setAppointmentSuccess(appointmentSuccess);
    }


    protected AppointmentsClass(Parcel in) {
        appointmentId = in.readString();
        appointmentStartDate = in.readString();
        appointmentEndDate = in.readString();
        appointmentDetails = in.readString();
        appointmentStatus = in.readString();
        appointmentSuccess = in.readByte() != 0;
    }

    public static final Creator<AppointmentsClass> CREATOR = new Creator<AppointmentsClass>() {
        @Override
        public AppointmentsClass createFromParcel(Parcel in) {
            return new AppointmentsClass(in);
        }

        @Override
        public AppointmentsClass[] newArray(int size) {
            return new AppointmentsClass[size];
        }
    };

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentStartDate() {
        return appointmentStartDate;
    }

    public void setAppointmentStartDate(String appointmentStartDate) {
        this.appointmentStartDate = appointmentStartDate;
    }

    public String getAppointmentEndDate() {
        return appointmentEndDate;
    }

    public void setAppointmentEndDate(String appointmentEndDate) {
        this.appointmentEndDate = appointmentEndDate;
    }

    public String getAppointmentDetails() {
        return appointmentDetails;
    }

    public void setAppointmentDetails(String appointmentDetails) {
        this.appointmentDetails = appointmentDetails;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public boolean isAppointmentSuccess() {
        return appointmentSuccess;
    }

    public void setAppointmentSuccess(boolean appointmentSuccess) {
        this.appointmentSuccess = appointmentSuccess;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(appointmentId);
        dest.writeString(appointmentStartDate);
        dest.writeString(appointmentEndDate);
        dest.writeString(appointmentDetails);
        dest.writeString(appointmentStatus);
        dest.writeByte((byte) (appointmentSuccess ? 1 : 0));
    }
}
