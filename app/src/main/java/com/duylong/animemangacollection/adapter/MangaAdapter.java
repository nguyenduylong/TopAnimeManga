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
import com.duylong.animemangacollection.model.Manga;
import com.google.android.material.imageview.ShapeableImageView;
import com.skydoves.transformationlayout.TransformationCompat;
import com.skydoves.transformationlayout.TransformationLayout;

import java.util.ArrayList;

public class MangaAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Manga> mangaList = new ArrayList<Manga>();

    public MangaAdapter(Context context, ArrayList<Manga> mangaList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.mangaList = mangaList;
    }

    @Override
    public int getCount() {
        return this.mangaList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.mangaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MangaHolder viewHolder;
        View layout  = this.layoutInflater.inflate(R.layout.anime_item, viewGroup, false);
        final TransformationLayout transformationLayout = layout.findViewById(R.id.transformation_layout);
        viewHolder = new MangaHolder(layout);
        layout.setTag(viewHolder);
        viewHolder.bind(i);

        final Manga item = this.mangaList.get(i);
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

    private class MangaHolder{
        private ShapeableImageView mangaImage;
        private TextView mangaTitle;
        private TextView startDate;

        public MangaHolder(View view) {
            mangaImage = (ShapeableImageView) view.findViewById(R.id.anime_image);
            mangaTitle = (TextView) view.findViewById(R.id.anime_title);
            startDate = (TextView) view.findViewById(R.id.start_date);
        }

        public void bind(int position) {
            Manga item = mangaList.get(position);

            mangaTitle.setText(item.getShortTitle());
            startDate.setText(item.getStartDate());
            Glide.with(context).load(item.getImageUrl()).into(mangaImage);

        }
    }
}
