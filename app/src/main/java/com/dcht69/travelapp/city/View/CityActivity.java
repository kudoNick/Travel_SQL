package com.dcht69.travelapp.city.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.dcht69.travelapp.Login_Registration.CommentActivity;
import com.dcht69.travelapp.R;
import com.dcht69.travelapp.city.Adapter.DetailCityAdapter;
import com.dcht69.travelapp.city.Moder.City;
import com.dcht69.travelapp.city.Sqlite.SqliteCity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {

    private RecyclerView rcvCity;

    private SqliteCity sqliteCity;
    private City city;
    public static List<City> cityList;
    private DetailCityAdapter DetailCityAdapter;

    public static LinearLayout line1;

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

        if (cityList.size() != 0) {
            DetailCityAdapter = new DetailCityAdapter(this, cityList);
            rcvCity.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
            rcvCity.setAdapter(DetailCityAdapter);
            line1.setVisibility(View.GONE);
        } else {
            line1.setVisibility(View.VISIBLE);
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CityActivity.this, ChoosePhotoActivity.class);
                startActivity(intent);
            }
        });

    }

    private void thamChieu() {
        rcvCity = findViewById(R.id.rcvCity);
        toolbar = findViewById(R.id.toolbar);
        line1 = findViewById(R.id.line1);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuComment) ;
        {
            startActivity(new Intent(this, CommentActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}