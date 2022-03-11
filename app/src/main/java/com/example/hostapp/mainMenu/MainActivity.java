package com.example.hostapp.mainMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import com.example.hostapp.R;
import com.example.hostapp.preSale.NewMailing;
import com.example.hostapp.serverapi.APIModels.MainDepartmentsModel;
import com.example.hostapp.serverapi.APIModels.PreSaleGroupStatusesModel;
import com.example.hostapp.serverapi.App;
import com.example.hostapp.serverapi.DemoServerApi;
import com.example.hostapp.serverapi.APIModels.PreSaleEntryModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private MaterialToolbar topToolbar;
    private NavController navController;

    FragmentTransaction transaction;
    Fragment mainMenuFragment;
    private FragmentManager manager;

    List<PreSaleEntryModel> preSaleList;
    List<MainDepartmentsModel> mainDepartmentsModelList;

    private static final String TAG = "PreSaleFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topToolbar = findViewById(R.id.topAppBar);
        //topToolbar.setTitle(R.string.title_main);

        preSaleList = new ArrayList<>();
        mainDepartmentsModelList = new ArrayList<>();

        App.getApi().getData().enqueue(new Callback<List<PreSaleEntryModel>>() {
            @Override
            public void onResponse(Call<List<PreSaleEntryModel>> call, Response<List<PreSaleEntryModel>> response) {
                if(response.body() == null){Log.i(TAG, "response.body == null");}
                else {
                    DemoServerApi.NEW_MAILINGS.clear();
                    preSaleList.addAll(response.body());
                    for (int i = 0; i < preSaleList.size(); i++) {
                        NewMailing newMailing = new NewMailing(3, preSaleList.get(i).getName().toString(),
                                preSaleList.get(i).getStatus().toString(), preSaleList.get(i).getDepartment().toString(), null, preSaleList.get(i).getId());

                        DemoServerApi.NEW_MAILINGS.add(newMailing);
                    }
                    Log.i(TAG, "OK OK OK");
                }
            }

            @Override
            public void onFailure(Call<List<PreSaleEntryModel>> call, Throwable t) {
                Log.i(TAG, "An error occurred during networking!");
            }
        });

        App.getApi().getMainDepartments().enqueue(new Callback<List<MainDepartmentsModel>>() {
            @Override
            public void onResponse(Call<List<MainDepartmentsModel>> call, Response<List<MainDepartmentsModel>> response) {
                if(response.body() == null){Log.i(TAG, "response.body == null");}
                else {
                    DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.clear();
                    DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.addAll(response.body());
                    Log.i(TAG, "Get main departments list");
                }
            }

            @Override
            public void onFailure(Call<List<MainDepartmentsModel>> call, Throwable t) {
                Log.i(TAG, "Enable to get main departments!");
            }
        });

        App.getApi().getPresaleGroupStatuses().enqueue(new Callback<List<PreSaleGroupStatusesModel>>() {
            @Override
            public void onResponse(Call<List<PreSaleGroupStatusesModel>> call, Response<List<PreSaleGroupStatusesModel>> response) {
                if(response.body() == null){Log.i(TAG, "response.body == null");}
                else {
                    DemoServerApi.PRE_SALE_GROUP_STATUSES_MODEL_LIST.clear();
                    DemoServerApi.PRE_SALE_GROUP_STATUSES_MODEL_LIST.addAll(response.body());
                    Log.i(TAG, "Get group statuses");
                }
            }

            @Override
            public void onFailure(Call<List<PreSaleGroupStatusesModel>> call, Throwable t) {
                Log.i(TAG, "Enable to get group statuses!");
            }
        });

        final FragmentContainerView containerView = findViewById(R.id.nav_host_fragment);
        navController = Navigation.findNavController(containerView);
        topToolbar.setNavigationIcon(R.drawable.ic_menu_top_toolbar_24);
        manager = getSupportFragmentManager();

        //todo create navigation class !!!
        topToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.nav_profile_icon) {
                this.navController.navigate(R.id.action_navigation_main_menu_to_navigation_profile);

            } else {
            }
            return false;
        });


    }


//    @Override
//    public void onBackPressed() {
//        // super.onBackPressed();
//        Fragment mainMenu = new MainMenuFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, mainMenu).commit();
//    }
}