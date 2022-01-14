package com.example.hostapp.utils;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.LinearLayout;
import android.widget.TableRow;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.hostapp.R;
import com.example.hostapp.mainMenu.MainActivity;
import com.example.hostapp.preSale.NewMailing;
import com.example.hostapp.preSale.PreSaleFragment;
import com.example.hostapp.preSale.PreSaleViewModel;
import com.example.hostapp.serverapi.DemoServerApi;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class UiUtils extends AppCompatActivity {

    FragmentTransaction fTrans;

    public static void ShowSimpleDialog(String title, String description, Context context){
        AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(description)
                .setPositiveButton(R.string.button_ok, (dialog1, which) -> dialog1.cancel()).create();
        dialog.show();
    }

    public void CreateNewMailingDialog(String title, Context context, Fragment fragment){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        Fragment preSaleFrag = new PreSaleFragment();

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
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String name1 = name.getText().toString();
                        NewMailing newMailing = new NewMailing(3, name.getText().toString(), status.getText().toString(), depart.getText().toString());
                        DemoServerApi.NEW_MAILINGS.add(newMailing);

                    }  });
        builder.show();;

    }

    public void refreshFragmentUI(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();
    }

}
