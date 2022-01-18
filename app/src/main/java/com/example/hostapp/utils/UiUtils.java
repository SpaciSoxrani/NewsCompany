package com.example.hostapp.utils;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hostapp.R;
import com.example.hostapp.preSale.NewMailing;
import com.example.hostapp.preSale.PreSaleDetailsFragment;
import com.example.hostapp.preSale.PreSaleFragment;
import com.example.hostapp.profile.ProfileFragment;
import com.example.hostapp.serverapi.DemoServerApi;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class UiUtils extends AppCompatActivity {

    public static void ShowSimpleDialog(String title, String description, Context context){
        AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(description)
                .setPositiveButton(R.string.button_ok, (dialog1, which) -> dialog1.cancel()).create();
        dialog.show();
    }

    public static void CreateNewMailingDialog(String title, Context context, FragmentTransaction transaction){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LinearLayout lila1= new LinearLayout(context);
        lila1.setLayoutParams(new LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.WRAP_CONTENT));

        lila1.setOrientation(LinearLayout.VERTICAL);

        TextInputEditText name = new TextInputEditText(context);
        name.setHint(R.string.table_header_name);

        TextInputEditText status = new TextInputEditText(context);
        status.setHint(R.string.table_header_status);

        TextInputEditText depart = new TextInputEditText(context);
        depart.setHint(R.string.table_header_depart);

        lila1.addView(name);
        lila1.addView(status);
        lila1.addView(depart);

        builder
                .setTitle(title)
                .setView(lila1)
                .setPositiveButton(R.string.button_ok, (dialog, whichButton) -> {
                    if(!name.getText().toString().equals("") && !status.getText().toString().equals("") && !depart.getText().toString().equals("")){
                    NewMailing newMailing = new NewMailing(3, name.getText().toString(), status.getText().toString(), depart.getText().toString(), null);

                    DemoServerApi.NEW_MAILINGS.add(newMailing);
                        Fragment preSaleFragment = new PreSaleFragment();
                        transaction.replace(R.id.nav_host_fragment, preSaleFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }
                    else{
                        dialog.cancel();
                    }
                });
        builder.show();

    }

    public static void ShowEditMailingDialogue(String title, String description, Context context, NewMailing mailing, FragmentTransaction transaction){
        LinearLayout lila1= new LinearLayout(context);
        lila1.setLayoutParams(new LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.WRAP_CONTENT));
        lila1.setOrientation(LinearLayout.VERTICAL);

        TextInputEditText name = new TextInputEditText(context);
        name.setText(mailing.name);

        TextInputEditText status = new TextInputEditText(context);
        status.setText(mailing.status);

        TextInputEditText depart = new TextInputEditText(context);
        depart.setText(mailing.depart);

        lila1.addView(name);
        lila1.addView(status);
        lila1.addView(depart);

        AlertDialog.Builder dialog = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setView(lila1)
                .setMessage(description)
                .setPositiveButton(R.string.button_edit, (dialog1, whichButton) -> {
                    DemoServerApi.NEW_MAILINGS.remove(mailing);
                    String name1 = name.getText().toString();
                    NewMailing newMailing = new NewMailing(3, name.getText().toString(), status.getText().toString(), depart.getText().toString(), null);
                    DemoServerApi.NEW_MAILINGS.add(newMailing);
                    Fragment preSaleFragment = new PreSaleFragment();
                    transaction.replace(R.id.nav_host_fragment, preSaleFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                })
                .setNegativeButton(R.string.button_close, (dialogInterface, i) -> dialogInterface.cancel());

        dialog.show();
    }

    public static void ShowDeleteDialog(String title, int description, Context context, NewMailing mailing, FragmentTransaction transaction){
        LinearLayout lila1= new LinearLayout(context);
        lila1.setLayoutParams(new LinearLayout.LayoutParams(80, LinearLayout.LayoutParams.WRAP_CONTENT));
        lila1.setOrientation(LinearLayout.VERTICAL);

        ImageView img = new ImageView(context);
        img.setImageResource(R.drawable.ic_twotone_warning_24);
        img.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        AlertDialog.Builder dialog = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(description)
                .setView(img)
                .setPositiveButton(R.string.button_ok, (dialogInterface, i) -> {
                    DemoServerApi.NEW_MAILINGS.remove(mailing);

                    Fragment preSaleFragment = new PreSaleFragment();
                    transaction.replace(R.id.nav_host_fragment, preSaleFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    dialogInterface.cancel();

                })
                .setNegativeButton(R.string.button_close, (dialogInterface, i) -> dialogInterface.cancel());
        dialog.show();
    }

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
}
