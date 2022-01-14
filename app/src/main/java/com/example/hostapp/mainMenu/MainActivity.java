package com.example.hostapp.mainMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.WindowManager;
import android.widget.TextView;
import android.view.View;

import com.example.hostapp.BackPressedForFragments;
import com.example.hostapp.R;
import com.example.hostapp.preSale.PreSaleFragment;
import com.example.hostapp.profile.ProfileFragment;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private MaterialToolbar topToolbar;
    private NavController navController;
    Fragment mainMenuFragment;

    /** идентификатор первого фрагмента. */
    public static final int FRAGMENT_MAIN = 0;
    /** идентификатор второго. */
    public static final int FRAGMENT_PROFILE = 1;
    /** идентификатор третего. */
    public static final int FRAGMENT_PRE_SALE = 2;
    /** количество фрагментов. */
    public static final int FRAGMENTS = 3;
    /** адаптер фрагментов. */
    private FragmentPagerAdapter _fragmentPagerAdapter;
    /** список фрагментов для отображения. */
    private final List<Fragment> _fragments = new ArrayList<Fragment>();
    /** сам ViewPager который будет все это отображать. */
    private ViewPager _viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topToolbar = findViewById(R.id.topAppBar);
        //topToolbar.setTitle(R.string.title_main);

        // создаем фрагменты.
        _fragments.add(FRAGMENT_MAIN, new MainMenuFragment());
        _fragments.add(FRAGMENT_PROFILE, new ProfileFragment());
        _fragments.add(FRAGMENT_PRE_SALE, new PreSaleFragment());

        final FragmentContainerView containerView = findViewById(R.id.nav_host_fragment);
        navController = Navigation.findNavController(containerView);
        topToolbar.setNavigationIcon(R.drawable.ic_menu_top_toolbar_24);

        FragmentManager fragmentManager = getSupportFragmentManager();

        //todo create navigation class !!!
        topToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.nav_profile_icon) {
                this.navController.navigate(R.id.action_navigation_main_menu_to_navigation_profile);

            } else {
            }
            return false;
        });
    }
}