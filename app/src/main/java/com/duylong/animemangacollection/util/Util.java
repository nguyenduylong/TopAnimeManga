package com.duylong.animemangacollection.util;

import android.text.TextUtils;

import com.duylong.animemangacollection.model.Anime;
import com.duylong.animemangacollection.model.Manga;
import com.exblr.dropdownmenu.DropdownListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Util {

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

    public static final List<DropdownListItem> convertArrayToListDropdownItem(String[] categories) {
        ArrayList<DropdownListItem> categoryList = new ArrayList<DropdownListItem>();
        for (int i = 0; i < categories.length; i++) {
            categoryList.add(new DropdownListItem(i + 1, categories[i]));
        }
        return categoryList;
    }

    public static final String getTitle(JSONObject responseJson, String defaultTitle) throws JSONException {
        String title = "";
        String engTitle = responseJson.getString("title_english");
        String japanTitle = responseJson.getString("title_japanese");
        if (!engTitle.equals("") && !engTitle.equals("null")) {
            title = title + engTitle;
        }
        if (!japanTitle.equals("") && !japanTitle.equals("null")) {
            title = title + "(" + japanTitle + ")";
        }
        if (title.equals("")) {
            return defaultTitle;
        }
        return title;
    }

    public static final String getDetailImageUrl(JSONObject responseJson, String defaultUrl) throws JSONException {
        String imageUrl = responseJson.getString("image_url");
        if (imageUrl.equals("null") || imageUrl.equals("")) {
            return defaultUrl;
        }
        return imageUrl;
    }

    public static final String getDescription(JSONObject responseJson) throws JSONException {
        String description = "<b>● Description : </b>";
        description = description + responseJson.getString("synopsis");
        return description;
    }

    public static final float getRatingScore(JSONObject responseJson) throws JSONException {
        String score = responseJson.getString("score");
        if (score.equals("null") || score.equals("")) {
            return 0;
        }
        return Float.parseFloat(score);
    }

    public static final String getGenres(JSONObject responseJson) throws JSONException {
        String[] genresStrs;
        JSONArray genres = responseJson.getJSONArray("genres");
        genresStrs = new String[genres.length()];
        for (int i = 0; i < genres.length(); i++) {
            JSONObject genreItem = (JSONObject) genres.get(i);
            genresStrs[i] = genreItem.getString("name");
        }
        return "<b>● Genres : </b>" + TextUtils.join(", ", genresStrs);
    }

    public static final String getAuthors(JSONObject responseJson) throws JSONException {
        String[] authorStrs;
        JSONArray authors = responseJson.getJSONArray("authors");
        authorStrs = new String[authors.length()];
        for (int i = 0; i < authors.length(); i++) {
            JSONObject authorItem = (JSONObject) authors.get(i);
            authorStrs[i] = authorItem.getString("name");
        }
        return "<b>● Authors : </b>" + TextUtils.join(", ", authorStrs);
    }

    public static final String getProducers(JSONObject responseJson) throws JSONException {
        String[] producerStrs;
        JSONArray producers = responseJson.getJSONArray("producers");
        producerStrs = new String[producers.length()];
        for (int i = 0; i < producers.length(); i++) {
            JSONObject producersItem = (JSONObject) producers.get(i);
            producerStrs[i] = producersItem.getString("name");
        }
        return "<b>● Producers : </b>" + TextUtils.join(", ", producerStrs);
    }
}
