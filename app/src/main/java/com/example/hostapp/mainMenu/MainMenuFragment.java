package com.example.hostapp.mainMenu;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.example.hostapp.R;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;

import com.example.hostapp.login.LoginViewModel;
import com.example.hostapp.serverapi.DemoServerApi;

import java.util.List;

public class MainMenuFragment extends Fragment {
    private MainMenuViewModel mainMenuViewModel;
    private View card;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainMenuViewModel = new ViewModelProvider(this).get(MainMenuViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_menu, container, false);

        final AppCompatTextView textViewDescription = root.findViewById(R.id.text_description);
        final AppCompatImageView textViewIconDescription = root.findViewById(R.id.icon_description);
        final AppCompatImageView imageViewItem = root.findViewById(R.id.main_card);
        //final LinearLayoutCompat cardsContainer = root.findViewById(R.id.card_container);
        final GridLayout cardsContainer = root.findViewById(R.id.grid_container);

        mainMenuViewModel.getMenuItems().observe(getViewLifecycleOwner(), new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                redrawEventsCards(menuItems, cardsContainer);
            }
        });

//        mainMenuViewModel.getSelectedMenuItem().observe(getViewLifecycleOwner(), new Observer<MenuItem>() {
//            @Override
//            public void onChanged(MenuItem menuItem) {
//                if (menuItem == null)
//                    return;
//                flag.setText(menuItem.name);
//            }
//        });

        mainMenuViewModel.setMenuItems(DemoServerApi.ITEMS);
        setHasOptionsMenu(true);

        return root;
    }


    private void redrawEventsCards(List<MenuItem> menuItems, GridLayout container) {
        container.removeAllViews();
        int TABLE_COLUMNS = 2;
        int TABLE_ROWS = menuItems.size() / 2 + menuItems.size() % 2;
        container.setRowCount(TABLE_ROWS);

        for (int i = 0; i < menuItems.size(); i++) {
            final MenuItem menuItem = menuItems.get(i);

            card = LayoutInflater.from(getContext()).inflate(R.layout.card_menu_item, container, false);

            AppCompatImageView imageView = card.findViewById(R.id.location_bg);
            AppCompatImageView imageIconView = card.findViewById(R.id.icon_description);
            AppCompatTextView textViewName = card.findViewById(R.id.text_description);
            imageView.setImageResource(MenuItem.backgrounds[menuItem.image]);
            imageIconView.setImageResource(MenuItem.icons[menuItem.icon]);
            textViewName.setText(menuItem.name);

            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainMenuViewModel.setSelectedMenuItem(menuItem);
                }
            });

            container.addView(card);

        }
    }
}
