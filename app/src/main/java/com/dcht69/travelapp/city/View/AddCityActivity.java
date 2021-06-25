package com.dcht69.travelapp.city.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dcht69.travelapp.R;
import com.dcht69.travelapp.city.Moder.City;
import com.dcht69.travelapp.city.Sqlite.SqliteCity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class AddCityActivity extends AppCompatActivity {

    private TextView edtNameCity;
    private ImageView imgCity;
    private Button btnChoosePhoto,btnSavePhoto,btnCancel;
    private Toolbar toolbar;

    private String realPathImages;

    private boolean checkPhoto = false;

    private SqliteCity sqliteCity;
    private City city;
    private Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        thamChieu();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("Chọn Thành Phố");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gson = new Gson();
        sqliteCity = new SqliteCity(this);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        City city = gson.fromJson(data, City.class);

        edtNameCity.setText(city.getNameCity());
        Picasso.get().load(city.getImgCity()).into(imgCity);

        btnSavePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    long result = sqliteCity.insertCity(city);
                    if (result>0){
                        Toast.makeText(AddCityActivity.this, "Thành Công",Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(AddCityActivity.this,CityActivity.class);
                        startActivity(intent1);
                        finish();
                    }else {
                        Toast.makeText(AddCityActivity.this, "Địa điểm này đã được thêm",Toast.LENGTH_SHORT).show();
                    }
                }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void thamChieu() {
        edtNameCity = findViewById(R.id.edtNameCity);
        imgCity = findViewById(R.id.imgCity);
        btnChoosePhoto = findViewById(R.id.btnChoosePhoto);
        btnSavePhoto = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        toolbar = findViewById(R.id.toolbar);

    }

    @Override
    protected void onDestroy() {
        sqliteCity.close();
        super.onDestroy();
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