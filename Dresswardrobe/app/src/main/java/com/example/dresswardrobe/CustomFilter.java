package com.example.dresswardrobe;
import android.widget.Filter;
import java.util.ArrayList;

/**
 * Created by Atif on 11/3/2018.
 * devofandroid.blogspot.com
 */

public class CustomFilter extends Filter{
    RecyclerViewAdapter adapter;
    ArrayList<Images> filterList;
    public CustomFilter(ArrayList<Images> filterList, RecyclerViewAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;
    }
    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Images> filteredPlayers=new ArrayList<>();
            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getItem_Name().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList.get(i));
                }
            }
            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter= (RecyclerViewAdapter) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}