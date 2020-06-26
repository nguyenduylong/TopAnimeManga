package com.duylong.animemangacollection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duylong.animemangacollection.R;
import com.duylong.animemangacollection.model.Anime;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AnimeAdapter extends BaseAdapter {

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
        Anime item = this.animeList.get(i);
        View layout  = this.layoutInflater.inflate(R.layout.anime_item, viewGroup, false);
        ShapeableImageView animeImage = (ShapeableImageView) layout.findViewById(R.id.anime_image);
        TextView animeTitle = (TextView) layout.findViewById(R.id.anime_title);
        TextView startDate = (TextView) layout.findViewById(R.id.start_date);
        animeTitle.setText(item.getShortTitle());
        startDate.setText(item.getStartDate());
        Glide.with(this.context).load(item.getImageUrl()).into(animeImage);

        return layout;
    }
}
