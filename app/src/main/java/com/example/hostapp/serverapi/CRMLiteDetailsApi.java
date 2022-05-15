package com.example.hostapp.serverapi;

import com.example.hostapp.serverapi.APIPreSaleDetailsModels.PreSaleDetailsModel;
import com.example.hostapp.serverapi.APIPreSaleDetailsModels.RegionsModel;
import com.example.hostapp.serverapi.APIPreSaleDetailsModels.StatusesModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CRMLiteDetailsApi {
    @GET("/PreSales/PreSaleRegions")
    Call<List<RegionsModel>> getRegions();

    @GET("/PreSales/PreSaleStatuses")
    Call<List<StatusesModel>> getStatuses();

    @GET("/PreSales/ForPreSalesTable/{id}")
    Call<List<PreSaleDetailsModel>> getDetailsForPreSale(@Path("id") String id);

    @PUT("/PreSales/EditPreSale/{id}")
    Call<ResponseBody> putEditDetails(@Path("id") String id, @Body PreSaleDetailsModel preSaleDetailsModel);
}
