package com.example.android.intergrupopruebatecnica.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity
public class Prospect implements IProspect, Parcelable {

    private @NonNull
    @PrimaryKey(autoGenerate = true)
    int id;
    private String prospectId;
    private String name;
    private String surname;
    private String telephone;
    private Integer statusCd;

    public Prospect(){

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getProspectId() {
        return prospectId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getTelephone() {
        return telephone;
    }

    @Override
    public Integer getStatusCd() {
        return statusCd;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setProspectId(String prospectId) {
        this.prospectId = prospectId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setStatusCd(Integer statusCd) {
        this.statusCd = statusCd;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.prospectId);
        dest.writeString(this.name);
        dest.writeString(this.surname);
        dest.writeString(this.telephone);
        dest.writeValue(this.statusCd);
    }

    protected Prospect(Parcel in) {
        this.id = in.readInt();
        this.prospectId = in.readString();
        this.name = in.readString();
        this.surname = in.readString();
        this.telephone = in.readString();
        this.statusCd = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Prospect> CREATOR = new Creator<Prospect>() {
        @Override
        public Prospect createFromParcel(Parcel source) {
            return new Prospect(source);
        }

        @Override
        public Prospect[] newArray(int size) {
            return new Prospect[size];
        }
    };
}
