package com.example.hostapp.serverapi;

import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static CRMLitePreSalesApi crmLitePreSalesApi;
    private static CRMLiteDetailsApi crmLiteDetailsApi;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://378b-176-59-193-18.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crmLitePreSalesApi = retrofit.create(CRMLitePreSalesApi.class);
        crmLiteDetailsApi = retrofit.create(CRMLiteDetailsApi.class);
    }

    public static CRMLitePreSalesApi getPreSalesApi() {
        return crmLitePreSalesApi;
    }

    public static CRMLiteDetailsApi getDetailsApi() {
        return crmLiteDetailsApi;
    }
}
