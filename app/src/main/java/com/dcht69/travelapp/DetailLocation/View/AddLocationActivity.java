package com.dcht69.travelapp.DetailLocation.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dcht69.travelapp.DetailLocation.Moder.LocationPlace;
import com.dcht69.travelapp.DetailLocation.Sqlite.SqliteDetailLocation;
import com.dcht69.travelapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class AddLocationActivity extends AppCompatActivity {
    // Moder
    public static String ID = "";

    private String idCity;
    public static String IMG_PLACE = "",IMG_1 ="",IMG_2 = "";
    public static String nameIMG_PLACE = "",nameIMG_1 ="",nameIMG_2 = "";
    private String comnet1 ="";
    private String comnet2 ="";
    //Tham chieu
    private EditText edtComent1;
    private EditText edtComent2;
    public  ImageView imgPlace,img1,img2,imgBack;
    private TextView tvPlace,tvImg1,tvImg2;

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    private LocationPlace locationPlace;
    private SqliteDetailLocation sqliteDetailLocation;
    private Toolbar toolbar;

    private String data;
    private boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        ThamChieu();
        toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        try {
            edtComent1.setText(ChoosePhotoLocationActivity.cmt1);
        } catch (Exception e) {
        }
        try {
            edtComent2.setText(ChoosePhotoLocationActivity.cmt2);
        } catch (Exception e) {
        }

        Gson gson = new Gson();
        Intent intent = getIntent();
        //Lấy IdCity
        if (intent != null) {
            idCity = intent.getStringExtra("idCity");
//            ID = intent.getStringExtra("ID");
//            IMG_PLACE = intent.getStringExtra("IMG_PLACE");
//            IMG_1 = intent.getStringExtra("IMG_1");
//            IMG_2 = intent.getStringExtra("IMG_2");
//            nameIMG_PLACE = intent.getStringExtra("nameIMG_PLACE");
//            nameIMG_1 = intent.getStringExtra("nameIMG_1");
//            nameIMG_2 = intent.getStringExtra("nameIMG_2");
//            comnet1 = intent.getStringExtra("comnet1");
//            comnet2 = intent.getStringExtra("comnet2");
            data = intent.getStringExtra("data");
        }
        locationPlace = new LocationPlace();
        locationPlace = gson.fromJson(data, LocationPlace.class);
        if (locationPlace != null) {
            actionBar.setTitle("Sửa địa điểm");
            idCity = locationPlace.getIdCity();
            ID = locationPlace.getId();
            IMG_PLACE = locationPlace.getImgPlace();
            IMG_1 = locationPlace.getImg1();
            IMG_2 = locationPlace.getImg2();
            nameIMG_PLACE = locationPlace.getNamePlace();
            nameIMG_1 = locationPlace.getTextImg1();
            nameIMG_2 = locationPlace.getTextImg2();
            edtComent1.setText(locationPlace.getComment1());
            edtComent2.setText(locationPlace.getComment2());
            check = true;
        } else {
            actionBar.setTitle("Thêm địa điểm");
            check = false;
        }


        //Set ảnh sau khi chọn ảnh
        SetPhoto();
        //Chon anh
        Intent choosePhoto = new Intent(AddLocationActivity.this, ChoosePhotoLocationActivity.class);
        choosePhoto.putExtra("idCity", idCity);
        imgPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto.putExtra("number", 1);
                choosePhoto.putExtra("cmt1", edtComent1.getText().toString().trim());
                choosePhoto.putExtra("cmt2", edtComent2.getText().toString().trim());
                startActivity(choosePhoto);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(ID == "")) {
                    choosePhoto.putExtra("number", 2);
                    choosePhoto.putExtra("ideXtra", ID);
                    choosePhoto.putExtra("cmt1", edtComent1.getText().toString().trim());
                    choosePhoto.putExtra("cmt2", edtComent2.getText().toString().trim());
                    startActivity(choosePhoto);
                } else {
                    Toast.makeText(AddLocationActivity.this, "Vui Lòng chọn ảnh nền trước!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(ID == "")) {
                    choosePhoto.putExtra("number", 3);
                    choosePhoto.putExtra("ideXtra", ID);
                    choosePhoto.putExtra("cmt1", edtComent1.getText().toString().trim());
                    choosePhoto.putExtra("cmt2", edtComent2.getText().toString().trim());
                    startActivity(choosePhoto);
                } else {
                    Toast.makeText(AddLocationActivity.this, "Vui Lòng chọn ảnh nền trước!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //dialog
        Dialog();
    }

    private void SaveData() {

        comnet1 = edtComent1.getText().toString().trim();
        comnet2 = edtComent2.getText().toString().trim();
        if (ID.isEmpty() || idCity.isEmpty() || IMG_PLACE.isEmpty() || IMG_1.isEmpty() || IMG_2.isEmpty() ||
                comnet1.isEmpty() || comnet2.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đủ thông tin trước khi lưu!", Toast.LENGTH_SHORT).show();
        } else {
            sqliteDetailLocation = new SqliteDetailLocation(this);
            locationPlace = new LocationPlace();

            locationPlace.setId(ID);
            locationPlace.setIdCity(idCity);
            locationPlace.setImgPlace(IMG_PLACE);
            locationPlace.setImg1(IMG_1);
            locationPlace.setImg2(IMG_2);
            locationPlace.setNamePlace(nameIMG_PLACE);
            locationPlace.setTextImg1(nameIMG_1);
            locationPlace.setTextImg2(nameIMG_2);
            locationPlace.setComment1(comnet1);
            locationPlace.setComment2(comnet2);

            if (check) {
                long result = sqliteDetailLocation.updateLocation(locationPlace);
                if (result > 0) {
                    Toast.makeText(this, "Thành Công", Toast.LENGTH_SHORT).show();
                    IMG_PLACE = "";
                    IMG_1 = "";
                    IMG_2 = "";
                    ID = "";
                    ChoosePhotoLocationActivity.cmt1 = "";
                    ChoosePhotoLocationActivity.cmt2 = "";
                    finish();
                } else {
                    Toast.makeText(this, "Sửa không thành công", Toast.LENGTH_SHORT).show();
                }
            } else {
                long result = sqliteDetailLocation.insertLocation(locationPlace);
                if (result > 0) {
                    Toast.makeText(this, "Thành Công", Toast.LENGTH_SHORT).show();
                    IMG_PLACE = "";
                    IMG_1 = "";
                    IMG_2 = "";
                    ID = "";
                    ChoosePhotoLocationActivity.cmt1 = "";
                    ChoosePhotoLocationActivity.cmt2 = "";
                    finish();
                } else {
                    Toast.makeText(this, "Địa điểm " + nameIMG_PLACE + " đã có!", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
    private void CheckData() {
        comnet1 = edtComent1.getText().toString().trim();
        comnet2 = edtComent2.getText().toString().trim();
        if (ID.isEmpty() && IMG_PLACE.isEmpty() && IMG_1.isEmpty() && IMG_2.isEmpty() &&
                comnet1.isEmpty() && comnet2.isEmpty()) {
            finish();
        } else {
            alertDialog.show();
        }
    }
    private void Dialog() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("CẢNH BÁO");
        builder.setMessage("Bạn sẽ mất dữ liệu đang nhập khi thoát khỏi màn hình này!");
        builder.setCancelable(false);

        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                IMG_PLACE = "";
                IMG_1 = "";
                IMG_2 = "";
                ID = "";
                ChoosePhotoLocationActivity.cmt1 = "";
                ChoosePhotoLocationActivity.cmt2 = "";
                finish();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog = builder.create();
    }
    private void ThamChieu() {
        imgPlace = findViewById(R.id.imgPlace);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        tvPlace = findViewById(R.id.tvPlace);
        tvImg1 = findViewById(R.id.tvImage1);
        tvImg2 = findViewById(R.id.tvImage2);
        edtComent1 = findViewById(R.id.edtComment1);
        edtComent2 = findViewById(R.id.edtComment2);

    }
    private void SetPhoto() {
        try {
            Picasso.get().load(IMG_PLACE)
                    .into(imgPlace);
            tvPlace.setText(nameIMG_PLACE);
        } catch (Exception e) {
            imgPlace.setImageResource(R.drawable.ic_baseline_add_photo_alternate_24);
        }

        try {
            Picasso.get().load(IMG_1)
                    .into(img1);
            tvImg1.setText(nameIMG_1);

        } catch (Exception e) {
            img1.setImageResource(R.drawable.ic_baseline_add_photo_alternate_24);
        }

        try {
            Picasso.get().load(IMG_2)
                    .into(img2);
            tvImg2.setText(nameIMG_2);
        } catch (Exception e) {
            img2.setImageResource(R.drawable.ic_baseline_add_photo_alternate_24);
        }
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
                CheckData();
                return true;
            case R.id.save:
                SaveData();
                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        CheckData();
    }

}