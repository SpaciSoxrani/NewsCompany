package com.example.hostapp.mainMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.hostapp.R;
import com.example.hostapp.preSale.PreSaleFragment;
import com.example.hostapp.profile.ProfileFragment;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private MaterialToolbar topToolbar;
    private NavController navController;

    FragmentTransaction transaction;
    Fragment mainMenuFragment;
    private FragmentManager manager;

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
    }


//    @Override
//    public void onBackPressed() {
//        // super.onBackPressed();
//        Fragment mainMenu = new MainMenuFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, mainMenu).commit();
//    }
}