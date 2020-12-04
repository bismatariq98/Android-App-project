package com.example.dresswardrobe;

import android.os.StrictMode;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;



class CallResult{
    public int StatusCode = 201;//200 ok
    public String jsonString;
    public String Message;
}

public class WCFHandler {

    static String webUrl = "http://192.168.10.9/DressWardrobeAPI/api/";

    //  static String webUrl="http://localhost/DressWardrobeAPI/api/";

    public static String GetJsonResult(String functionName) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL url = new URL(webUrl+functionName);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String jsonData;

            if ((jsonData = bufferedReader.readLine()) != null) {


            }
            return jsonData;

        } catch (MalformedURLException e) {
            String err = e.getMessage();
            return err;
            //  return e.getMessage();
        } catch (ProtocolException e) {
            String err = e.getMessage();
            return err;
        } catch (IOException e) {
            String err = e.getMessage();
            return err;
            // return e.getMessage();
        }


    }

    public static String PostJsonResult(String functionName, String userJson) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpPost request = new HttpPost(webUrl + functionName);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");


        StringEntity entity = null;
        try {
            entity = new StringEntity(userJson, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setContentEncoding(new BasicHeader(org.apache.http.protocol.HTTP.CONTENT_TYPE, "application/json"));
        entity.setContentType("application/json");

        request.setEntity(entity);

// Send request to WCF service
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try {
            HttpResponse response = httpClient.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String result = "";
            String jsonData = null;
            while ((jsonData = reader.readLine()) != null)
                result += jsonData;

            return result;
            //  return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public static CallResult post(String endPoints, String data)
    {
        CallResult callResult = new CallResult();
        callResult.StatusCode = 201;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpPost request = new HttpPost(webUrl + endPoints);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");
        request.setHeader("Connection", "Keep-Alive");
        StringEntity entity = null;

        try
        {
            entity = new StringEntity(data.toString(),"UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        entity.setContentEncoding(new BasicHeader(org.apache.http.protocol.HTTP.CONTENT_TYPE, "application/json"));
        entity.setContentType("application/json");

        request.setEntity(entity);

        // Send request to WCF service
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try
        {
            HttpResponse response = httpClient.execute(request);

            int code = response.getStatusLine().getStatusCode();
            String message = response.getStatusLine().getReasonPhrase();
            callResult.StatusCode = code;

            if(code == 200)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String result = "";
                String jsonData = null;

                while ((jsonData = reader.readLine()) != null)
                    result += jsonData;

                callResult.jsonString = result;
            }
            else
            {
                callResult.Message = message;
                callResult.jsonString = "Got Error";
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
            callResult.jsonString = "Got Error";
            callResult.Message = e.getMessage();
        }

        return callResult;
    }
}