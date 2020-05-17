package com.example.lap_shop.shahad_3asal.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lap_shop.shahad_3asal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eng_m on 8/19/2016.
 */
public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> dataList;
    private Spinner spinner;
    private int hint;

    public SpinnerAdapter(Context context, Spinner spinner, int resource, int hint) {
        this(context, spinner, resource, new ArrayList(), hint);
    }

    public SpinnerAdapter(Context context, Spinner spinner, int resource, List dataList, int hint) {
        super(context, resource, dataList);
        this.context=context;
        this.spinner=spinner;
        this.hint=hint;
        setDropDownViewResource(R.layout.spinner_item_simple);
        setDataList(dataList);
        spinner.setAdapter(this);
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
//                if(LanguageManager.getLanguage(ContactUsActivity.this).equals(LanguageManager.LANGUAGE_ARABIC)) {
//                    v.setRotationY(180);
//                }
        Log.e("getView",position+" - "+dataList.toString());
        if (position == getCount() || dataList.size()==1) {
            ((TextView)v.findViewById(android.R.id.text1)).setText("");
            ((TextView)v.findViewById(android.R.id.text1)).setHint(dataList.get((dataList.size()==1)?0:getCount())); //"Hint to be displayed"
        }else ((TextView)v.findViewById(android.R.id.text1)).setTextColor(Color.BLACK);
        return v;
    }
    @Override
    public int getCount() {
        return (dataList.size()==1)?1:dataList.size(); // you don't display last item. It is used as hint.
    }

    public void setDataList(List dataList){
        dataList.add(0,context.getString(hint));
        this.dataList=dataList;
        Log.e("setSelection", (dataList.size()-1)+" - "+dataList.get(dataList.size()-1));
        spinner.setSelection(0); //display hint
    }

    public boolean isValidSelection(){
        return spinner.getSelectedItemPosition()!=getCount();
    }

    public boolean isSelected(){
        return spinner.getSelectedItemPosition()!=0 && dataList.size()!=1;
    }

//    public static SpinnerAdapter setSpinnerAdapter(Context context, Spinner spinner, int hint) {
//        return setSpinnerAdapter(context, spinner, new ArrayList(), hint);
//    }
//    public static SpinnerAdapter setSpinnerAdapter(Context context, Spinner spinner, List dataList, int hint) {
//        dataList.add(context.getString(hint));
//        SpinnerAdapter adapter = new SpinnerAdapter(context, android.R.layout.simple_spinner_item, dataList, hint);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setSelection(dataList.size()-1); //display hint
//        adapter.setSpinner(spinner);
//        return adapter;
//    }

    public void setOnItemClickListener(final AdapterView.OnItemClickListener onItemClickListener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i<getCount())onItemClickListener.onItemClick(adapterView, view, i, l);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }
}
