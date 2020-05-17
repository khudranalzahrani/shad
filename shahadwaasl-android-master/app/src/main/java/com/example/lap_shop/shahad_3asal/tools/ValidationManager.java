package com.example.lap_shop.shahad_3asal.tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.lap_shop.shahad_3asal.R;

import java.util.regex.Pattern;

/**
 * Created by eng_m on 8/3/2016.
 */
public class ValidationManager {

    public static boolean validateEmptyFields(Context context, EditText... fieldsEditText) {
        boolean valid = true;
        for (int i = 0; i < fieldsEditText.length; i++) {
            if (!validateEmptyField(context, fieldsEditText[i])) valid = false;
        }
        return valid;
    }



    public static boolean validatesmallFields(Context context, EditText... fieldsEditText) {
        boolean valid = true;
        for (int i = 0; i < fieldsEditText.length; i++) {
            if (!validatedatasmallField(context, fieldsEditText[i])) valid = false;
        }
        return valid;
    }

    public static boolean isValidName(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        boolean isValidComment = true;
        if (value.length() < 4) {
            isValidComment = false;
        }
        if (!isValidComment)
            editText.setError(context.getString(R.string.msg_enter_valid_name_at_lest_4ch));
        return isValidComment;
    }


    public static boolean isValidLoginPass(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        boolean isValidComment = true;
        if (value.length() ==0 ) {
            isValidComment = false;
        }
        if (!isValidComment){
        editText.setError(null);
        Toast.makeText(context, context.getString(R.string.loginPass), Toast.LENGTH_SHORT).show();}
        return isValidComment;
    }

    public static boolean isValidVerificationCode(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        boolean isValidComment = true;
        if (value.length() < 4 ) {
            isValidComment = false;
        }
        if (!isValidComment){
            editText.setError(null);
            Toast.makeText(context, context.getString(R.string.registerVerify), Toast.LENGTH_SHORT).show();}
        return isValidComment;
    }

    public static boolean isValidRegisterPass(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        boolean isValidComment = true;
        if (value.length() <8 ) {
            isValidComment = false;
        }
        if (!isValidComment){
            editText.setError(null);
            Toast.makeText(context, context.getString(R.string.registerPass), Toast.LENGTH_SHORT).show();}
        return isValidComment;
    }

