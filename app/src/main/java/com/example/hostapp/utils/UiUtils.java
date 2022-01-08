package com.example.hostapp.utils;

import android.content.Context;
import androidx.appcompat.app.AlertDialog;

import com.example.hostapp.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class UiUtils {

    public static void ShowSimpleDialog(String title, String description, Context context){
        AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(description)
               .setPositiveButton(R.string.button_ok, (dialog1, which) -> dialog1.cancel()).create();
        dialog.show();
    }
}
