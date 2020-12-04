package com.example.dresswardrobe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.example.dresswardrobe.UploadService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tan on 2/26/2016.
 */
public class RestClient {
    private UploadService  uploadService;
    private String URL ="http://localhost:53706/api/imagefile/";

   private static Retrofit retrofit=null;
   public static Retrofit getClient()
   {
       HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
       interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       OkHttpClient client=new OkHttpClient.Builder().addInterceptor(interceptor).build();
       retrofit=new Retrofit.Builder()
       .baseUrl("http://localhost:53706/myserver/api/")
       .addConverterFactory(GsonConverterFactory.create())
       .client(client)
       .build();
       return retrofit;


   }
   /* public RestClient(){
        Gson localGson = new GsonBuilder().create();


        this.uploadService = ((UploadService)new RestAdapter.Builder()
                .setEndpoint(URL)
                .setConverter(new GsonConverter(localGson))
                .setRequestInterceptor(new RequestInterceptor()
                {
                    public void intercept(RequestInterceptor.RequestFacade requestFacade)
                    {   //By adding header to the request will allow us to debug into .Net code in server
                        if (URL.contains("localhost")) {
                            requestFacade.addHeader("Host", "localhost");
                        }
                    }
                })
                .build().create(UploadService.class));

    }*/



    public UploadService getService()
    {
        return this.uploadService;
    }


}
