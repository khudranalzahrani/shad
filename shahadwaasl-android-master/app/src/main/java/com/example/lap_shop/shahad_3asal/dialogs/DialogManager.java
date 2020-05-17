package com.example.lap_shop.shahad_3asal.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.text.InputFilter;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lap_shop.shahad_3asal.R;

import java.util.ArrayList;


public class DialogManager {

    public static void showDialog(Activity activity, String text){
        showDialog(activity, activity.getString(R.string.app_name), text);
    }
    public static void showDialog(Activity activity, String title, String text){
        AlertDialog alertDialog=new AlertDialog.Builder(activity, R.style.myAlertDialogStyle).setTitle(title).
                setMessage(text).
                setNegativeButton(activity.getString(R.string.label_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void showConfirmDialog(Activity activity, String text, final Confirm confirm){
        showConfirmDialog(activity, text, activity.getString(R.string.label_ok), activity.getString(R.string.label_cancel), confirm);
    }
    public static void showConfirmDialog(Activity activity, String text, String okText, String cancelText, final Confirm confirm){
        showConfirmDialog(activity, text, okText, cancelText, confirm, null);

    }
    public static void showConfirmDialog(Activity activity, String text, String okText, String cancelText, final Confirm okConfirm, final Confirm cancelConfirm){
        AlertDialog alertDialog=new AlertDialog.Builder(activity, R.style.myAlertDialogStyle).setTitle(activity.getString(R.string.app_name)).
                setMessage(text)
                .setPositiveButton(okText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(okConfirm!=null)okConfirm.ok();
                        else dialog.dismiss();
                    }
                })
                .setNegativeButton(cancelText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(cancelConfirm!=null)cancelConfirm.ok();
                        else dialog.dismiss();
                    }
                }).show();

    }

    public static void showChoiceDialog(final Activity activity, String text, String[] choices, final ConfirmChoice confirmChoice){
        if(text==null || text.length()==0)text=activity.getString(R.string.app_name);
        new AlertDialog.Builder(activity, R.style.myAlertDialogStyle)
                .setTitle(text)
                .setItems(choices, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmChoice.returnPosition(which);
                    }
                })
                .setNegativeButton(activity.getString(R.string.label_cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                }).show();

    }

    public static void showHTMLDialog(Activity activity, String title, String text){
        final TextView textView=new TextView(activity);
        textView.setText(Html.fromHtml(text));
        Linkify.addLinks(textView, Linkify.ALL);
        LinearLayout layout=new LinearLayout(activity);
        ScrollView sv = new ScrollView(activity);
        sv.addView(layout);
        layout.addView(textView);
        int margin=20;
        ((ViewGroup.MarginLayoutParams)textView.getLayoutParams()).setMargins(margin, margin, margin, margin);
        new AlertDialog.Builder(activity, R.style.myAlertDialogStyle).setTitle(title).
                setView(sv).
                setNegativeButton(activity.getString(R.string.label_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void showHTMLConfirmDialog(Activity activity, String title, String text, final Confirm confirm){
        final TextView textView=new TextView(activity);
        textView.setText(Html.fromHtml(text));
        Linkify.addLinks(textView, Linkify.ALL);
        LinearLayout layout=new LinearLayout(activity);
        ScrollView sv = new ScrollView(activity);
        sv.addView(layout);
        layout.addView(textView);
        int margin=20;
        ((ViewGroup.MarginLayoutParams)textView.getLayoutParams()).setMargins(margin, margin, margin, margin);
        new AlertDialog.Builder(activity, R.style.myAlertDialogStyle).setTitle(title).
                setView(sv).
                setPositiveButton(activity.getString(R.string.label_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirm.ok();
                        dialog.dismiss();
                    }
                }).show();
    }

    public static void showInputDialog(final Activity activity, String text, final ConfirmInput input){
        showInputDialog(activity, text, "", input);
    }
    public static void showInputDialog(final Activity activity, String text, String initialText, final ConfirmInput input){
        final EditText editText=new EditText(activity);
        int maxLength = 100;
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);
        editText.setFilters(FilterArray);
        editText.setText(initialText);
        if(text==null || text.length()==0)text=activity.getString(R.string.app_name);
        new AlertDialog.Builder(activity, R.style.myAlertDialogStyle)//.setTitle(context.getString(R.string.app_name))
                .setTitle(text)
                .setView(editText)
//			.setMessage(text)
                .setPositiveButton(activity.getString(R.string.label_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        input.returnInput(editText.getText().toString());
                    }
                })
                .setNegativeButton(activity.getString(R.string.label_cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        dialog.dismiss();
                    }
                }).show();
        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
//		((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE)).showInputMethodPicker()
    }
    public static void showTwoInputDialog(final Activity activity, String text, String text1Hint, String text2Hint, final ConfirmInput2 input){
        final EditText editText1=new EditText(activity);
        editText1.setHint(text1Hint);
        final EditText editText2=new EditText(activity);
        editText2.setHint(text2Hint);
        LinearLayout layout=new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editText1);
        layout.addView(editText2);
        if(text==null || text.length()==0)text=activity.getString(R.string.app_name);
        new AlertDialog.Builder(activity, R.style.myAlertDialogStyle)//.setTitle(activity.getString(R.string.app_name))
                .setTitle(text)
                .setView(layout)
