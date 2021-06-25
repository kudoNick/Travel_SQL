package com.dcht69.travelapp.thang;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dcht69.travelapp.R;

import java.util.ArrayList;
import java.util.List;

public class TravelForeginAct extends AppCompatActivity {
    ListView lvCountry;
    List<Country> countryList;
    CustomListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_m000_listview);
        lvCountry = findViewById(R.id.lvCountry);
        countryList = getListData();
        adapter = new CustomListAdapter(TravelForeginAct.this, R.layout.act_m004_travelforegin, countryList);
        lvCountry.setAdapter(adapter);

    }

    private List<Country> getListData() {
        List<Country> list = new ArrayList<Country>();
        Country paRis = new Country("Paris", R.drawable.ic_paris);
        Country lonDon = new Country("LonDon", R.drawable.ic_london);
        Country uc = new Country("Úc", R.drawable.ic_uc);
        Country thaiLan = new Country("Thái Lan", R.drawable.ic_thailan);
        Country nga = new Country("Nga", R.drawable.ic_nga);
        Country italy = new Country("Italy", R.drawable.ic_itali);
        Country my = new Country("Mỹ", R.drawable.ic_my);


        list.add(paRis);
        list.add(lonDon);
        list.add(uc);
        list.add(thaiLan);
        list.add(nga);
        list.add(italy);
        list.add(my);

        return list;
    }
}
