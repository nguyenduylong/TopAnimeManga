package com.duylong.animemangacollection.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.duylong.animemangacollection.constant.APIEndpoint;
import com.duylong.animemangacollection.model.Anime;
import com.duylong.animemangacollection.model.Manga;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnimeUtil {

    public static final Anime convertJsonToAnimeObject(JSONObject animeItem) throws JSONException {
        int id = animeItem.getInt("mal_id");
        String title = animeItem.getString("title");
        Anime animeObject = new Anime(id, title);
        if (animeItem.has("url")) {
            animeObject.setUrl(animeItem.getString("url"));
        }
        if (animeItem.has("image_url")) {
            animeObject.setImageUrl(animeItem.getString("image_url"));
        }
        if (animeItem.has("rank")) {
            animeObject.setRank(animeItem.getInt("rank"));
        }
        if (animeItem.has("start_date")) {
            animeObject.setStartDate(animeItem.getString("start_date"));
        }
        if (animeItem.has("end_date")) {
            animeObject.setEndDate(animeItem.getString("end_date"));
        }
        if (animeItem.has("score")) {
            animeObject.setScore(animeItem.getDouble("score"));
        }
        if (animeItem.has("episodes")) {
            String episodes = animeItem.getString("episodes");
            if (episodes != "null" && !episodes.equals("")) {
                animeObject.setEpisodeCounts(Integer.parseInt(episodes));
            }
        }

        return animeObject;
    }

    public static final Manga convertJsonToMangaObject(JSONObject mangaItem) throws JSONException {
        int id = mangaItem.getInt("mal_id");
        String title = mangaItem.getString("title");
        Manga mangaObject = new Manga(id, title);
        if (mangaItem.has("url")) {
            mangaObject.setUrl(mangaItem.getString("url"));
        }
        if (mangaItem.has("image_url")) {
            mangaObject.setImageUrl(mangaItem.getString("image_url"));
        }
        if (mangaItem.has("rank")) {
            mangaObject.setRank(mangaItem.getInt("rank"));
        }
        if (mangaItem.has("start_date")) {
            mangaObject.setStartDate(mangaItem.getString("start_date"));
        }
        if (mangaItem.has("end_date")) {
            mangaObject.setEndDate(mangaItem.getString("end_date"));
        }
        if (mangaItem.has("score")) {
            mangaObject.setScore(mangaItem.getDouble("score"));
        }

        return mangaObject;
    }
}
