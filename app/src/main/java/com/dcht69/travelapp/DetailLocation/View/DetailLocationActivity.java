package com.dcht69.travelapp.DetailLocation.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dcht69.travelapp.DetailLocation.Moder.LocationPlace;
import com.dcht69.travelapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailLocationActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView edtComent1;
    private TextView edtComent2;
    private TextView tvEdit;
    public  ImageView imgPlace,img1,img2,imgBack;
    private TextView tvImg1,tvImg2;
    private ProgressBar proBar1;


    private LocationPlace locationPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);
        overridePendingTransition(R.anim.slie_up,R.anim.slide_down);
        ThamChieu();

        Gson gson = new Gson();
        locationPlace = new LocationPlace();
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");

        if (!data.isEmpty()) {
            locationPlace = gson.fromJson(data, LocationPlace.class);

            Picasso.get().load(locationPlace.getImgPlace()).into(imgPlace);
            toolbar.setTitle(locationPlace.getNamePlace());
            edtComent1.setText(locationPlace.getComment1());
            edtComent2.setText(locationPlace.getComment2());
            Picasso.get().load(locationPlace.getImg1()).into(img1);
            Picasso.get().load(locationPlace.getImg2()).into(img2);
            tvImg1.setText(locationPlace.getTextImg1());
            tvImg2.setText(locationPlace.getTextImg2());
            proBar1.setVisibility(View.GONE);
        }

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailLocationActivity.this, AddLocationActivity.class);
//                i.putExtra("idCity", locationPlace.getIdCity());
//                i.putExtra("ID", locationPlace.getId());
//                i.putExtra("IMG_PLACE", locationPlace.getImgPlace());
//                i.putExtra("IMG_1", locationPlace.getImg1());
//                i.putExtra("IMG_2", locationPlace.getImg2());
//                i.putExtra("nameIMG_PLACE", locationPlace.getNamePlace());
//                i.putExtra("nameIMG_1", locationPlace.getTextImg1());
//                i.putExtra("nameIMG_2", locationPlace.getTextImg2());
//                i.putExtra("comnet1", locationPlace.getComment1());
//                i.putExtra("comnet2", locationPlace.getComment2());

                i.putExtra("data", data);
                startActivity(i);
            }
        });

    }

    private void ThamChieu() {
        tvEdit = findViewById(R.id.tvEdit);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        edtComent1 = findViewById(R.id.tvComment1);
        edtComent2 = findViewById(R.id.tvComment2);
        imgPlace = findViewById(R.id.imgPlace);
        tvImg1 = findViewById(R.id.tvImg1);
        tvImg2 = findViewById(R.id.tvImg2);
        proBar1 = findViewById(R.id.proBar1);

        imgBack = findViewById(R.id.imgBack);

        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slie_up_back,R.anim.slide_down_back);
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}