package com.dcht69.travelapp.city.View;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.dcht69.travelapp.PlaceUrl;
import com.dcht69.travelapp.R;
import com.dcht69.travelapp.city.Adapter.CityAdapter;
import com.dcht69.travelapp.city.Moder.City;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ChoosePhotoActivity extends AppCompatActivity {

    private RecyclerView rcvCityPhoto;
    private City city;
    private List<City> cityList;
    private CityAdapter cityAdapter;

    private Toolbar toolbar;
    private ProgressBar proBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);
        ThamChieu();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Chọn Thành Phố");

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rcvCityPhoto.setHasFixedSize(true);
        rcvCityPhoto.setLayoutManager(gridLayoutManager);

        cityList = new ArrayList<>();
        AndroidNetworking.get(PlaceUrl.CITY)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i <response.length() ; i++) {
                            try {
                                City city = new City(response.getJSONObject(i));
                                cityList.add(city);
//                                progressBar.setVisibility(View.GONE);
//                                dialog.cancel();
                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }
                        cityAdapter = new CityAdapter(ChoosePhotoActivity.this, cityList);
                        rcvCityPhoto.setAdapter(cityAdapter);
                        proBar1.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ChoosePhotoActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        proBar1.setVisibility(View.GONE);
                    }
                });

    }

    private void ThamChieu() {
        rcvCityPhoto = findViewById(R.id.rcvCityPhoto);
        toolbar = findViewById(R.id.toolbar);
        proBar1 = findViewById(R.id.proBar1);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}