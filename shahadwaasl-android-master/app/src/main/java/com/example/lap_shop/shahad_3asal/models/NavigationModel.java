package com.example.lap_shop.shahad_3asal.models;

/**
 * Created by LAP_SHOP on 06/02/2017.
 */

public class NavigationModel {
    private String Text;
    private  int fragmentName;
    private int image;


    public NavigationModel(String Text,int fragmentName,int image){
        this.Text=Text;
        this.fragmentName=fragmentName;
        this.image=image;

    }

    public void setText(String text) {
        Text = text;
    }

    public String getText() {
        return Text;
    }

    public void setFragmentName(int fragmentName) {
        this.fragmentName = fragmentName;
    }

    public int getFragmentName() {
        return fragmentName;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }
}
