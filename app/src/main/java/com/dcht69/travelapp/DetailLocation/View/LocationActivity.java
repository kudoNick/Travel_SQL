package com.dcht69.travelapp.DetailLocation.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dcht69.travelapp.DetailLocation.Adapter.LocationPlaceAdapter;
import com.dcht69.travelapp.DetailLocation.Moder.LocationPlace;
import com.dcht69.travelapp.DetailLocation.Sqlite.SqliteDetailLocation;
import com.dcht69.travelapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    private String idCity,nameCity;

    public static LinearLayout line1;

    private RecyclerView rcvLocation;
    private SqliteDetailLocation sqliteDetailLocation;
    private LocationPlaceAdapter locationPlaceAdapter;
    private List<LocationPlace> locationPlaceList;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        rcvLocation = findViewById(R.id.rcvLocation);
        toolbar = findViewById(R.id.toolbar);
        line1 = findViewById(R.id.line1);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        idCity = intent.getStringExtra("idCity");
        nameCity = intent.getStringExtra("nameCity");
        toolbar.setTitle(nameCity);

        sqliteDetailLocation = new SqliteDetailLocation(this);
        locationPlaceList = new ArrayList<>();

        locationPlaceList = sqliteDetailLocation.getAllLocationByIdCity(idCity);

        if (locationPlaceList.size() != 0) {
            locationPlaceAdapter = new LocationPlaceAdapter(locationPlaceList, this);
            RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            rcvLocation.setLayoutManager(layoutManager1);
            rcvLocation.setAdapter(locationPlaceAdapter);
            line1.setVisibility(View.GONE);
        } else {
            line1.setVisibility(View.VISIBLE);
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationActivity.this, AddLocationActivity.class);
                intent.putExtra("idCity", idCity);
                startActivity(intent);
            }
        });
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
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}