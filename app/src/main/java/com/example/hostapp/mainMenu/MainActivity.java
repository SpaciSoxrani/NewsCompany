package com.example.hostapp.mainMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.util.Log;
import com.example.hostapp.R;
import com.example.hostapp.mails.MailingItem;
import com.example.hostapp.serverapi.APINews.RiaNewsModel;
import com.example.hostapp.serverapi.App;
import com.example.hostapp.serverapi.DemoServerApi;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    List<RiaNewsModel> riaNewsList;

    private static final String TAG = "PreSaleFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialToolbar topToolbar = findViewById(R.id.topAppBar);

        topToolbar.setNavigationIcon(R.drawable.ic_menu_top_toolbar_24);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_mails, R.id.navigation_filters_mail)
                .build();
        final FragmentContainerView containerView = findViewById(R.id.nav_host_fragment);
        NavController navController = Navigation.findNavController(containerView);
        NavigationUI.setupWithNavController(navView, navController);

        riaNewsList = new ArrayList<>();
        App.getNewsApi().getRiaNews().enqueue(new Callback<List<RiaNewsModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<RiaNewsModel>> call, @NonNull Response<List<RiaNewsModel>> response) {
                if (response.body() == null) {
                    Log.i(TAG, "response.body == null");
                } else {
                    DemoServerApi.NEW_MAILINGS.clear();
                    riaNewsList.addAll(response.body());
                    for (int i = 0; i < riaNewsList.size(); i++) {
                        MailingItem newMailing = new MailingItem(
                                R.drawable.menu_item_orange,
                                riaNewsList.get(i).getName(),
                                riaNewsList.get(i).getPrediction(),
                                riaNewsList.get(i).getProbability(),
                                riaNewsList.get(i).getDateTime(),
                                riaNewsList.get(i).getId());

                        DemoServerApi.TEST_MAILINGS.add(newMailing);
                    }
                    Log.i(TAG, "OK OK OK");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<RiaNewsModel>> call, @NonNull Throwable t) {
                Log.i(TAG, "An error occurred during networking!");
            }
        });
    }
}