package com.example.lap_shop.shahad_3asal.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.activites.LoginRegister;
import com.example.lap_shop.shahad_3asal.dialogs.CustomDialog;


public class LogInManager {

    public  static final String KEY_LOGGED_IN = "loggedIn";
    private static final String KEY_ACTIVATED = "activated";
    private static final String KEY_USER_ID = "userID";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_PHONE = "userPhone";
    private static final String KEY_USER_ADDRESS = "UserType";
    private static final String KEY_USER_SESSION = "UserSession";

    public static boolean isUserLoggedIn(final Context context) {
        return isUserLoggedIn(context, false, false);
    }

    private static CustomDialog customDialog;

    public static boolean isUserLoggedIn(final Context context, boolean showLogin, boolean showActivation) {
        if (!DataManager.getBoolSetting(context, KEY_LOGGED_IN, false)) {
            if (showLogin) {
                if (context instanceof Activity) {
                    customDialog = new CustomDialog((Activity) context, context.getString(R.string.loginto)).
                            setOkButtonText(context.getString(R.string.ok)).
                            setOkButtonClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    customDialog.dismiss();
                                    context.startActivity(new Intent(context, LoginRegister.class));
                                }
                            }).
                            setCancelButtonText(context.getString(R.string.label_cancel)).
                            setCancelButtonClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    customDialog.dismiss();
                                    //  context.startActivity(new Intent(context, RegisterActivity.class));
                                }
                            });
                    customDialog.show();
                }
            }
            return false;
        } else if (!DataManager.getBoolSetting(context, KEY_ACTIVATED, false)) {
            if (showActivation && Constants.SHOW_ACTIVATION) {
                //TODO open activation screen
                DataManager.setBoolSetting(context, KEY_LOGGED_IN, false);
                //context.startActivity(new Intent(context, ActivationActivity.class));
            } else if (!Constants.SHOW_ACTIVATION) return true;
            return false;//TODO for nw
        } else {
            return true;
        }
    }

    public static String getUserID(Context context) {
        return DataManager.getStringSetting(context, KEY_USER_ID, "");
    }

    public static String getUserSession(Context context) {
        return DataManager.getStringSetting(context, KEY_USER_SESSION, "");
    }

    public static String getUserFullName(Context context) {
        return DataManager.getStringSetting(context, KEY_USER_NAME, "");
    }

    public static String getUserPhone(Context context) {
        return DataManager.getStringSetting(context, KEY_USER_PHONE, "");
    }

    public static String getUserAddress(Context context) {
        return DataManager.getStringSetting(context, KEY_USER_ADDRESS, "");
    }

    public static void setUserSession(Context context, String Session) {
        DataManager.setStringSetting(context, KEY_USER_SESSION, Session);
    }

    public static void setUserLoggedIn(Context context, boolean isActivated, String userID, String userName, String userPhone, String userAddress) {
        DataManager.setBoolSetting(context, KEY_LOGGED_IN, true);
        DataManager.setStringSetting(context, KEY_USER_ID, userID);
        DataManager.setStringSetting(context, KEY_USER_NAME, userName);
        DataManager.setStringSetting(context, KEY_USER_PHONE, userPhone);
        DataManager.setStringSetting(context, KEY_USER_ADDRESS, userAddress);
        DataManager.setBoolSetting(context, KEY_ACTIVATED, isActivated);
        if (!isActivated && Constants.SHOW_ACTIVATION) {
            //TODO open activation screen
            // context.startActivity(new Intent(context, ActivationActivity.class));
        }
    }

    public static void setEditProfileData(Context context, String userName, String userAddress) {
        DataManager.setStringSetting(context, KEY_USER_NAME, userName);
        DataManager.setStringSetting(context, KEY_USER_ADDRESS, userAddress);
    }

    public static void setUserLoggedOut(Context context) {
        DataManager.setBoolSetting(context, KEY_LOGGED_IN, false);
        DataManager.setStringSetting(context, KEY_USER_ID, "");
        DataManager.setStringSetting(context, KEY_USER_ADDRESS, "");
        DataManager.setStringSetting(context, KEY_USER_NAME, "");
        DataManager.setBoolSetting(context, KEY_ACTIVATED, false);
    }

    public static void setActivate(Context context) {
        DataManager.setBoolSetting(context, KEY_ACTIVATED, true);
    }

    public static void setUserLoggedInFromRegister(Context context, boolean isActivated, String userID, String userName, String userPhone) {
        DataManager.setBoolSetting(context, KEY_LOGGED_IN, true);
        DataManager.setStringSetting(context, KEY_USER_ID, userID);
        DataManager.setStringSetting(context, KEY_USER_NAME, userName);
        DataManager.setStringSetting(context, KEY_USER_PHONE, userPhone);
        DataManager.setBoolSetting(context, KEY_ACTIVATED, !isActivated);
        if (isActivated && Constants.SHOW_ACTIVATION) {
//            TODO open activation screen
            //   context.startActivity(new Intent(context, ActivationActivity.class));
        }
    }
}
