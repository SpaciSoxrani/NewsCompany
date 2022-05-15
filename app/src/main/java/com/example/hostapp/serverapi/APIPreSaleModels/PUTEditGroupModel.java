package com.example.hostapp.serverapi.APIPreSaleModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PUTEditGroupModel {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("statusId")
    @Expose
    private String statusId;
    @SerializedName("departmentId")
    @Expose
    private String departmentId;
    @SerializedName("id")
    @Expose
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
