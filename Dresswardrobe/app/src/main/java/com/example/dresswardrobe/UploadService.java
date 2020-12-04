package com.example.dresswardrobe;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
/*import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;*/
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Tan on 2/26/2016.
 */
public abstract interface UploadService {


    @Multipart
    @POST("imagefile/upload")
    Call<ResponseBody> Upload(@Part MultipartBody.Part photo,
                              @Part("discription")RequestBody discription,
                              @Part("season")RequestBody season,
                              @Part("quality")RequestBody quality,
                              @Part("item_name")RequestBody item_name,
                              @Part("type")RequestBody type,
                              @Part("size")RequestBody size,
                              @Part("username")RequestBody username,
                              @Part("color")RequestBody color
                                );

   /* @Multipart
    @POST("/upload")
    public abstract Response uploadSync(@Part("file") TypedFile paramTypedFile);*/
}
