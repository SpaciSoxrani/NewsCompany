package com.example.hostapp.serverapi;

import android.app.Application;

import com.example.hostapp.serverapi.APIPreSaleModels.NewsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static CRMLitePreSalesApi crmLitePreSalesApi;
    private static CompanyAppApi companyAppApi;
    private static NewsApi newsApi;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13b7-188-234-81-61.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        companyAppApi = retrofit.create(CompanyAppApi.class);

        crmLitePreSalesApi = retrofit.create(CRMLitePreSalesApi.class);

        newsApi = retrofit.create(NewsApi.class);
    }

    public static CRMLitePreSalesApi getPreSalesApi() {
        return crmLitePreSalesApi;
    }

    public static CompanyAppApi getDetailsApi() {
        return companyAppApi;
    }

    public static NewsApi getNewsApi() {return newsApi;}
}
