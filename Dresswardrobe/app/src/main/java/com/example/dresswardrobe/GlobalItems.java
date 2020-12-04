package com.example.dresswardrobe;

import android.app.Application;
import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.List;


public class GlobalItems implements Serializable {


    public GlobalItems() {
    }
    public String UserTo;
    public String ITEM_NAME;
    public String SEASON;
    public String SIZE;
    public String COLOR;
    public String TYPE;
    public int QUANTITY;
    public String IMAGE_PATH;
    public String USERNAME;
    public int ITEM_ID;
   public ImageView i;

    public GlobalItems(int ITEM_ID, String ITEM_NAME, String SEASON, String SIZE, String COLOR, String TYPE, int QUANTITY, String IMAGE_PATH, String USERNAME) {
        this.ITEM_ID = ITEM_ID;
        this.ITEM_NAME = ITEM_NAME;
        this.SEASON = SEASON;
        this.SIZE = SIZE;
        this.COLOR = COLOR;
        this.TYPE = TYPE;
        this.QUANTITY = QUANTITY;
        this.IMAGE_PATH = IMAGE_PATH;
        this.USERNAME = USERNAME;
        this.QUANTITY = QUANTITY;
        this.UserTo = UserTo;
        this.i = i;
    }

    public void setI(ImageView i) {
        this.i = i;
    }

    public ImageView getI() {
        return i;
    }

    public GlobalItems(int ITEM_ID,String ITEM_NAME, String SEASON, String SIZE, String COLOR, String TYPE, int QUANTITY, String IMAGE_PATH, String USERNAME, String UserTo) {
        this.ITEM_ID = ITEM_ID;
        this.ITEM_NAME = ITEM_NAME;
        this.SEASON = SEASON;
        this.SIZE = SIZE;
        this.COLOR = COLOR;
        this.TYPE = TYPE;
        this.QUANTITY = QUANTITY;
        this.IMAGE_PATH = IMAGE_PATH;
        this.USERNAME = USERNAME;
        this.UserTo = UserTo;
    }
    public String getUserTo() {return UserTo ;}
    public int getITEM_ID() {
        return ITEM_ID;
    }
    public void setUserTo(String UserTo) {this.UserTo = UserTo; }
    public void setITEM_ID(int ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public String getITEM_NAME() {
        return ITEM_NAME;
    }

    public void setITEM_NAME(String ITEM_NAME) {
        this.ITEM_NAME = ITEM_NAME;
    }

    public String getSEASON() {
        return SEASON;
    }

    public void setSEASON(String SEASON) {
        this.SEASON = SEASON;
    }

    public String getSIZE() {
        return SIZE;
    }

    public void setSIZE(String SIZE) {
        this.SIZE = SIZE;
    }

    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public String getIMAGE_PATH() {
        return IMAGE_PATH;
    }

    public void setIMAGE_PATH(String IMAGE_PATH) {
        this.IMAGE_PATH = IMAGE_PATH;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }
}
