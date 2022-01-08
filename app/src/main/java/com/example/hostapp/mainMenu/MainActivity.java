package com.example.hostapp.mainMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.view.View;

import com.example.hostapp.R;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private MaterialToolbar topToolbar;
    private  TextView flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flag = findViewById(R.id.flag);

        topToolbar = findViewById(R.id.topAppBar);
        topToolbar.setNavigationIcon(R.drawable.ic_menu_top_toolbar_24);
        // Handle navigation icon press
        topToolbar.setNavigationOnClickListener(v -> flag.setText("MENU"));

        topToolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.nav_profile_icon) {
                flag.setText("PROFILE");
            } else {
                // do something
            }
            return false;
        });
    }

}