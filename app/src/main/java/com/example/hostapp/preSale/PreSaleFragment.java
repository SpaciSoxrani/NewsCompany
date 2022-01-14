package com.example.hostapp.preSale;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hostapp.BackPressedForFragments;
import com.example.hostapp.R;
import com.example.hostapp.mainMenu.MainMenuFragment;
import com.example.hostapp.mainMenu.MainMenuViewModel;
import com.example.hostapp.mainMenu.MenuItem;
import com.example.hostapp.serverapi.DemoServerApi;

import java.util.List;

public class PreSaleFragment extends Fragment {
    Fragment mainMenuFragment;
    private PreSaleViewModel preSaleViewModel;
    private Context context;

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pre_sale_fragment, container, false);
        final TableLayout tableContainer = root.findViewById(R.id.tableContainer);
        preSaleViewModel = new ViewModelProvider(this).get(PreSaleViewModel.class);
        context = root.getContext();
        preSaleViewModel.getNewMailings().observe(getViewLifecycleOwner(), new Observer<List<NewMailing>>() {
            @Override
            public void onChanged(List<NewMailing> newMailings) {
                addTableRow(newMailings, tableContainer);
            }
        });

        preSaleViewModel.setNewMailings(DemoServerApi.NEW_MAILINGS);
        return root;
    }

    private void addTableRow(List<NewMailing> newMailings, TableLayout tableContainer) {
        //tableContainer.removeAllViews();

        for (int i = 0; i < newMailings.size(); i++) {
            final NewMailing newMailing = newMailings.get(i);
            TableRow tr1 = new TableRow(context);
            TextView tw1 = new TextView(context);
            TextView tw2 = new TextView(context);
            TextView tw3 = new TextView(context);
            tw1.setText(newMailing.name);
            tr1.addView(tw1);
            tw2.setText(newMailing.status);
            tr1.addView(tw2);
            tw3.setText(newMailing.depart);
            tr1.addView(tw3);
            tableContainer.addView(tr1);
        }
    }

}
