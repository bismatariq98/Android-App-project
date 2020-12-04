package com.example.dresswardrobe;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
public class AddSingleImage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


Context c = this;
    String stringImg = "";
    byte[] bytes;

    Uri uri;


    String picpath = "";

String ID = "0";
    int _id = 1;
    String username;
    String _seasonSelected;
    String _categorySelected;
    String encodedImage;
    String _size;
    String _encodedimg;
    String _colors;
    String type = "";
    int qty;
    private static final int PICK_CAMERA_IMAGE = 1;
    private String imagePath="";
    private File savedFilewDestination;
    private RestClient restClient;
    ProgressDialog mProgress;
    String itemname;
    private static final int PICK_FROM_CAMERA = 1;
    private static final int CROP_FROM_CAMERA = 2;
    private static final int PICK_FROM_FILE = 3;
    final String[] items = new String[]{ "Select From Gallery"};
    ImageView _imgupload;
    private int PICK_IMAGE_REQUEST = 1;
    final Context ctx = this;
    GlobalItems g = new GlobalItems();
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_single_image);
        Intent intent = getIntent();
        username = intent.getStringExtra("uname");
        final EditText tname = findViewById(R.id.itemname);
        final EditText _color = findViewById(R.id.tvcolor);
        final EditText qtyy = findViewById(R.id.spinQuantity);
        final Spinner _spinnerCategory = findViewById(R.id.spinCategory);
        final Spinner _spinnersize = findViewById(R.id.spinSize);
        _imgupload = findViewById(R.id.imgup);
        Button _select = findViewById(R.id.selectBtn);
        final Spinner _spinnerSeason = findViewById(R.id.spinSeason);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        this.encodedImage = "";
        ArrayAdapter<CharSequence> _sadapter = ArrayAdapter.createFromResource(this, R.array.Seasons, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> _cadapter = ArrayAdapter.createFromResource(this, R.array.Categories, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> _sizeadapter = ArrayAdapter.createFromResource(this, R.array.Size, android.R.layout.simple_spinner_item);
        _sizeadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        _cadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        _sadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
         _spinnersize.setAdapter(_sizeadapter);
        _spinnersize.setOnItemSelectedListener(this);
        _spinnerCategory.setAdapter(_cadapter);
        _spinnerCategory.setOnItemSelectedListener(this);
        _spinnerSeason.setAdapter(_sadapter);
        _spinnerSeason.setOnItemSelectedListener(this);



        Button _btnupload = findViewById(R.id.UploadBtn);

        _btnupload.setOnClickListener(v -> {
            // loginRetrofit2Api(1,"ali",1,"asd",01234,"as","large","Yellow","Casual");
            itemname = tname.getText().toString();
            _colors = _color.getText().toString();
            _seasonSelected=_spinnerSeason.getSelectedItem().toString();
            _size=_spinnersize.getSelectedItem().toString();
            _categorySelected=_spinnerCategory.getSelectedItem().toString();
            String kk = qtyy.getText().toString();
            qty = Integer.parseInt(kk);

            _imgupload.buildDrawingCache();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapDrawable drawable = (BitmapDrawable) _imgupload.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            bitmap.compress(Bitmap.CompressFormat.PNG, 60, baos);
            byte[] imageBytes = baos.toByteArray();
            String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);


             g = new GlobalItems(-100,itemname,_seasonSelected, _size, _colors , _categorySelected ,qty , imageString, username);
            insertitem();







            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    final  Intent ii = new Intent(c,AllowSharing.class);
                    ii.putExtra("id", ID);
                    ii.putExtra("uname",username);
                    startActivity(ii);
                }
            }, 3000);

            //uploadImage();
           /* Log.d("Save Button Clicked", "");

            JSONObject payload = new JSONObject();

            try {
//                    payload.put("image_id", 1);
//                    payload.put("image", encodedImage);
                payload.put("ITEM_ID",1);
                payload.put("USER_NAME", "ali");
                payload.put("ITEM_NAME", _itemsName);
                payload.put("Image", _encodedimg);
                payload.put("QUANTITY", 1);
                payload.put("SEASON", _seasonSelected);
                payload.put("SIZE", _size);
                payload.put("COLOR", "s");
                payload.put("TYPE", "n");
//                    payload.put("PHOTO","ASDASDSAAS");
                CallResult result = WCFHandler.post("items/DoCreate", payload.toString());
                Log.d("Call Response = ", result.jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
        });
        _select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();

            }
        });
                }

    private void ConvertToString(Uri uri) {
        stringImg = uri.toString();
        Log.d("data", "onActivityResult: uri" + stringImg);
        try {
            InputStream inputStream = this.getContentResolver().openInputStream(uri);
            bytes = getBytes(inputStream);
            Log.d("data", "onActivityResult: bytes size=" + bytes.length);
            Log.d("data", "onActivityResult: Base64string=" + Base64.encodeToString(bytes, Base64.DEFAULT));
            picpath = Base64.encodeToString(bytes, Base64.DEFAULT);
            stringImg = picpath;
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("error", "onActivityResult:" + e.toString());
        }
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }


    public Bitmap base64ToImg(String uri) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageBytes = baos.toByteArray();
        imageBytes = Base64.decode(uri, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//        image.setImageBitmap(decodedImage);

        return decodedImage;
    }



    private void uploadImage(){
        try
        {
            //Toast.makeText(ctx, ""+imagePath, Toast.LENGTH_LONG).show();
            File newFile=new File(imagePath);
            RequestBody photoConten=RequestBody.create(MediaType.parse("multipart/form-data"),newFile);
            MultipartBody.Part photo=MultipartBody.Part.createFormData("photo",newFile.getName(),photoConten);
            RequestBody description=RequestBody.create(MediaType.parse("text/plain"),"description");
            RequestBody quality=RequestBody.create(MediaType.parse("text/plain"),_categorySelected);
            RequestBody season=RequestBody.create(MediaType.parse("text/plain"),_seasonSelected);
            RequestBody size=RequestBody.create(MediaType.parse("text/plain"),_size);
            RequestBody color=RequestBody.create(MediaType.parse("text/plain"),_colors);
            RequestBody types=RequestBody.create(MediaType.parse("text/plain"),type);
            RequestBody itemsname = RequestBody.create(MediaType.parse("text/plain"),itemname);
            RequestBody name=RequestBody.create(MediaType.parse("text/plain"),username);

            UploadService uploadService=RestClient.getClient().create(UploadService.class);
            uploadService.Upload(photo,description,season,quality,itemsname,types,size,name,color).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful())
                    {
                        //Toast.makeText(getApplicationContext(), "Upload Sucessfull", Toast.LENGTH_SHORT).show();
                        fetchData();
                    }else
                        {
                            Toast.makeText(getApplicationContext(), ""+response.toString(), Toast.LENGTH_SHORT).show();
                        }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(ctx, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });




        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


       /* if (savedFileDestination==null) {
            Toast.makeText(this,"Please take photo first", Toast.LENGTH_LONG).show();
            return;
        }*/

        /*TypedFile typedFile = new TypedFile("multipart/form-data", savedFileDestination);
        initiateProgressDialog();

        restClient.getService().upload(typedFile, new CancelableCallback<Response>() {
            @Override
            public void onSuccess(Response response, Response response2) {
                mProgress.dismiss();
                *//*Picasso.with(AddSingleImage.this)
                        .load(savedFileDestination)
                        .into(imgPhoto);*//*


                Toast.makeText(AddSingleImage.this,"Upload successfully",Toast.LENGTH_LONG).show();
                Log.e("Upload", "success");
            }

            @Override
            public void onFailure(RetrofitError error) {
                mProgress.dismiss();
                Toast.makeText(AddSingleImage.this,"Upload failed",Toast.LENGTH_LONG).show();
                Log.e("Upload", error.getMessage().toString());
            }
        });*/


    }
    private void insertitem(){




        try {



            Gson gs = new Gson();
            String jsonString = gs.toJson(g);
            JSONObject item = new JSONObject();
            try {
                item = new JSONObject(jsonString);
            } catch (JSONException err) {
                Log.d("Error", err.toString());
            }

            String URL = WCFHandler.webUrl+"users/InsertItem";
            RequestQueue requestQueue = Volley.newRequestQueue(c);
            final String requestBody = jsonString.toString();
            final int[] idrec = {0};
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    URL, item, new com.android.volley.Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    try {
                       ID = response.getString("s");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());

                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                                requestBody, "utf-8");
                        return null;
                    }
                }
            };

            requestQueue.add(jsonObjectRequest);

        } catch (Exception e) {
        }
    }

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
                List<Example> adslist = response.body();

                int Quantity = adslist.get(0).getQuantity();
                String Season = adslist.get(0).getSeason();
                String Size = adslist.get(0).getSize();
                String Image = adslist.get(0).getImagePath();

                Toast.makeText(AddSingleImage.this, ""+Quantity+"\n"+Season+"\n"+Size+"\n"+Image, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {

                Toast.makeText(AddSingleImage.this, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _seasonSelected = parent.getItemAtPosition(position).toString();
        _categorySelected = parent.getItemAtPosition(position).toString();
        _size = parent.getItemAtPosition(position).toString();

    }
    private void initiateProgressDialog(){
        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Uploading files...");
        mProgress.setCancelable(true);

        //setButton is depreciated, it's tell us is not good to cancel when something is running at the back. :). Think about it.
//When cancel button clicked, it will ignore the response from server
        //You could see this from video
        mProgress.setButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                CancelableCallback.cancelAll();
                return;
            }
        });
        mProgress.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        Intent result=Intent.createChooser(intent, "Select Picture");
        startActivityForResult(result,10);
    }

    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.id.imgup);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }

    public static String ConvertBitmapToString(Bitmap bitmap) {
        String encodedImage = "";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        try {
            encodedImage = URLEncoder.encode(Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encodedImage;
    }

    private byte[] imageToByteArray(Bitmap bitmapImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 0, baos);
        return baos.toByteArray();
    }

    private String getRealPathFromUri(Uri contentUri)
    {
        String[] proj={MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader=new CursorLoader(getApplicationContext(),contentUri,proj,null,null,null);
        Cursor cursor=cursorLoader.loadInBackground();
        int columnIndex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(columnIndex);
        cursor.close();
        return result;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10)
        {
            if(resultCode==RESULT_OK)
            {

                Uri uri=data.getData();
                imagePath=getRealPathFromUri(uri);
                _imgupload.setImageURI(uri);
            }
        }

        /*if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = findViewById(R.id.imgup);

                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    byte[] _imgbytes = imageToByteArray(bitmap);
                    _encodedimg = Base64.encodeToString(_imgbytes, Base64.DEFAULT);

                    this.encodedImage = _encodedimg;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    }