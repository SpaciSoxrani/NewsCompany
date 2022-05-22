package com.example.hostapp.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hostapp.R;
import com.example.hostapp.preSale.NewMailing;
import com.example.hostapp.preSale.PreSaleDetailsFragment;
import com.example.hostapp.preSale.PreSaleEntry;
//import com.example.hostapp.preSale.PreSaleFragment;
import com.example.hostapp.profile.ProfileFragment;
import com.example.hostapp.serverapi.APIPreSaleModels.PUTEditGroupModel;
import com.example.hostapp.serverapi.APIPreSaleModels.PostPreSaleGroup;
import com.example.hostapp.serverapi.APIPreSaleModels.PreSaleGroupModel;
import com.example.hostapp.serverapi.App;
import com.example.hostapp.serverapi.DemoServerApi;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UiUtils extends AppCompatActivity {

    public static void ShowSimpleDialog(String title, String description, Context context){
        AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(description)
                .setPositiveButton(R.string.button_ok, (dialog1, which) -> dialog1.cancel()).create();
        dialog.show();
    }

    public static void CreateNewMailingDialog(String title, Context context, FragmentTransaction transaction){
        final String[] statusGroup = {""};
        final String[] departGroup = {""};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LinearLayout lila1= new LinearLayout(context);
        lila1.setLayoutParams(new LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.WRAP_CONTENT));
        lila1.setOrientation(LinearLayout.VERTICAL);

        TextInputEditText name = new TextInputEditText(context);
        name.setHint(R.string.table_header_name);

        //spinner status list
        Spinner spinStatus = createSpinnerStatus(context, statusGroup);

        // TODO: fix this govno for spinner department' list
        Spinner spinDepart = createSpinnerDepartment(context, departGroup);

        lila1.addView(name);
        lila1.addView(spinStatus);
        lila1.addView(spinDepart);

        builder
                .setTitle(title)
                .setView(lila1)
                .setPositiveButton(R.string.button_ok, (dialog, whichButton) -> {
                    if(!name.getText().toString().equals("")){
                        PostPreSaleGroup postPreSaleGroup = new PostPreSaleGroup();
                        postPreSaleGroup.setName(name.getText().toString());
                        // TODO: fix logic for finding status id and depart id
                        for(int i = 0; i < DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.size(); i ++){
                            if(departGroup[0].equals(DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.get(i).getName())){
                                postPreSaleGroup.setDepartmentId(DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.get(i).getId());
                            }
                        };

                        for(int i = 0; i < DemoServerApi.PRE_SALE_GROUP_STATUSES_MODEL_LIST.size(); i ++){
                            if(statusGroup[0].equals(DemoServerApi.PRE_SALE_GROUP_STATUSES_MODEL_LIST.get(i).getName())){
                                postPreSaleGroup.setStatusId(DemoServerApi.PRE_SALE_GROUP_STATUSES_MODEL_LIST.get(i).getId());
                            }
                        };

                        Call<PostPreSaleGroup> call = App.getPreSalesApi().createPost(postPreSaleGroup);
                        call.enqueue(new Callback<PostPreSaleGroup>() {
                            @Override
                            public void onResponse(Call<PostPreSaleGroup> call, Response<PostPreSaleGroup> response) {
                                Log.i("Group id", postPreSaleGroup.getStatusId() + ' ' + postPreSaleGroup.getDepartmentId()  );
                            }

                            @Override
                            public void onFailure(Call<PostPreSaleGroup> call, Throwable t) {
                                Log.i("Enable post group", "NOOOOO");
                            }
                        });
                    }
                    else{
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    private static String onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        String preSaleGroupStatus= (String) adapter.getItem(position);
        Log.i("Get status", preSaleGroupStatus);

        return preSaleGroupStatus;
    }

    private static Spinner createSpinnerStatus(Context context, String[] statusGroup){
        Spinner spinStatus = new Spinner(context);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, new String[]{
                DemoServerApi.PRE_SALE_GROUP_STATUSES_MODEL_LIST.get(0).getName(),
                DemoServerApi.PRE_SALE_GROUP_STATUSES_MODEL_LIST.get(1).getName(),
        });

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinStatus.setAdapter(adapter);

        spinStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                statusGroup[0] = (String) adapter.getItem(position);
                Log.i("Get status", statusGroup[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                statusGroup[0] = (String) adapter.getItem(0);
            }
        });
        return spinStatus;
    }

    private static Spinner createSpinnerDepartment(Context context, String[] departGroup){
        Spinner spinDepart = new Spinner(context);
        ArrayAdapter<String> adapterDepart = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, new String[]{
                DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.get(0).getName(),
                DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.get(1).getName(),
                DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.get(2).getName(),
                DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.get(3).getName()
        });

        adapterDepart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDepart.setAdapter(adapterDepart);

        spinDepart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Adapter adapter = parent.getAdapter();
                departGroup[0] = (String) adapter.getItem(position);
                Log.i("Get depart", departGroup[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Adapter adapter = parent.getAdapter();
                departGroup[0] = (String) adapter.getItem(0);
            }
        });
        return spinDepart;
    }

    public static void ShowEditMailingDialogue(String title, String description, Context context, NewMailing mailing, FragmentTransaction transaction){
        final String[] statusGroup = {""};
        final String[] departGroup = {""};

        LinearLayout lila1= new LinearLayout(context);
        lila1.setLayoutParams(new LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.WRAP_CONTENT));
        lila1.setOrientation(LinearLayout.VERTICAL);

        TextInputEditText name = new TextInputEditText(context);
        name.setText(mailing.name);

        Spinner spinStatus = createSpinnerStatus(context, statusGroup);
        Spinner spinDepart = createSpinnerDepartment(context, departGroup);

        lila1.addView(name);
        lila1.addView(spinStatus);
        lila1.addView(spinDepart);

        AlertDialog.Builder dialog = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setView(lila1)
                .setMessage(description)
                .setPositiveButton(R.string.button_edit, (dialog1, whichButton) -> {
                    PUTEditGroupModel putEditGroupModel = new PUTEditGroupModel();
                    putEditGroupModel.setName(name.getText().toString());

                    for(int i = 0; i < DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.size(); i ++){
                        if(departGroup[0].equals(DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.get(i).getName())){
                            putEditGroupModel.setDepartmentId(DemoServerApi.MAIN_DEPARTMENTS_MODEL_LIST.get(i).getId());
                        }
                    };

                    for(int i = 0; i < DemoServerApi.PRE_SALE_GROUP_STATUSES_MODEL_LIST.size(); i ++){
                        if(statusGroup[0].equals(DemoServerApi.PRE_SALE_GROUP_STATUSES_MODEL_LIST.get(i).getName())){
                            putEditGroupModel.setStatusId(DemoServerApi.PRE_SALE_GROUP_STATUSES_MODEL_LIST.get(i).getId());
                        }
                    };
                    putEditGroupModel.setId(mailing.idGroup);

                    Call<ResponseBody> call = App.getPreSalesApi().editPreSaleGroup(putEditGroupModel.getId(), putEditGroupModel);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.i("Edit group", "OK"  );
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.i("Edit group", "NOT OK");
                        }
                    });

                })
                .setNegativeButton(R.string.button_close, (dialogInterface, i) -> dialogInterface.cancel());

        dialog.show();
    }

