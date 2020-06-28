package com.duylong.animemangacollection.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duylong.animemangacollection.DetailActivity;
import com.duylong.animemangacollection.R;
import com.duylong.animemangacollection.model.Anime;
import com.google.android.material.imageview.ShapeableImageView;
import com.skydoves.transformationlayout.TransformationCompat;
import com.skydoves.transformationlayout.TransformationLayout;

import java.util.ArrayList;


public class AnimeAdapter extends BaseAdapter{

    private ArrayList<Anime> animeList;
    private LayoutInflater layoutInflater;
    private Context context;

    public AnimeAdapter(Context context, ArrayList<Anime> animeList) {
        this.animeList = animeList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.animeList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.animeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AnimeHolder holder;
        View layout  = this.layoutInflater.inflate(R.layout.anime_item, viewGroup, false);
        final TransformationLayout transformationLayout = layout.findViewById(R.id.transformation_layout);
        holder = new AnimeHolder(layout);
        layout.setTag(holder);
        holder.bind(i);
        final Anime item = this.animeList.get(i);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detailItem", item);
                TransformationCompat.INSTANCE.startActivity(transformationLayout, intent);
            }
        });
        return layout;
    }

    private class AnimeHolder{
        private ShapeableImageView animeImage;
        private TextView animeTitle;
        private TextView startDate;
        public AnimeHolder(View view) {
            animeImage = (ShapeableImageView) view.findViewById(R.id.anime_image);
            animeTitle = (TextView) view.findViewById(R.id.anime_title);
            startDate = (TextView) view.findViewById(R.id.start_date);
        }

        public void bind(int position) {
            Anime item = animeList.get(position);
            animeTitle.setText(item.getShortTitle());
            startDate.setText(item.getStartDate());
            Glide.with(context).load(item.getImageUrl()).into(animeImage);
        }
    }
}