    public static boolean isAlpha(Context context, EditText editText, String name) {
        char[] chars = name.toCharArray();
        boolean containnyum = true;

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                containnyum = false;
            }
        }
        if (!containnyum) editText.setError(context.getString(R.string.msg_enter_valid_name));

        return containnyum;
    }

    private static boolean validatedatasmallField(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        if (value.length() == 0) {
            String hint = "";
            if (editText.getParent() instanceof TextInputLayout) {
                TextInputLayout parent = (TextInputLayout) editText.getParent();
                hint = parent.getHint().toString();
            } else if (editText.getParent().getParent() instanceof TextInputLayout) {
                TextInputLayout parent = (TextInputLayout) editText.getParent().getParent();
                hint = parent.getHint().toString();
            } else {
                hint = editText.getHint().toString();
            }
            editText.setError(context.getString(R.string.please_enter) + " " + hint + context.getString(R.string.msg_enter_valid));
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    private static boolean validateEmptyField(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        if (value.length() == 0) {
            String hint = "";
            if (editText.getParent() instanceof TextInputLayout) {
                TextInputLayout parent = (TextInputLayout) editText.getParent();
                hint = parent.getHint().toString();
            } else if (editText.getParent().getParent() instanceof TextInputLayout) {
                TextInputLayout parent = (TextInputLayout) editText.getParent().getParent();
                hint = parent.getHint().toString();
            } else {
                hint = editText.getHint().toString();
            }
            editText.setError(context.getString(R.string.please_enter) + " " + hint);
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    public static boolean isValidEmailOrPhone(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        boolean isValidEmail = Patterns.EMAIL_ADDRESS.matcher(value).matches();
        boolean isValidPhone = Patterns.PHONE.matcher(value).matches();
        if (!isValidEmail && !isValidPhone) {
            editText.setError(context.getString(R.string.msg_enter_valid_email_or_phone));
            return false;
        } else return true;
    }

    public static boolean isValidEmail(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        boolean isValidEmail = Patterns.EMAIL_ADDRESS.matcher(value).matches();
        if (!isValidEmail) editText.setError(context.getString(R.string.msg_enter_valid_email));
        return isValidEmail;
    }

    public static boolean isemptySpinner(Context context, Spinner... spinner) {

        boolean valid = true;
        for (int i = 0; i < spinner.length; i++) {
            if (spinner[i].getSelectedItemPosition() == 0) {
                valid = false;
                Toast.makeText(context, context.getString(R.string.please_enter) + " " + spinner[i].getAdapter().getItem(0).toString(), Toast.LENGTH_LONG).show();
                //  spinner[i].setError(context.getString(R.string.please_enter) + " " + hint);
            } else {
                valid = true;
            }

        }
        return valid;
    }

    public static boolean isValidComment(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        boolean isValidComment = true;
        if (value.length() < 10) {
            isValidComment = false;
        }

        if (!isValidComment) editText.setError(context.getString(R.string.msg_enter_valid_comment));
        return isValidComment;
    }

    public static boolean validReview(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        boolean isValidComment = true;
        if (value.length()< 10 ){
            isValidComment=false;
        }
        return isValidComment;
    }

    public static boolean isValidComment_review(Context context, EditText editText, RatingBar rateBar) {
        String value = editText.getText().toString().trim();
        boolean isValidComment = true;
        int x = (int) rateBar.getRating();
        if (value.length() < 10 && x == 0) {
            isValidComment = false;
        }

        if (!isValidComment)
        //editText.setError(context.getString(R.string.msg_enter_valid_comment));
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage(R.string.msg_validate_rate);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
        return isValidComment;
    }


    public static boolean isValidPassWord(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        boolean isValidComment = true;
        if (value.length() < 8) {
            isValidComment = false;
        }
        if (!isValidComment) editText.setError(context.getString(R.string.msg_enter_valid_pass));
        return isValidComment;
    }

    public static boolean isValidPostCode(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        boolean isValidCode = true;
        if (value.length() != 5) {
            isValidCode = false;
        }
        if (!isValidCode)
            Toast.makeText(context, context.getString(R.string.msg_enter_valid_postCode), Toast.LENGTH_SHORT).show();
            editText.setError(null);
        return isValidCode;
    }


    public static boolean isValidPhone(Context context, EditText editText) {
        String value = editText.getText().toString().trim();
        boolean isValidPhone = Patterns.PHONE.matcher(value).matches();
        if (isValidPhone) editText.setError(context.getString(R.string.msg_enter_valid_phone));
        return isValidPhone;
    }

    public static boolean isSamePassword(Context context, EditText passwordEditText, EditText password2EditText) {
        boolean isSamePassword = passwordEditText.getText().toString().equals(password2EditText.getText().toString());
        if (!isSamePassword) {
            passwordEditText.setError(context.getString(R.string.msg_enter_same_password));
            password2EditText.setError(context.getString(R.string.msg_enter_same_password));
        }
        return isSamePassword;
    }

    public static boolean validRating(Context context, RatingBar ratingBar) {
        boolean rate = false;
        int x = (int) ratingBar.getRating();
        if (x == 0) {
           /* AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage(R.string.msg_validate_rate);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();*/

            Toast.makeText(context, R.string.msg_validate_rate, Toast.LENGTH_SHORT).show();
        }
        return rate;

    }


    public static boolean validphone(Context context, EditText editText) {
        boolean out = false;
        String content = editText.getText().toString();
        String pattern = "^(009665|9665|\\+9665|05|5)?(5|0|3|6|4|9|1|8|7)([0-9]{7})$";  //0505330609

        if (Pattern.matches(pattern, content)) {
            out = true;
        } else {
            editText.setError(context.getString(R.string.msg_enter_valid_phone));
        }
        return out;


    }

    public static boolean validZipCode(Context context, EditText editText) {
        boolean out = false;
        String content = editText.getText().toString();
        if (content.length() != 5) {
            out = false;
        } else {
            out = true;
        }
        return out;
    }

}
