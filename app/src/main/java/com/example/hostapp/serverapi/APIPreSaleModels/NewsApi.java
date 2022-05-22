package com.example.hostapp.serverapi.APIPreSaleModels;

import com.example.hostapp.serverapi.APINews.RiaNewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsApi {
    @GET("/api/RiaNewsApi")
    Call<List<RiaNewsModel>> getRiaNews();

    @GET("/api/RiaNewsApi/{id}")
    Call<List<RiaNewsModel>> getRiaNewsById(@Path("id") String id);

}
