package com.example.lap_shop.shahad_3asal.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.activites.ProductDetails;
import com.example.lap_shop.shahad_3asal.models.AddReviewModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.LogInManager;
import com.example.lap_shop.shahad_3asal.tools.ValidationManager;


public class AddReview extends Dialog {

    private Button okButton, cancelButton;
    final RatingBar ratingBar;
    private EditText comment;

    public AddReview(final Activity activity, final int id,final int where) {
        super(activity);
        setContentView(R.layout.dialog_add_review);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ratingBar = (RatingBar) findViewById(R.id.dialog_rate_ratingBar);
        okButton = (Button) findViewById(R.id.dialog_ok_button);
        cancelButton = (Button) findViewById(R.id.dialog_cancel_button);
        comment = (EditText) findViewById(R.id.comment);

        ratingBar.setRating(1);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidationManager.validReview(activity,comment)) {
                    dismiss();
                    Log.e("", LogInManager.getUserFullName(activity) + " " + comment.getText().toString() + " " + ((int) ratingBar.getRating()));
                    APIManager.AddReviews(activity, LogInManager.getUserFullName(activity)
                            , comment.getText().toString(), ((int) ratingBar.getRating()) + ""
                            , id, new APIManager.ResponseListener<AddReviewModel>() {
                        @Override
                        public void done(AddReviewModel dataModel) {
                            if (dataModel.getSuccess().equals("true")) {

                                Toast.makeText(getContext(), activity.getString(R.string.Succes), Toast.LENGTH_LONG).show();
                                startActivityWithCategoryId(activity, id + "", where);
                            }
                        }

                        @Override
                        public void failed(boolean fromConnection) {

                        }
                    });
                }else {
                    Toast.makeText(activity, activity.getString(R.string.msg_valid_review), Toast.LENGTH_SHORT).show();
                }

            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public AddReview setMessage(String text) {
        okButton.setText(text);
        return this;
    }

    public AddReview setOkButtonText(String text) {
        okButton.setText(text);
        return this;
    }

    public void cancle() {
        setCancelable(false);
    }

    public AddReview setCancelButtonText(String text) {
        cancelButton.setText(text);
        return this;
    }

    public AddReview setCancelButtonVisable(int v) {
        cancelButton.setVisibility(v);
        return this;
    }

    public AddReview setOkButtonVisable(int v) {
        okButton.setVisibility(v);
        return this;
    }


    public AddReview setOkButtonClickListener(View.OnClickListener okButtonClickListener) {
        okButton.setOnClickListener(okButtonClickListener);
        return this;
    }

    public AddReview setCancelButtonClickListener(View.OnClickListener cancelButtonClickListener) {
        cancelButton.setOnClickListener(cancelButtonClickListener);
        return this;
    }

    public static void startActivityWithCategoryId(Activity context, String id,int where) {
        Intent intent = new Intent(context, ProductDetails.class);
        intent.putExtra("Category_id", id);
        intent.putExtra("where",where);
        context.startActivity(intent);
        context.finish();
    }


}