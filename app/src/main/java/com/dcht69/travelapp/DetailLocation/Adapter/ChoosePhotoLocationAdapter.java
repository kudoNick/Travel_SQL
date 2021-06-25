package com.dcht69.travelapp.DetailLocation.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dcht69.travelapp.DetailLocation.Moder.LocationPlace;
import com.dcht69.travelapp.DetailLocation.View.AddLocationActivity;
import com.dcht69.travelapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChoosePhotoLocationAdapter extends RecyclerView.Adapter<ChoosePhotoLocationAdapter.ChoosePhotoLocationHolder> {

    Context context;
    List<LocationPlace> locationList;
    public static String IMG = "";

    int a;

    public ChoosePhotoLocationAdapter(Context context, List<LocationPlace> locationList, int a) {
        this.context = context;
        this.locationList = locationList;
        this.a = a;
    }

    public ChoosePhotoLocationAdapter(Context context, List<LocationPlace> locationList) {
        this.context = context;
        this.locationList = locationList;
    }


    @NonNull
    @Override
    public ChoosePhotoLocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.location, null);
        ChoosePhotoLocationHolder choosePhotoLocationHolder = new ChoosePhotoLocationHolder(view);

        return choosePhotoLocationHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChoosePhotoLocationHolder holder, int position) {
        LocationPlace location = locationList.get(position);

        if (location.getImgPlace() == null) {
            Picasso.get().load(location.getImg1()).into(holder.imgPlace);
            Picasso.get().load(location.getImg2()).into(holder.img1);
        } else {
            Picasso.get().load(location.getImgPlace()).into(holder.imgPlace);
            holder.img1.setVisibility(View.GONE);
        }




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (a){
                    case 1:
                        AddLocationActivity.IMG_PLACE = location.getImgPlace();
                        AddLocationActivity.nameIMG_PLACE = location.getNamePlace();
                        AddLocationActivity.ID = location.getId();
                        break;
                    case 2:
                        AddLocationActivity.IMG_1 = location.getImg1();
                        AddLocationActivity.nameIMG_1 = location.getTextImg1();
                        break;
                    case 3:
                        AddLocationActivity.IMG_2 = location.getImg2();
                        AddLocationActivity.nameIMG_2 = location.getTextImg2();
                        break;
                }
                ((Activity) context).finish();
            }
        });



    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class ChoosePhotoLocationHolder extends RecyclerView.ViewHolder {
        private ImageView imgPlace,img1;
        public ChoosePhotoLocationHolder(@NonNull View itemView) {
            super(itemView);
            imgPlace = itemView.findViewById(R.id.imgPlace);
            img1 = itemView.findViewById(R.id.img1);
        }
    }
}
