package com.example.lap_shop.shahad_3asal.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lap_shop.shahad_3asal.R;



public class CustomDialog extends Dialog {

    private Button okButton, cancelButton;
    private TextView messageTextView;

    public CustomDialog(Activity activity, String message) {
        super(activity);
        setContentView(R.layout.dialog_custom);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        okButton = (Button) findViewById(R.id.dialog_ok_button);
        cancelButton = (Button) findViewById(R.id.dialog_cancel_button);
        messageTextView = (TextView) findViewById(R.id.dialog_message_textView);

        messageTextView.setText(message);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public CustomDialog setMessage(String text) {
        okButton.setText(text);
        return this;
    }

    public CustomDialog setOkButtonText(String text) {
        okButton.setText(text);
        return this;
    }

    public void cancle() {
        setCancelable(false);
    }

    public CustomDialog setCancelButtonText(String text) {
        cancelButton.setText(text);
        return this;
    }

    public CustomDialog setCancelButtonVisable(int v) {
        cancelButton.setVisibility(v);
        return this;
    }

    public CustomDialog setOkButtonVisable(int v) {
        okButton.setVisibility(v);
        return this;
    }

    public CustomDialog settextsize() {
        messageTextView.setTypeface(Typeface.DEFAULT_BOLD);
        return this;
    }

    public CustomDialog setOkButtonClickListener(View.OnClickListener okButtonClickListener) {
        okButton.setOnClickListener(okButtonClickListener);
        return this;
    }

    public CustomDialog setCancelButtonClickListener(View.OnClickListener cancelButtonClickListener) {
        cancelButton.setOnClickListener(cancelButtonClickListener);
        return this;
    }
}