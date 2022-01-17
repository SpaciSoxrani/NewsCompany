package com.example.hostapp.preSale;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.hostapp.R;
import com.example.hostapp.serverapi.DemoServerApi;
import com.example.hostapp.utils.UiUtils;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class PreSaleFragment extends Fragment {
    Fragment mainMenuFragment;
    private PreSaleViewModel preSaleViewModel;
    private Context context;
    Fragment preSaleFragment;
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
        MaterialButton newMailingButton = root.findViewById(R.id.newMailing);
        ImageView refreshView = root.findViewById(R.id.refresh);
        preSaleFragment = new PreSaleFragment();
        preSaleViewModel = new ViewModelProvider(this).get(PreSaleViewModel.class);
        context = root.getContext();

        newMailingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtils.CreateNewMailingDialog("Новая рассылка", context, preSaleFragment);
            }
        });

        refreshView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                preSaleFragment = new PreSaleFragment();
                transaction.replace(R.id.nav_host_fragment, preSaleFragment);
                transaction.commit();
            }
        });

        preSaleViewModel.getSelectedEditRow().observe(getViewLifecycleOwner(), new Observer<NewMailing>() {
            @Override
            public void onChanged(NewMailing newMailing) {
                if (newMailing == null)
                    return;
                UiUtils.ShowEditMailingDialogue("Редактирование", "Измените данные рассылки", context, newMailing);
            }
        });


        preSaleViewModel.getNewMailings().observe(getViewLifecycleOwner(), new Observer<List<NewMailing>>() {
            @Override
            public void onChanged(List<NewMailing> newMailings) {
                addTableRow(newMailings, tableContainer);
            }
        });

        preSaleViewModel.getDeleteMailing().observe(getViewLifecycleOwner(), new Observer<NewMailing>() {
            @Override
            public void onChanged(NewMailing newMailing) {
                if (newMailing == null)
                    return;
                UiUtils.ShowDeleteDialog("Удаление рассылки", R.string.dialogue_delete_desc,
                           context, newMailing);
            }
        });

        preSaleViewModel.setNewMailings(DemoServerApi.NEW_MAILINGS);
        return root;
    }

    private void addTableRow(List<NewMailing> newMailings, TableLayout tableContainer) {
        tableContainer.removeAllViews();

        ImageView iconHeader = new ImageView(context);
        ImageView iconHeader2 = new ImageView(context);

        iconHeader.setImageResource(R.drawable.ic_baseline_psychology_24);
        tableContainer.addView(createTableRow("Название", "Статус", "Департамент", iconHeader, iconHeader2));
        TableRow row = new TableRow(context);


        for (int i = 0; i < newMailings.size(); i++) {
            final NewMailing newMailing = newMailings.get(i);

            ImageView icon = new ImageView(context);
            icon.setImageResource(R.drawable.ic_baseline_edit_24);

            ImageView iconDelete = new ImageView(context);
            iconDelete.setImageResource(R.drawable.ic_baseline_delete_24);

            row = createTableRow(newMailing.name, newMailing.status, newMailing.depart, icon, iconDelete);
            tableContainer.addView(row);
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    preSaleViewModel.setSelectedEditRow(newMailing);
                }
            });

            iconDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    preSaleViewModel.setDeleteMailing(newMailing);
                }
            });
        }
    }

    private TableRow createTableRow(String name, String status, String depart, ImageView icon, ImageView iconDelete){
        TableRow tr1 = new TableRow(context);
        TextView twName = new TextView(context);
        TextView twStatus = new TextView(context);
        TextView twDepart = new TextView(context);

        twName.setText(name);
        twName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 0.20f));
        tr1.addView(twName);

        twStatus.setText(status);
        twStatus.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 0.20f));
        tr1.addView(twStatus);

        twDepart.setText(depart);
        twDepart.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 0.20f));
        tr1.addView(twDepart);

        icon.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 0.20f));
        tr1.addView(icon);

        iconDelete.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 0.20f));
        tr1.addView(iconDelete);

        return  tr1;
    }

}
