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
import com.example.hostapp.serverapi.App;
import com.example.hostapp.serverapi.DemoServerApi;
import com.example.hostapp.serverapi.PreSaleEntryModel;
import com.google.android.material.appbar.MaterialToolbar;

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
    private static final String TAG = "PreSaleFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topToolbar = findViewById(R.id.topAppBar);
        //topToolbar.setTitle(R.string.title_main);

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


        App.getApi().getData().enqueue(new Callback<List<PreSaleEntryModel>>() {
            @Override
            public void onResponse(Call<List<PreSaleEntryModel>> call, Response<List<PreSaleEntryModel>> response) {
                preSaleList.addAll(response.body());
                for(int i = 0; i < preSaleList.size(); i++)
                {
                    DemoServerApi.NEW_MAILINGS.add(new NewMailing(4, preSaleList.get(i).getName().toString(), preSaleList.get(i).getStatus(),
                            preSaleList.get(i).getDepartment(), null));
                }
                Log.i(TAG, "OK OK OK");
            }

            @Override
            public void onFailure(Call<List<PreSaleEntryModel>> call, Throwable t) {
                Log.i(TAG, "An error occurred during networking!");
                //Toast.makeText(PreSaleFragment.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }


//    @Override
//    public void onBackPressed() {
//        // super.onBackPressed();
//        Fragment mainMenu = new MainMenuFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, mainMenu).commit();
//    }
}