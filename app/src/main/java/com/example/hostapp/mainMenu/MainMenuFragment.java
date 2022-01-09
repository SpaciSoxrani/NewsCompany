package com.example.hostapp.mainMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import com.example.hostapp.R;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.hostapp.login.LoginViewModel;
import com.example.hostapp.serverapi.DemoServerApi;

import java.util.List;

public class MainMenuFragment extends Fragment {
    private MainMenuViewModel mainMenuViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainMenuViewModel = new ViewModelProvider(this).get(MainMenuViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_menu, container, false);

        final AppCompatTextView textViewDescription = root.findViewById(R.id.text_description);
        final AppCompatImageView textViewIconDescription = root.findViewById(R.id.icon_description);
        final AppCompatImageView imageViewItem = root.findViewById(R.id.location_bg);
        final LinearLayoutCompat cardsContainer = root.findViewById(R.id.card_container);

        mainMenuViewModel.getMenuItems().observe(getViewLifecycleOwner(), new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                redrawEventsCards(menuItems, cardsContainer);
            }
        });

        mainMenuViewModel.getSelectedMenuItem().observe(getViewLifecycleOwner(), new Observer<MenuItem>() {
            @Override
            public void onChanged(MenuItem menuItem) {
                if(menuItem==null)
                    return;
                textViewDescription.setText(menuItem.name);
                textViewIconDescription.setImageDrawable(getResources().getDrawable(MenuItem.icons[menuItem.icon]));
                imageViewItem.setImageDrawable(getResources().getDrawable(MenuItem.backgrounds[menuItem.image]));
            }
        });

        //todo load from server
        mainMenuViewModel.setMenuItems(DemoServerApi.ITEMS);
        setHasOptionsMenu(true);

        return  root;
    }

    private void redrawEventsCards(List<MenuItem> menuItems, LinearLayoutCompat container) {
        container.removeAllViews();
        for (int i = 0; i < menuItems.size(); i++) {
            final MenuItem menuItem = menuItems.get(i);
            View card = LayoutInflater.from(getContext()).inflate(R.layout.card_menu_item, container, false);
            AppCompatImageView imageView = card.findViewById(R.id.location_bg);
            AppCompatImageView imageIconView = card.findViewById(R.id.icon_description);
            AppCompatTextView textViewName = card.findViewById(R.id.text_description);
            imageView.setImageResource(MenuItem.backgrounds[menuItem.image]);
            imageIconView.setImageResource(MenuItem.icons[menuItem.icon]);
            textViewName.setText(menuItem.name);
            container.addView(card);
        }
    }
}
