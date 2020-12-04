package com.example.dresswardrobe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.goncalves.pugnotification.notification.PugNotification;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Context c = this;
    SearchView sv;
    List<Images> img;
    List<Images> img2;
    SharedPreferences sharedpreferences;
    DrawerLayout _drawerLayout;
    ActionBarDrawerToggle _toggle;
    NavigationView _navigationalview;
    TabLayout _tablayout;
    TabItem _firstFrag, _secondFrag;
    PagerAdapter _adapter;
    ViewPager _pager;
    AutoCompleteTextView _atv;
    public static String username = " ";
    String[] _arraySeason;
    FloatingActionButton _fabSingleItem;
    String frname;
    EditText editText;
    SharedPreferences prf;
    private AdapterView.OnItemClickListener mOnItemClickListener;
    Intent intent;
    public MainActivity() {

    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv = findViewById(R.id.searchView);
        PugNotification.with(c)

                .load()
                .message("You may have new notifications please click here to see them")
                .bigTextStyle("You may have new notifications please click here to see them")
                .onlyAlertOnce(true)
                .smallIcon(R.drawable.pugnotification_ic_launcher)
                .largeIcon(R.drawable.pugnotification_ic_launcher)
                .flags(Notification.DEFAULT_ALL)
               .click(listview.class)
                .simple()
                .build();

        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        username = prf.getString("username",null);
        getfriend();
        getmine();
        getDetails();
        _fabSingleItem = (FloatingActionButton) findViewById(R.id.singleitem);
        RecyclerView _rv = (RecyclerView) findViewById(R.id.recview);
        RecyclerViewAdapter _myadapter = new RecyclerViewAdapter(this, img);
        _rv.setAdapter(_myadapter);
        _myadapter.notifyDataSetChanged();
         _arraySeason = new String[]{"Select Season", "Spring", "Summer", "Autumn", "Winter"};
        List<String> _seasons = new ArrayList<>(Arrays.asList(_arraySeason));
        ArrayAdapter<String> _spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, _seasons) {
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        _fabSingleItem.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddSingleImage.class);
            intent.putExtra("uname", username);
            startActivity(intent);
           // _myadapter. notifyDataSetChanged();
        });
        Spinner _spinnerSeason = (Spinner) findViewById(R.id.spinSeason);
        Toolbar _toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);
        _tablayout = findViewById(R.id.tablayout);
        _firstFrag = findViewById(R.id.FFTab);
        _secondFrag = findViewById(R.id.WardrobeTab);

        //_formal = findViewById(R.id.formSelect);
        _drawerLayout = findViewById(R.id.drawer);
        _navigationalview = findViewById(R.id.nav_view);
        _navigationalview.setNavigationItemSelectedListener(this);
        _toggle = new ActionBarDrawerToggle(this, _drawerLayout, _toolbar, R.string.open, R.string.close);
        _drawerLayout.addDrawerListener(_toggle);
        _toggle.setDrawerIndicatorEnabled(true);
        _toggle.syncState();
        //  _pager = findViewById(R.id.viewpager);
        //_adapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, _tablayout.getTabCount());
        //_pager.setAdapter(_adapter);

        _tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //_pager.setCurrentItem(tab.getPosition());

            }

            int a = 0;

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (a == 0) {
                    a = 1;

                    RecyclerView _rv = (RecyclerView) findViewById(R.id.recview);

                    RecyclerViewAdapter _myadapter = new RecyclerViewAdapter(c, img2);
                    _rv.setLayoutManager(new GridLayoutManager(c, 2));
                    _myadapter.notifyDataSetChanged();
                    _rv.setAdapter(_myadapter);

                } else {
                    a = 0;
                    RecyclerView _rv = (RecyclerView) findViewById(R.id.recview);

                    RecyclerViewAdapter _myadapter = new RecyclerViewAdapter(c, img);
                    _rv.setLayoutManager(new GridLayoutManager(c,2));
                    _myadapter.notifyDataSetChanged();
                    _rv.setAdapter(_myadapter);
                }


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // _pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(_tablayout));
    }

    private void getDetails() {
        String api_url = "users/show?UserName="+username;

    }

    public void getfriend()
    {
        img = new ArrayList<>();
        String api_url = "users/getFrienditems?UserName="+username;
        String ja = WCFHandler.GetJsonResult(api_url);

        if (ja.startsWith("http"))
        {
            return;
        }

        Gson gson = new Gson();
        Type founderListType = new TypeToken<ArrayList<GlobalItems>>(){}.getType();
        List<GlobalItems> it = gson.fromJson(ja, founderListType);



        for(GlobalItems g : it){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] imageBytes = baos.toByteArray();
            imageBytes = Base64.decode(g.IMAGE_PATH, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

            img.add(new Images(g.getITEM_ID(),g.getUSERNAME(),2,g.getITEM_NAME(),decodedImage,g.getSEASON(),g.getSIZE(),g.getCOLOR(),g.getTYPE()));

        }
    }
    public void getmine(){

        String api_url = "users/getAllitems?UserName="+username;
        String ja = WCFHandler.GetJsonResult(api_url);
        Log.d("helloo",ja);

        Gson gson = new Gson();
        Type founderListType = new TypeToken<ArrayList<GlobalItems>>(){}.getType();
        List<GlobalItems> it = gson.fromJson(ja, founderListType);

        img2 = new ArrayList<>();
        for(GlobalItems g : it){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] imageBytes = baos.toByteArray();
            imageBytes = Base64.decode(g.IMAGE_PATH, Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

            img2.add(new Images(g.getITEM_ID(),g.getUSERNAME(),2,g.getITEM_NAME(),decodedImage,g.getSEASON(),g.getSIZE(),g.getCOLOR(),g.getTYPE()));

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        _drawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();
        switch (id) {
            case R.id.winSelect:
                Intent intent = new Intent(getApplicationContext(), WinterActivty.class);
                intent.putExtra("uname", username);
                startActivity(intent);
                break;
            case R.id.userslist:
                Intent intent1 = new Intent(getApplicationContext(), ShowUsersClass.class);
                intent1.putExtra("uname", username);
                startActivity(intent1);
                break;
            case R.id.sumSelect:
                Intent intent2 = new Intent(getApplicationContext(), SummerActivity.class);
                intent2.putExtra("uname", username);
                startActivity(intent2);
                break;
            case R.id.home:
                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                intent3.putExtra("uname", username);
                username = intent3.getStringExtra("uname");
                startActivity(intent3);
                break;
            case R.id.noti:
                Intent intent4 = new Intent(getApplicationContext(), listview.class);
                intent4.putExtra("uname", username);
                username = intent4.getStringExtra("uname");
                startActivity(intent4);
                break;
           case R.id.logout:
                Intent loginscreen = new Intent(getApplicationContext(), LoginActivity.class);
                loginscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginscreen);
                this.finish();
        }
        return false;
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}