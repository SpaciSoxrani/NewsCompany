package com.example.hostapp.serverapi;

import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static CRMLiteApi crmLiteApi;

    @Override
    public void onCreate() {
        super.onCreate();

        //Базовая часть адреса
        //Конвертер, необходимый для преобразования JSON'а в объекты
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://c583-94-75-13-255.ngrok.io") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();

        crmLiteApi = retrofit.create(CRMLiteApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static CRMLiteApi getApi() {
        return crmLiteApi;
    }
}
