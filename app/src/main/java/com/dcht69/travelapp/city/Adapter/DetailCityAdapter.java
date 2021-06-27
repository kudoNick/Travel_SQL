package com.dcht69.travelapp.city.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.dcht69.travelapp.DetailLocation.View.LocationActivity;
import com.dcht69.travelapp.R;
import com.dcht69.travelapp.city.Moder.City;
import com.dcht69.travelapp.city.Sqlite.SqliteCity;
import com.dcht69.travelapp.city.View.CityActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailCityAdapter extends RecyclerView.Adapter<DetailCityAdapter.CityHolder> {


    private SqliteCity sqliteCity;
    Context context;
    List<City> cityList;

    public DetailCityAdapter(Context context, List<City> cityList) {
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

        sqliteCity = new SqliteCity(context);

        City city = cityList.get(position);
        Picasso.get().load(city.getImgCity()).into(holder.imgCity);
        holder.tvCity.setText(city.getNameCity());
//        holder.tvID.setText(city.getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,LocationActivity.class);
                intent.putExtra("idCity", city.getId());
                intent.putExtra("nameCity", city.getNameCity());

                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa thành Phố");
                builder.setMessage("Bạn có muốn xóa " + city.getNameCity() +" Không?");
                builder.setCancelable(false);

                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        long result = sqliteCity.delCity(city);
                        if (result>0){
                            cityList.remove(city);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();

                            if (cityList.size() != 0) {
                                CityActivity.line1.setVisibility(View.GONE);
                            } else {
                                CityActivity.line1.setVisibility(View.VISIBLE);
                            }

                        }else {Toast.makeText(context," thất bại",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return false;
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
