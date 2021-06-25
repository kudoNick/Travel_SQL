package com.dcht69.travelapp.city.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.dcht69.travelapp.R;
import com.dcht69.travelapp.city.Adapter.DetailCityAdapter;
import com.dcht69.travelapp.city.Moder.City;
import com.dcht69.travelapp.city.Sqlite.SqliteCity;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {

    private RecyclerView rcvCity;

    private SqliteCity sqliteCity;
    private City city;
    private List<City> cityList;
    private DetailCityAdapter DetailCityAdapter;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        thamChieu();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Thành Phố");

        sqliteCity = new SqliteCity(this);
        cityList = new ArrayList<>();
        cityList = sqliteCity.getAllCity();

        DetailCityAdapter = new DetailCityAdapter(this, cityList);
        rcvCity.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        rcvCity.setAdapter(DetailCityAdapter);
        

    }

    private void thamChieu() {
        rcvCity = findViewById(R.id.rcvCity);
        toolbar = findViewById(R.id.toolbar);
    }

    public void addCity(View view) {
        Intent intent = new Intent(CityActivity.this, ChoosePhotoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        sqliteCity.close();
        super.onDestroy();
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}