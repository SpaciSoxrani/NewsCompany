package com.example.hostapp.preSale;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.hostapp.R;
import com.example.hostapp.serverapi.APIPreSaleDetailsModels.PreSaleDetailsModel;
import com.example.hostapp.serverapi.APIPreSaleDetailsModels.RegionsModel;
import com.example.hostapp.serverapi.App;
import com.example.hostapp.serverapi.DemoServerApi;
import com.example.hostapp.serverapi.APIPreSaleModels.PreSaleEntryModel;
import com.example.hostapp.utils.UiUtils;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreSaleFragment extends Fragment {
    private PreSaleViewModel preSaleViewModel;
    private Context context;
    Fragment preSaleFragment;

    List<PreSaleEntryModel> preSaleList;
    private static final String TAG = "PreSaleFragment";

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preSaleList = new ArrayList<>();

        App.getPreSalesApi().getData().enqueue(new Callback<List<PreSaleEntryModel>>() {
            @Override
            public void onResponse(Call<List<PreSaleEntryModel>> call, Response<List<PreSaleEntryModel>> response) {
                if(response.body() == null){Log.i(TAG, "response.body == null");}
                else {
                    DemoServerApi.NEW_MAILINGS.clear();
                    preSaleList.addAll(response.body());
                    for (int i = 0; i < preSaleList.size(); i++) {
                        NewMailing newMailing = new NewMailing(3, preSaleList.get(i).getName().toString(),
                                preSaleList.get(i).getStatus().toString(), preSaleList.get(i).getDepartment().toString(), null, preSaleList.get(i).getId());

                        DemoServerApi.NEW_MAILINGS.add(newMailing);
                    }
                    Log.i(TAG, "OK OK OK");
                }
            }

            @Override
            public void onFailure(Call<List<PreSaleEntryModel>> call, Throwable t) {
                Log.i(TAG, "An error occurred during networking!");
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pre_sale, container, false);
        final TableLayout tableContainer = root.findViewById(R.id.tableContainer);
        MaterialButton newMailingButton = root.findViewById(R.id.newMailing);
        ImageView refreshButton = root.findViewById(R.id.refresh);
        preSaleFragment = new PreSaleFragment();
        preSaleViewModel = new ViewModelProvider(this).get(PreSaleViewModel.class);
        context = root.getContext();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        newMailingButton.setOnClickListener(v -> UiUtils.CreateNewMailingDialog("Новая рассылка", context, transaction));

        refreshButton.setOnClickListener(view -> {
            preSaleViewModel.setUpdateFragment("preSaleFragment");
            transaction.replace(R.id.nav_host_fragment, preSaleFragment);
//               transaction.addToBackStack(null);
                transaction.commit();
                preSaleViewModel.setUpdateFragment(null);
        });

        preSaleViewModel.getUpdateFragment().observe(getViewLifecycleOwner(), nameFragment -> {
            if(nameFragment == null) return;
            if(nameFragment.equals("preSaleFragment")) {
//                transaction.replace(R.id.nav_host_fragment, preSaleFragment);
////                transaction.addToBackStack(null);
//                transaction.commit();
//
//                preSaleViewModel.setUpdateFragment(null);
            }
        });


        preSaleViewModel.getSelectedEditRow().observe(getViewLifecycleOwner(), newMailing -> {
            if (newMailing == null)
                return;
            UiUtils.ShowEditMailingDialogue("Редактирование", "Измените данные рассылки", context, newMailing, transaction);
            preSaleViewModel.setSelectedEditRow(null);
        });


        preSaleViewModel.getNewMailings().observe(getViewLifecycleOwner(), newMailings -> addTableRow(newMailings, tableContainer));

        preSaleViewModel.getDeleteMailing().observe(getViewLifecycleOwner(), newMailing -> {
            if (newMailing == null)
                return;
            UiUtils.ShowDeleteDialog("Удаление рассылки", R.string.dialogue_delete_desc,
                       context, newMailing, transaction);
            preSaleViewModel.setDeleteMailing(null);
        });

        preSaleViewModel.getSelectedRow().observe(getViewLifecycleOwner(), newMailing -> {
            if (newMailing == null)
                return;

            App.getDetailsApi().getDetailsForPreSale(newMailing.idGroup).enqueue(new Callback<List<PreSaleDetailsModel>>() {
                @Override
                public void onResponse(Call<List<PreSaleDetailsModel>> call, Response<List<PreSaleDetailsModel>> response) {
                    if(response.body() == null){
                        Log.i(TAG, "response.body of PreSaleDetailsModel == null");}
                    else {
                        DemoServerApi.DETAILS_MODEL.clear();
                        DemoServerApi.DETAILS_MODEL.addAll(response.body());
                        Log.i(TAG, "Get list PreSaleDetailsModel");
                    }
                }

                @Override
                public void onFailure(Call<List<PreSaleDetailsModel>> call, Throwable t) {
                    Log.i(TAG, "Enable to get list PreSaleDetailsModel!");
                }
            });

            UiUtils.ShowGoToEditRowDialog("Редактирование рассылки", "Перейти к редактированию контактов выбранной рассылки?",
                    context, transaction);
            preSaleViewModel.setSelectedRow(null);
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

        for (int i = 0; i < newMailings.size(); i++) {
            final NewMailing newMailing = newMailings.get(i);
            TableRow row = new TableRow(context);

            ImageView icon = new ImageView(context);
            icon.setImageResource(R.drawable.ic_baseline_edit_24);

            ImageView iconDelete = new ImageView(context);
            iconDelete.setImageResource(R.drawable.ic_baseline_delete_24);

            row = createTableRow(newMailing.name, newMailing.status, newMailing.depart, icon, iconDelete);
            tableContainer.addView(row);
            icon.setOnClickListener(v -> preSaleViewModel.setSelectedEditRow(newMailing));

            iconDelete.setOnClickListener(v -> preSaleViewModel.setDeleteMailing(newMailing));

            row.setOnClickListener(v -> preSaleViewModel.setSelectedRow(newMailing));
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
