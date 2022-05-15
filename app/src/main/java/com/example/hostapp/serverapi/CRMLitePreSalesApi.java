package com.example.hostapp.serverapi;

import com.example.hostapp.serverapi.APIPreSaleModels.MainDepartmentsModel;
import com.example.hostapp.serverapi.APIPreSaleModels.PUTEditGroupModel;
import com.example.hostapp.serverapi.APIPreSaleModels.PostPreSaleGroup;
import com.example.hostapp.serverapi.APIPreSaleModels.PreSaleEntryModel;
import com.example.hostapp.serverapi.APIPreSaleModels.PreSaleGroupModel;
import com.example.hostapp.serverapi.APIPreSaleModels.PreSaleGroupStatusesModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CRMLitePreSalesApi {
    @GET("/PreSales/ForGroupsTable")
    Call<List<PreSaleEntryModel>> getData();

    @GET("/Departments/MainDepartments")
    Call<List<MainDepartmentsModel>> getMainDepartments();

    @GET("/PreSales/PreSaleGroupStatuses")
    Call<List<PreSaleGroupStatusesModel>> getPresaleGroupStatuses();

    @DELETE("/PreSales/DeletePreSaleGroup/{id}")
    Call<PreSaleGroupModel> deletePreSaleGroup(@Path("id") String preSaleGroupId);

    @POST("/PreSales/CreatePreSaleGroup")
    Call<PostPreSaleGroup> createPost(@Body PostPreSaleGroup postPreSaleGroup);

    @PUT("/PreSales/EditPreSaleGroup/{id}")
    Call<ResponseBody> editPreSaleGroup(@Path("id") String id, @Body PUTEditGroupModel putEditGroupModel);
}
