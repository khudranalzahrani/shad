package com.example.lap_shop.shahad_3asal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lap_shop.shahad_3asal.R;
import com.example.lap_shop.shahad_3asal.models.ContactModel;
import com.example.lap_shop.shahad_3asal.tools.APIManager;
import com.example.lap_shop.shahad_3asal.tools.LogInManager;
import com.example.lap_shop.shahad_3asal.tools.ValidationManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class contactus extends Fragment {
    View rootview;
    private static Fragment fragment;
    EditText name, comment, email;

    public static Fragment newInstance() {

        fragment = new contactus();
        return fragment;
    }

    public contactus() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_contactus, container, false);
        name = rootview.findViewById(R.id.name);
        comment = rootview.findViewById(R.id.comment);
        email = rootview.findViewById(R.id.email);
        rootview.findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                sendComment();
            }
        });
        return rootview;
    }

    public void sendComment() {

        if (ValidationManager.validateEmptyFields(getActivity(), name, comment, email) && ValidationManager.isValidEmail(getContext(), email) && ValidationManager.isValidComment(getActivity(), comment)
        && ValidationManager.isValidName(getActivity(),name)) {
            APIManager.SendContact(getActivity(), name.getText().toString(), email.getText().toString(), comment.getText().toString(), LogInManager.getUserSession(getActivity()), new APIManager.ResponseListener<ContactModel>() {
                @Override
                public void done(ContactModel dataModel) {
                    if (dataModel.getSuccess()) {
                        name.setText("");
                        comment.setText("");
                        email.setText("");
                        Toast.makeText(getActivity(), getString(R.string.send_Comment), Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void failed(boolean fromConnection) {

                }
            });

        }


    }


}
