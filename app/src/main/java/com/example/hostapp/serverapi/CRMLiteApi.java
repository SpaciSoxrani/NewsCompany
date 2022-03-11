package com.example.hostapp.serverapi;

import com.example.hostapp.serverapi.APIModels.DepartmentModel;
import com.example.hostapp.serverapi.APIModels.MainDepartmentsModel;
import com.example.hostapp.serverapi.APIModels.PostPreSaleGroup;
import com.example.hostapp.serverapi.APIModels.PreSaleEntryModel;
import com.example.hostapp.serverapi.APIModels.PreSaleGroupModel;
import com.example.hostapp.serverapi.APIModels.PreSaleGroupStatusesModel;

import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CRMLiteApi {
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
}
