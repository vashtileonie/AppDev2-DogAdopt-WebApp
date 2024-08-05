package com.doggo.dogadopt;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class LoadingDialog {
    private Activity activity;
    private AlertDialog dialog;

    public LoadingDialog(Activity myActivity){
        activity = myActivity;
    }

    public void startLoadingAnimation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog, null));

        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

    }

    public void startLoadingAnimation(String text){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.loading_dialog, null);
        TextView textview = dialogView.findViewById(R.id.loading_text);
        textview.setText(text);

        builder.setView(dialogView);

        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

    }

    public void dismissAnimation(){
        dialog.dismiss();
    }

}
