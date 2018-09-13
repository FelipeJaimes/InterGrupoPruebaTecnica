package com.example.android.intergrupopruebatecnica.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity
public class User implements IUser, Parcelable {


    private @NonNull
    @PrimaryKey(autoGenerate = true)
    int userId;
    private String email;
    private String password;
    private String token;

    public User() {
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getToken() {
        return token;
    }

    public void setUserId(@NonNull int userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.token);
    }

    protected User(Parcel in) {
        this.userId = in.readInt();
        this.email = in.readString();
        this.password = in.readString();
        this.token = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}