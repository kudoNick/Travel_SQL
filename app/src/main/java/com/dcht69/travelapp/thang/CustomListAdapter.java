package com.dcht69.travelapp.thang;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dcht69.travelapp.R;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private final int layout;
    private final List<Country> list;
    private final Context context;

    public CustomListAdapter(TravelVietNamAct context, int layout, List<Country> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    public CustomListAdapter(TravelForeginAct context, int layout, List<Country> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            holder.imgTravel = view.findViewById(R.id.imgTravel);
            holder.txtvName = view.findViewById(R.id.txtvName);
            holder.imgSearch = view.findViewById(R.id.imgSet);
            holder.imgSet = view.findViewById(R.id.imgSet);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final Country country = list.get(i);
        holder.txtvName.setText(country.getName());
        holder.imgTravel.setImageResource(country.getImg());
        holder.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsVietActivity.class);
                intent.putExtra("Chi tiết", country);
                context.startActivity(intent);
            }
        });
        holder.imgSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsForeginAct.class);
                intent.putExtra("Chi tiết", country);
                context.startActivity(intent);
            }
        });
        return view;
    }


    class ViewHolder {
        ImageView imgTravel;
        TextView txtvName;
        ImageView imgSearch;
        ImageView imgSet;
    }

}