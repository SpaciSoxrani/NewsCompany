package com.example.hostapp.serverapi.APIModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartmentModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("parentDepartment")
    @Expose
    private Object parentDepartment;
    @SerializedName("parentDepartmentId")
    @Expose
    private Object parentDepartmentId;
    @SerializedName("manager")
    @Expose
    private Object manager;
    @SerializedName("managerId")
    @Expose
    private Object managerId;
    @SerializedName("managerFromAD")
    @Expose
    private Object managerFromAD;
    @SerializedName("canSell")
    @Expose
    private Boolean canSell;
    @SerializedName("canProduct")
    @Expose
    private Boolean canProduct;
    @SerializedName("canExecute")
    @Expose
    private Boolean canExecute;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("childDepartments")
    @Expose
    private Object childDepartments;
    @SerializedName("users")
    @Expose
    private Object users;

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

    public Object getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Object parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public Object getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(Object parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public Object getManager() {
        return manager;
    }

    public void setManager(Object manager) {
        this.manager = manager;
    }

    public Object getManagerId() {
        return managerId;
    }

    public void setManagerId(Object managerId) {
        this.managerId = managerId;
    }

    public Object getManagerFromAD() {
        return managerFromAD;
    }

    public void setManagerFromAD(Object managerFromAD) {
        this.managerFromAD = managerFromAD;
    }

    public Boolean getCanSell() {
        return canSell;
    }

    public void setCanSell(Boolean canSell) {
        this.canSell = canSell;
    }

    public Boolean getCanProduct() {
        return canProduct;
    }

    public void setCanProduct(Boolean canProduct) {
        this.canProduct = canProduct;
    }

    public Boolean getCanExecute() {
        return canExecute;
    }

    public void setCanExecute(Boolean canExecute) {
        this.canExecute = canExecute;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Object getChildDepartments() {
        return childDepartments;
    }

    public void setChildDepartments(Object childDepartments) {
        this.childDepartments = childDepartments;
    }

    public Object getUsers() {
        return users;
    }

    public void setUsers(Object users) {
        this.users = users;
    }

}
