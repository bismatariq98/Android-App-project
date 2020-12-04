package com.example.dresswardrobe;

import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader;

public class Images {
    private String UserName;
    private int ITEM_ID;
    private String Item_Name;
    private Bitmap Item_Image;
    private String Season;
    private String Size;
    private String Color;
    private String Type;
    private String url;
    private ImageLoader loader;

    public Images(String username,int ITEM_ID, int i, String item_name, Bitmap decodedImage, String season, String size, String color, String type) {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageLoader getLoader() {
        return loader;
    }

    public void setLoader(ImageLoader loader) {
        this.loader = loader;
    }

    public Images(int quantity, String url, ImageLoader imageLoader) {
    }


    public Images(int ITEM_ID, String UserName, int Category_ID, String Item_Name, Bitmap Item_Image, String Season, String Size, String Color, String Type)
    {
        this.ITEM_ID = ITEM_ID;
        this.UserName = UserName;
        this.ITEM_ID = ITEM_ID;
        this.Item_Name = Item_Name;
        this.Item_Image = Item_Image;
        this.Season = Season;
        this.Size = Size;
        this.Color = Color;
        this.Type = Type;
    }
    public Images(String name, String url, ImageLoader loader)
    {

        this.Item_Name = name;
        this.url=url;
        this.loader=loader;

    }

    public String getUserName() {
        return UserName;
    }

    public int getITEM_ID() {
        return ITEM_ID;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public Bitmap getItem_Image() {
        return Item_Image;
    }

    public String getSeason() {
        return Season;
    }

    public String getSize() {
        return Size;
    }

    public String getColor() {
        return Color;
    }

    public String getType() {
        return Type;
    }

    public void setID(int ITEM_ID) {
        this.ITEM_ID = ITEM_ID;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setCategory_ID(int category_ID) {
        ITEM_ID = category_ID;
    }

    public void setItem_Name(String item_Name) {
        Item_Name = item_Name;
    }

    public void setItem_Image(Bitmap item_Image) {
        Item_Image = item_Image;
    }

    public void setSeason(String season) {
        Season = season;
    }

    public void setSize(String size) {
        Size = size;
    }

    public void setColor(String color) {
        Color = color;
    }

    public void setType(String type) {
        Type = type;
    }
}
