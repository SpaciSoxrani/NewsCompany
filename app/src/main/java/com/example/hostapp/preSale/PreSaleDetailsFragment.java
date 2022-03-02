package com.example.hostapp.preSale;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hostapp.R;
import com.example.hostapp.mainMenu.MainMenuViewModel;
import com.example.hostapp.mainMenu.MenuItem;
import com.example.hostapp.serverapi.DemoServerApi;
import com.example.hostapp.utils.UiUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PreSaleDetailsFragment extends Fragment {
    private Context context;
    private PreSaleDetailsViewModel preSaleDetailsViewModel;
    private View card;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        preSaleDetailsViewModel = new ViewModelProvider(this).get(PreSaleDetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pre_sale_details, container, false);
        context = root.getContext();

        final LinearLayout cardsContainer = root.findViewById(R.id.layout_container);
        final FloatingActionButton floatingActionButton = root.findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                UiUtils.AddNewPreSaleEntryDialog("Добавление контакта", context, transaction);

            }
        });


        preSaleDetailsViewModel.getMenuItems().observe(getViewLifecycleOwner(), new Observer<List<PreSaleEntry>>() {
                    @Override
                    public void onChanged(List<PreSaleEntry> preSaleEntries) {
                        redrawPreSaleCards(preSaleEntries, cardsContainer);
                    }
                });

                preSaleDetailsViewModel.getSelectedMenuItem().observe(getViewLifecycleOwner(), new Observer<PreSaleEntry>() {
                            @Override
                            public void onChanged(PreSaleEntry preSaleEntry) {

                            }
                        });

        preSaleDetailsViewModel.setMenuItems(DemoServerApi.PRE_SALE_ENTRIES);
        setHasOptionsMenu(true);
        return root;
    }

    private void redrawPreSaleCards(List<PreSaleEntry> preSaleEntries, LinearLayout container) {
        container.removeAllViews();

        for (int i = 0; i < preSaleEntries.size(); i++) {
            final PreSaleEntry preSaleEntry = preSaleEntries.get(i);

            card = LayoutInflater.from(getContext()).inflate(R.layout.card_pre_sale_details, container, false);

            AppCompatTextView districtView = card.findViewById(R.id.district);
            AppCompatTextView timeZoneView = card.findViewById(R.id.time_zone);
            AppCompatTextView organizationView = card.findViewById(R.id.organization);
            AppCompatTextView nameView = card.findViewById(R.id.full_name);
            AppCompatTextView positionView = card.findViewById(R.id.position);
            AppCompatTextView phoneView = card.findViewById(R.id.phone);
            AppCompatTextView emailView = card.findViewById(R.id.mail);
            AppCompatTextView siteView = card.findViewById(R.id.site);
            AppCompatTextView requestView = card.findViewById(R.id.requestSend);
            AppCompatTextView numberView = card.findViewById(R.id.number);

            districtView.setText(preSaleEntry.district);
            timeZoneView.setText(preSaleEntry.timeZone);
            organizationView.setText(preSaleEntry.organization);
            nameView.setText(preSaleEntry.name);
            positionView.setText(preSaleEntry.position);
            phoneView.setText(preSaleEntry.phone);
            emailView.setText(preSaleEntry.mail);
            siteView.setText(preSaleEntry.site);
            requestView.setText(preSaleEntry.requestSend);
            numberView.setText(preSaleEntry.number);;

            container.addView(card);

        }
    }
}
