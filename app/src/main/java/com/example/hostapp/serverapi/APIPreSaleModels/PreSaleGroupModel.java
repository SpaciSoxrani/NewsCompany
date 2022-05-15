package com.example.hostapp.serverapi.APIPreSaleModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreSaleGroupModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("statusId")
    @Expose
    private String statusId;
    @SerializedName("status")
    @Expose
    private StatusModel status;
    @SerializedName("departmentId")
    @Expose
    private String departmentId;
    @SerializedName("department")
    @Expose
    private DepartmentModel department;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("changedDate")
    @Expose
    private String changedDate;
    @SerializedName("isVisible")
    @Expose
    private Boolean isVisible;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public DepartmentModel getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentModel department) {
        this.department = department;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getChangedDate() {
        return changedDate;
    }

    public void setChangedDate(String changedDate) {
        this.changedDate = changedDate;
    }

    public Boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }
}
