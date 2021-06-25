package com.dcht69.travelapp.thang;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dcht69.travelapp.R;

import java.util.ArrayList;
import java.util.List;

public class TravelVietNamAct extends AppCompatActivity {
    ListView lvCountry;
    Menu mnuAdd;
    List<Country> countryList;
    CustomListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_m000_listview);
        lvCountry = findViewById(R.id.lvCountry);
        countryList = getListData();
        adapter = new CustomListAdapter(TravelVietNamAct.this, R.layout.act_m002_travelviet, countryList);
        lvCountry.setAdapter(adapter);

    }

    private List<Country> getListData() {
        List<Country> list = new ArrayList<Country>();
        Country haNoi = new Country("Hà Nội", R.drawable.ic_hn);
        Country tpHCM = new Country("Hồ Chí Minh", R.drawable.ic_tphcm);
        Country daNang = new Country("Đà Nẵng ", R.drawable.ic_danang);
        Country nhaTrang = new Country("Nha Trang", R.drawable.ic_nhatrang);
        Country haLong = new Country("Hạ Long", R.drawable.ic_halong);
        Country namDinh = new Country("Nam Định", R.drawable.ic_namdinh);
        Country haiDuong = new Country("Hải Dương", R.drawable.ic_haiduong);


        list.add(haNoi);
        list.add(tpHCM);
        list.add(daNang);
        list.add(nhaTrang);
        list.add(haLong);
        list.add(namDinh);
        list.add(haiDuong);

        return list;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.mnuAdd) ;
//        {
//            startActivity(new Intent(TravelVietNamAct.this, TravelForeginAct.class));
//        }
//        return super.onOptionsItemSelected(item);
//    }

}


