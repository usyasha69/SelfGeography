package com.example.pk.selfgeography.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInformationModel implements Parcelable{
    public String name;
    public String password;
    public String country;

    public UserInformationModel() {
    }

    public UserInformationModel(String name, String password, String country) {
        this.name = name;
        this.password = password;
        this.country = country;
    }

    protected UserInformationModel(Parcel in) {
        name = in.readString();
        password = in.readString();
        country = in.readString();
    }

    public static final Creator<UserInformationModel> CREATOR = new Creator<UserInformationModel>() {
        @Override
        public UserInformationModel createFromParcel(Parcel in) {
            return new UserInformationModel(in);
        }

        @Override
        public UserInformationModel[] newArray(int size) {
            return new UserInformationModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(password);
        parcel.writeString(country);
    }

    @Override
    public String toString() {
        return "UserInformationModel{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