//			.setMessage(text)
                .setPositiveButton(activity.getString(R.string.label_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText1.getWindowToken(), 0);
//					if(editText1.getText().length()==0 || editText2.getText().length()==0){
//						showToast(activity, RS.getString(activity, R.string.msg_incomplete_data));
//					}else{
                        input.returnInput(editText1.getText().toString(), editText2.getText().toString());
                        dialog.dismiss();
//					}
                    }
                }).setNegativeButton(activity.getString(R.string.label_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText1.getWindowToken(), 0);
                dialog.dismiss();
            }
        }).show();
        ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
//		((InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE)).showInputMethodPicker()
    }

    public static void showMultipleChoiceDialog(final Activity activity, String text, String[] choices, final ConfirmChoices confirmChoices){
        final ArrayList<Integer> selectedItems = new ArrayList<Integer>();  // Where we track the selected items
        if(text==null || text.length()==0)text=activity.getString(R.string.app_name);
        new AlertDialog.Builder(activity, R.style.myAlertDialogStyle)
                .setTitle(text)
                .setMultiChoiceItems(choices, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    selectedItems.add(which);
                                } else if (selectedItems.contains(which)) {
                                    selectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                .setPositiveButton(R.string.label_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        confirmChoices.returnpositions(selectedItems);
                    }
                })
                .setNegativeButton(R.string.label_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).show();
    }

    public static int showNotification(Context context, String message) {
        return showNotification(context, context.getString(R.string.app_name), message, 0, null, false);
    }
    public static final String NOTIFICATION_ID="notificationID";
    public static int showNotification(Context context, String title, String message, Intent resultIntent, boolean onGoing){
        return showNotification(context, title, message, 0, resultIntent, onGoing);
    }
    public static int showNotification(Context context, String title, String message, int id, Intent resultIntent, boolean onGoing){
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
//                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setOngoing(onGoing);
        int notificationID=id;
        if(notificationID==0)notificationID=(int)System.currentTimeMillis();
        if(resultIntent!=null){
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            resultIntent.putExtra(NOTIFICATION_ID, notificationID);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, resultIntent, PendingIntent.FLAG_ONE_SHOT);
            notificationBuilder=notificationBuilder.setContentIntent(pendingIntent);
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID, notificationBuilder.build());
        return notificationID;
    }
    public static void removeNotification(Context context, int notificationID){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(notificationID);
    }

    public static void showToast(Context context, int text){
        showToast(context, context.getString(text));
    }
    public static void showToast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

//    public static void showSnackBar(View view, String text, String action, View.OnClickListener onClickListener){
//        Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction(action, onClickListener).show();
//    }

    public static ProgressDialog showProgressDialog(Context context, String title, String body, boolean cancelable, final DialogListener dialogListener) {
        ProgressDialog progressDialog = ProgressDialog.show(context, title, body, false, cancelable);
        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialogListener.onDialogDismiss();
            }
        });
//        ProgressDialog progressDialog=new ProgressDialog(context, R.style.MyAlertDialogStyle);
//        progressDialog.setTitle(title);
//        progressDialog.setMessage(body);
//        progressDialog.setIndeterminate(false);
//        progressDialog.setCancelable(cancelable);
//        progressDialog.show();
        return progressDialog;
    }

    public static interface Confirm{
        public void ok();
    }
    public static interface DialogListener {
        public void onDialogDismiss();
    }
    public static interface ConfirmChoice{
        public void returnPosition(int position);
    }
    public static interface ConfirmInput{
        public void returnInput(String text);
    }
    public static interface ConfirmRate{
        public void returnRate(float rate);
    }
    public static interface ConfirmInput2{
        public void returnInput(String text1, String text2);
    }
    public static interface ConfirmChoices{
        public void returnpositions(ArrayList<Integer> positions);
    }

    public static void showRatingDialog(Activity activity, final ConfirmRate confirmRate) {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(activity, R.style.myAlertDialogStyle);
        final RatingBar rating = new RatingBar(activity);
        rating.setNumStars(5);
        rating.setStepSize(1.0f);
        rating.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout parent = new LinearLayout(activity);
        parent.setGravity(Gravity.CENTER);
        parent.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        parent.addView(rating);

        // popDialog.setIcon(android.R.drawable.btn_star_big_on);
        popDialog.setTitle(activity.getString(R.string.app_name));
        popDialog.setView(parent);

        // Button OK
        popDialog.setPositiveButton(R.string.label_ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        confirmRate.returnRate(rating.getRating());
                        dialog.dismiss();
                    }
                }).setNegativeButton(R.string.label_cancel,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        popDialog.create();
        popDialog.show();
    }
}
