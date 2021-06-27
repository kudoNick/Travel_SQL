package com.dcht69.travelapp.DetailLocation.Adapter;

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

import com.dcht69.travelapp.DetailLocation.Moder.LocationPlace;
import com.dcht69.travelapp.DetailLocation.Sqlite.SqliteDetailLocation;
import com.dcht69.travelapp.DetailLocation.View.DetailLocationActivity;
import com.dcht69.travelapp.DetailLocation.View.LocationActivity;
import com.dcht69.travelapp.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LocationPlaceAdapter extends RecyclerView.Adapter<LocationPlaceAdapter.LocationHolder> {
    private List<LocationPlace> locationPlaceList;
    private Context context;

    private SqliteDetailLocation sqliteDetailLocation;
    public LocationPlaceAdapter(List<LocationPlace> locationPlaceList, Context context) {
        this.locationPlaceList = locationPlaceList;
        this.context = context;
    }

    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.city, null);
        LocationHolder locationHolder = new LocationHolder(view);
        return locationHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {
        LocationPlace locationPlace = locationPlaceList.get(position);
        Picasso.get().load(locationPlace.getImgPlace()).into(holder.img);
        holder.tvImg.setText(locationPlace.getNamePlace());

        sqliteDetailLocation = new SqliteDetailLocation(context);

        Gson gson = new Gson();
        String data = gson.toJson(locationPlace);
        // chi tiet
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailLocationActivity.class);
                intent.putExtra("data", data);
                context.startActivity(intent);
            }
        });

        //xoas
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa địa điểm");
                builder.setMessage("Bạn có muốn xóa " + locationPlace.getNamePlace() +" Không?");
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
                        long result = sqliteDetailLocation.delLocation(locationPlace);
                        if (result>0){
                            locationPlaceList.remove(locationPlace);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();

                            if (locationPlaceList.size() != 0) {
                                LocationActivity.line1.setVisibility(View.GONE);
                            } else {
                                LocationActivity.line1.setVisibility(View.VISIBLE);
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
        return locationPlaceList.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tvImg;
        public LocationHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgCity);
            tvImg = itemView.findViewById(R.id.tvCity);
        }
    }
}
