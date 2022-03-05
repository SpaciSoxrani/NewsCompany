package com.example.hostapp.serverapi;

import com.example.hostapp.preSale.NewMailing;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CRMLiteApi {
    @GET("/PreSales/ForGroupsTable")
    Call<List<PreSaleEntryModel>> getData(@Query("name") String resourceName, int i);
}
