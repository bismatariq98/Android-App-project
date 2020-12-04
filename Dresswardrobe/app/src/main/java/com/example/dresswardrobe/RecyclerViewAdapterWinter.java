package com.example.dresswardrobe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapterWinter extends RecyclerView.Adapter<RecyclerViewAdapterWinter.MyViewHolder> {

    private Context _context;
    private List<Images> _data;

    ImageLoader imageLoader;

    public RecyclerViewAdapterWinter(Context _context, List<Images> _data) {
        this._context = _context;
        this._data = _data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View _view;
        LayoutInflater _inflator = LayoutInflater.from(_context);
        _view = _inflator.inflate(R.layout.cardview_itemwinter,parent,false);

        return new MyViewHolder(_view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder._tv.setText(_data.get(position).getItem_Name());
       // holder.previewImage = _data.get(position).getItem_Image();
        holder.previewImage.setImageUrl(_data.get(position).getUrl(), _data.get(position).getLoader());
        holder._tv1.setText(_data.get(position).getUserName());

    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView _cardview;
        TextView _tv,_tv1;
        NetworkImageView previewImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            _tv = (TextView) itemView.findViewById(R.id.txt11);
            previewImage =  itemView.findViewById(R.id.img);
            _tv1 = (TextView) itemView.findViewById(R.id.txt22);
        }
    }
}
