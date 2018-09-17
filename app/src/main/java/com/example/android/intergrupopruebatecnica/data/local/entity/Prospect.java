package com.example.android.intergrupopruebatecnica.data.local.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Prospect  {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String objectId;
    private String sid;
    private String name;
    private String surname;
    private String telephone;
    private String schProspectIdentification;
    private String address;
    private String createdAt;
    private String updatedAt;
    private Integer statusCd;
    private String zoneCode;
    private String neighborhoodCode;
    private String cityCode;
    private String sectionCode;
    private Integer roleId;
    private String observation;
    private Boolean disable;
    private Boolean visited;
    private Boolean callcenter;
    private Boolean acceptSearch;
    private String campaignCode;


    public Prospect(){
    }


    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSchProspectIdentification() {
        return schProspectIdentification;
    }

    public void setSchProspectIdentification(String schProspectIdentification) {
        this.schProspectIdentification = schProspectIdentification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(Integer statusCd) {
        this.statusCd = statusCd;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getNeighborhoodCode() {
        return neighborhoodCode;
    }

    public void setNeighborhoodCode(String neighborhoodCode) {
        this.neighborhoodCode = neighborhoodCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public Boolean getCallcenter() {
        return callcenter;
    }

    public void setCallcenter(Boolean callcenter) {
        this.callcenter = callcenter;
    }

    public Boolean getAcceptSearch() {
        return acceptSearch;
    }

    public void setAcceptSearch(Boolean acceptSearch) {
        this.acceptSearch = acceptSearch;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

}
