package com.example.dresswardrobe;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SchoolAdapter extends BaseAdapter {
    SharedPreferences prf;
    String username;
    String frndname;

    private Context mContext;
    private List<String> mArrSchoolData;



    public SchoolAdapter(Context context, List<String> arrSchoolData) {
        super();
        mContext = context;
        mArrSchoolData =  arrSchoolData;
        prf = mContext.getSharedPreferences("user_details",MODE_PRIVATE);
        username = prf.getString("username",null);

    }

    public int getCount() {
        // return the number of records
        return mArrSchoolData.size();
    }

    // getView method is called for each item of ListView
    public View getView(int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_listview_row, parent, false);


        // get the reference of textView and button
        TextView txtSchoolTitle = (TextView) view.findViewById(R.id.txtSchoolTitle);
        Button btnAction = (Button) view.findViewById(R.id.btnAction);
        Button btnAction1 = (Button) view.findViewById(R.id.btnAction1);
        // Set the title and button name
        txtSchoolTitle.setText(mArrSchoolData.get(position));
        btnAction.setText("Accept ");
        btnAction1.setText("Reject");
      //  btnAction.setTag(position);
        //btnAction1.setTag(position);
        btnAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //reject
                String s = "Rejected";
                String fr = mArrSchoolData.get(position);
                 String api_url = WCFHandler.GetJsonResult("users/requests?UserName="+username+"&User_to="+fr+"&Status=Pending");

                Toast.makeText(mContext, "Rejected" +username + "to "+fr,Toast.LENGTH_LONG ).show();
            }
        });
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            //accept
               public void onClick(View view) {
               // String s = "Accepted";
                String fr = mArrSchoolData.get(position);
                String api_url = WCFHandler.GetJsonResult("users/requests?UserName="+username+"&User_to="+fr+"&Status=Pending");
                Toast.makeText(mContext, "Accepted" +username + "to "+ fr,Toast.LENGTH_LONG ).show();

            }
        });

        return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

}
