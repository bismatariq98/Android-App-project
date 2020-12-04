package com.example.dresswardrobe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Locale;

public class HRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context _context;
    private List<Images> _data;


    public HRecyclerViewAdapter(Context _context, List<Images> _data) {
        this._context = _context;
        this._data = _data;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View _view;
        LayoutInflater _inflator = LayoutInflater.from(_context);
        _view = _inflator.inflate(R.layout.cardview_item,parent,false);

        return new RecyclerViewAdapter.MyViewHolder(_view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, final int position) {

        holder._tv.setText(_data.get(position).getItem_Name());
        holder._imageview.setImageBitmap(_data.get(position).getItem_Image());
        holder._imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_context,DetailsA.class);
                String itt = _data.get(position).getItem_Name();
                Bitmap _bitmap = _data.get(position).getItem_Image();
                ByteArrayOutputStream _bs = new ByteArrayOutputStream();
                _bitmap.compress(Bitmap.CompressFormat.JPEG,60,_bs);
                intent.putExtra("byteArray",_bs.toByteArray());
                intent.putExtra("itemname",_data.get(position).getItem_Name());
                intent.putExtra("season",_data.get(position).getSeason());
                intent.putExtra("clr",_data.get(position).getColor());
                intent.putExtra("username",_data.get(position).getUserName());
                intent.putExtra("itemid",_data.get(position).getITEM_ID());
                _context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CardView _cardview;
        TextView _tv;
        ImageView _imageview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            _tv = (TextView) itemView.findViewById(R.id.txt1);
            _imageview = (ImageView) itemView.findViewById(R.id.img);

        }

    }

}
