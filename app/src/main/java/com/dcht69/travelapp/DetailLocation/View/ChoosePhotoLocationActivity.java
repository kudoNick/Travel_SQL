package com.dcht69.travelapp.DetailLocation.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.dcht69.travelapp.DetailLocation.Adapter.ChoosePhotoLocationAdapter;
import com.dcht69.travelapp.DetailLocation.Moder.LocationPlace;
import com.dcht69.travelapp.PlaceUrl;
import com.dcht69.travelapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChoosePhotoLocationActivity extends AppCompatActivity {

    private int a = 0;
    private String idCity;
    private String idXtra;
    public static String cmt1;
    public static String cmt2;

    private LocationPlace locationPlace;
    private ChoosePhotoLocationAdapter choosePhotoLocationAdapter;
    private List<LocationPlace> locationPlaceList;
    private RecyclerView rcvLocation;
    private Toolbar toolbar;

    private ProgressBar proBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo_location);
        proBar1 = findViewById(R.id.proBar1);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Ảnh địa điểm");

        Intent intent = getIntent();
        idCity = intent.getStringExtra("idCity");
        idXtra = intent.getStringExtra("ideXtra");
        cmt1 = intent.getStringExtra("cmt1");
        cmt2 = intent.getStringExtra("cmt2");


        a = intent.getIntExtra("number", 0);

        rcvLocation = findViewById(R.id.rcvLocation);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rcvLocation.setHasFixedSize(true);
        rcvLocation.setLayoutManager(gridLayoutManager);


        switch (a) {
            case 1:
                AddPhotoPlace();
                break;
            case 2:
                AddPhotoPlaceExtra();
                break;
            case 3:
                AddPhotoPlaceExtra();
                break;
        }

    }

    private void AddPhotoPlace() {
        locationPlaceList = new ArrayList<>();
        AndroidNetworking.get(PlaceUrl.CITY + idCity +"/chilPlace")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                locationPlace = new LocationPlace(response.getJSONObject(i));
                                locationPlaceList.add(locationPlace);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        choosePhotoLocationAdapter = new ChoosePhotoLocationAdapter(ChoosePhotoLocationActivity.this, locationPlaceList,a);
                        rcvLocation.setAdapter(choosePhotoLocationAdapter);
                        proBar1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ChoosePhotoLocationActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        proBar1.setVisibility(View.GONE);
                    }
                });
    }

    private void AddPhotoPlaceExtra() {
        locationPlaceList = new ArrayList<>();
        AndroidNetworking.get(PlaceUrl.LOCATION + idXtra)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        locationPlace = new LocationPlace();
                        if (response.has("img1")) {
                            try {
                                locationPlace.setImg1(response.getString("img1"));
                                locationPlace.setImg2(response.getString("img2"));
                                locationPlace.setTextImg1(response.getString("textImg1"));
                                locationPlace.setTextImg2(response.getString("textImg2"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        locationPlaceList.add(locationPlace);

                        choosePhotoLocationAdapter = new ChoosePhotoLocationAdapter(ChoosePhotoLocationActivity.this, locationPlaceList,a);
                        rcvLocation.setAdapter(choosePhotoLocationAdapter);
                        proBar1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ChoosePhotoLocationActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        proBar1.setVisibility(View.GONE);
                    }
                });

    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}