//    public static void ShowDeleteDialog(String title, int description, Context context, NewMailing mailing, FragmentTransaction transaction){
//        LinearLayout lila1= new LinearLayout(context);
//        lila1.setLayoutParams(new LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.WRAP_CONTENT));
//        lila1.setOrientation(LinearLayout.VERTICAL);
//
//        ImageView img = new ImageView(context);
//        img.setImageResource(R.drawable.ic_twotone_warning_24);
//        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//        AlertDialog.Builder dialog = new MaterialAlertDialogBuilder(context)
//                .setTitle(title)
//                .setMessage(description)
//                .setView(img)
//                .setPositiveButton(R.string.button_ok, (dialogInterface, i) -> {
//
//                    Call<PreSaleGroupModel> call = App.getPreSalesApi().deletePreSaleGroup(mailing.idGroup);
//
//                    call.enqueue(new Callback<PreSaleGroupModel>() {
//                        @Override
//                        public void onResponse(Call<PreSaleGroupModel> call, Response<PreSaleGroupModel> response) {
//                            Log.i("Delete group", "OK OK OK");
//                        }
//
//                        @Override
//                        public void onFailure(Call<PreSaleGroupModel> call, Throwable t) {
//                            Log.i("Delete group", "NOOOOO");
//                        }
//                    });
//
//                    DemoServerApi.NEW_MAILINGS.remove(mailing);
//
//                    Fragment preSaleFragment = new PreSaleFragment();
////                    transaction.replace(R.id.nav_host_fragment, preSaleFragment);
////                    transaction.addToBackStack(null);
////                    transaction.commit();
//
//                    dialogInterface.cancel();
//
//                })
//                .setNegativeButton(R.string.button_close, (dialogInterface, i) -> dialogInterface.cancel());
//        dialog.show();
//    }

    public static void ShowGoToEditRowDialog(String title, String description, Context context, FragmentTransaction transaction){
        AlertDialog.Builder dialog = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(description)
                .setPositiveButton(R.string.button_ok, (dialogInterface, i) -> {
                    dialogInterface.cancel();
                    ProfileFragment prof = new ProfileFragment();

//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    Fragment proF = new PreSaleDetailsFragment();
                    transaction.replace(R.id.nav_host_fragment, proF);
                    transaction.addToBackStack(null);
                    transaction.commit();
                })
                .setNegativeButton(R.string.button_close, (dialogInterface, i) -> dialogInterface.cancel());
        dialog.show();
    }

    public static void AddNewPreSaleEntryDialog(String title, Context context, FragmentTransaction transaction){
        LinearLayout lila1= new LinearLayout(context);
        lila1.setLayoutParams(new LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.WRAP_CONTENT));
        lila1.setPadding(10, 10,10,10);
        lila1.setOrientation(LinearLayout.VERTICAL);

        TextInputEditText districtView = new TextInputEditText(context);
        TextInputEditText timeZoneView = new TextInputEditText(context);
        TextInputEditText organizationView = new TextInputEditText(context);
        TextInputEditText nameView = new TextInputEditText(context);
        TextInputEditText positionView = new TextInputEditText(context);
        TextInputEditText phoneView = new TextInputEditText(context);
        TextInputEditText emailView = new TextInputEditText(context);
        TextInputEditText siteView = new TextInputEditText(context);
        TextInputEditText requestView = new TextInputEditText(context);
        TextInputEditText numberView = new TextInputEditText(context);

        districtView.setHint("Регион");
        timeZoneView.setHint("Часовой пояс");
        organizationView.setHint("Организация");
        nameView.setHint("ФИО");
        positionView.setHint("Должность");
        phoneView.setHint("Телефон");
        emailView.setHint("E-mail");
        siteView.setHint("Сайт");
        requestView.setHint("Запрос отправлен");
        numberView.setHint("Номер входящего");

        lila1.addView(districtView);
        lila1.addView(timeZoneView);
        lila1.addView(organizationView);
        lila1.addView(nameView);
        lila1.addView(positionView);
        lila1.addView(phoneView);
        lila1.addView(emailView);
        lila1.addView(siteView);
        lila1.addView(requestView);
        lila1.addView(numberView);


        AlertDialog.Builder dialog = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setView(lila1)
                .setPositiveButton(R.string.button_ok, (dialogInterface, i) -> {
                    PreSaleEntry newEntry = new PreSaleEntry(4, districtView.getText().toString(), timeZoneView.getText().toString(),
                            organizationView.getText().toString(), nameView.getText().toString(), positionView.getText().toString(),
                            phoneView.getText().toString(), emailView.getText().toString(), siteView.getText().toString(),
                            requestView.getText().toString(), numberView.getText().toString());

                    DemoServerApi.PRE_SALE_ENTRIES.add(newEntry);
                    dialogInterface.cancel();

                    Fragment proF = new PreSaleDetailsFragment();
                    transaction.replace(R.id.nav_host_fragment, proF);
                    transaction.addToBackStack(null);
                    transaction.commit();
                })
                .setNegativeButton(R.string.button_close, (dialogInterface, i) -> dialogInterface.cancel());
        dialog.show();
    }
}
