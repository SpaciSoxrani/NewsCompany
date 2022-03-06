package com.example.hostapp.serverapi;

import com.example.hostapp.serverapi.PreSaleEntryModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CRMLiteApi {
    @GET("/PreSales/ForGroupsTable")
    Call<List<PreSaleEntryModel>> getData();
}
