package com.example.hostapp.mainMenu;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import com.example.hostapp.R;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hostapp.preSale.PreSaleFragment;
import com.example.hostapp.serverapi.DemoServerApi;

import java.util.List;

public class MainMenuFragment extends Fragment {
    private MainMenuViewModel mainMenuViewModel;
    private View card;
    private Context context;
    Fragment preSaleFragment;
    Fragment mainMenuFragment;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainMenuViewModel = new ViewModelProvider(this).get(MainMenuViewModel.class);
        View root = inflater.inflate(R.layout.fragment_main_menu, container, false);
        context = root.getContext();

        //final LinearLayoutCompat cardsContainer = root.findViewById(R.id.card_container);
        final GridLayout cardsContainer = root.findViewById(R.id.grid_container);

        mainMenuViewModel.getMenuItems().observe(getViewLifecycleOwner(), new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(List<MenuItem> menuItems) {
                redrawEventsCards(menuItems, cardsContainer);
            }
        });

        mainMenuViewModel.getSelectedMenuItem().observe(getViewLifecycleOwner(), new Observer<MenuItem>() {
            @Override
            public void onChanged(MenuItem menuItem) {
                if (menuItem == null)
                    return;
                if(menuItem.getId() == 7) {
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    preSaleFragment = new PreSaleFragment();
                    transaction.replace(R.id.nav_host_fragment, preSaleFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });

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
