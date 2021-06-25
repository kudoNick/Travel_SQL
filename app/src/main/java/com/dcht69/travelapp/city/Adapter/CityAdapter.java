package com.dcht69.travelapp.city.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dcht69.travelapp.R;
import com.dcht69.travelapp.city.Moder.City;
import com.dcht69.travelapp.city.View.AddCityActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityHolder> {

    Context context;
    List<City> cityList;

    public CityAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.city, null);
        CityHolder cityHolder = new CityHolder(view);

        return cityHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityHolder holder, int position) {
        City city = cityList.get(position);
        Picasso.get().load(city.getImgCity()).into(holder.imgCity);
        holder.tvCity.setText(city.getNameCity());
//        holder.tvID.setText(city.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddCityActivity.class);
                Gson gson = new Gson();
                String data = gson.toJson(city);
                intent.putExtra("data", data);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class CityHolder extends RecyclerView.ViewHolder {
        private TextView tvCity,tvID;
        private ImageView imgCity;

        public CityHolder(@NonNull View itemView) {
            super(itemView);
            tvCity = itemView.findViewById(R.id.tvCity);
            imgCity = itemView.findViewById(R.id.imgCity);
            tvID = itemView.findViewById(R.id.tvID);

        }
    }
}
