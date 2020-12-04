package com.example.dresswardrobe;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second extends Fragment {

    private RecyclerView recyclerView;
    List<Images> img;
    String URL="http://192.168.43.18/myserver";


    public Second() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        img = new ArrayList<>();

        fetchData();
        recyclerView = view.findViewById(R.id.SecRecview);
        RecyclerViewAdapterWinter _myadapter  = new RecyclerViewAdapterWinter(getContext().getApplicationContext(),img);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(),2));
        recyclerView.setAdapter(_myadapter);



        return view;
    }
    List<Example> adslist;
    public String url;
    public int Quantity;
    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Example>> call = api.getImage();

        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                adslist = response.body();

                Quantity = adslist.get(0).getQuantity();
                String Season = adslist.get(0).getSeason();
                String Size = adslist.get(0).getSize();
                String Image = adslist.get(0).getImagePath();


                showImage();

                //  Toast.makeText(WinterActivty.this, ""+Quantity+"\n"+Season+"\n"+Size+"\n"+Image, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {

                Toast.makeText(getActivity().getApplicationContext(), ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    NetworkImageView previewImage;
    ImageLoader imageLoader;
    public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }
    private void showImage()
    {
        //String finalImagePath=charRemoveAt(imagePath,0);
        // Toast.makeText(this, ""+finalImagePath, Toast.LENGTH_SHORT).show();


        //  File imgFile = new File(URL+finalImagePath);
        Toast.makeText(getContext().getApplicationContext(), ""+adslist.size(), Toast.LENGTH_LONG).show();



        imageLoader = CustomVolleyRequest.getInstance(getActivity().getApplicationContext())
                .getImageLoader();
        previewImage.setImageUrl(url, imageLoader);
        for(int i=0;i<adslist.size();i++)
        {
            url=URL+charRemoveAt(adslist.get(i).getImagePath(),0);

            imageLoader.get(url, ImageLoader.getImageListener(previewImage
                    ,0,android.R.drawable
                            .ic_dialog_alert));
            img.add(new Images(adslist.get(i).getQuantity(),url,imageLoader));
        }

    }

}
