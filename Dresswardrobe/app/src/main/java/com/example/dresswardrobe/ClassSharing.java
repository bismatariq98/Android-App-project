package com.example.dresswardrobe;

import java.util.List;

public class ClassSharing {
    public int Item_id ;
    public String sharedby ;

    public List<String> sharedwith ;

    public ClassSharing(int item_id, String sharedby, List<String> sharedwith) {
        Item_id = item_id;
        this.sharedby = sharedby;
        this.sharedwith = sharedwith;
    }

    public void setItem_id(int item_id) {
        Item_id = item_id;
    }

    public void setSharedby(String sharedby) {
        this.sharedby = sharedby;
    }

    public void setSharedwith(List<String> sharedwith) {
        this.sharedwith = sharedwith;
    }

    public int getItem_id() {
        return Item_id;
    }

    public String getSharedby() {
        return sharedby;
    }

    public List<String> getSharedwith() {
        return sharedwith;
    }
}
