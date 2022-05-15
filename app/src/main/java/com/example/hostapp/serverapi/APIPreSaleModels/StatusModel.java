package com.example.hostapp.serverapi.APIPreSaleModels;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("preSaleGroups")
    @Expose
    private List<Object> preSaleGroups = null;

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

    public List<Object> getPreSaleGroups() {
        return preSaleGroups;
    }

    public void setPreSaleGroups(List<Object> preSaleGroups) {
        this.preSaleGroups = preSaleGroups;
    }
}
