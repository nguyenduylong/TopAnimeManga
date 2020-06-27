package com.duylong.animemangacollection.ui.anime;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.duylong.animemangacollection.R;
import com.duylong.animemangacollection.adapter.AnimeAdapter;
import com.duylong.animemangacollection.adapter.CategoryFilterAdapter;
import com.duylong.animemangacollection.constant.APIEndpoint;
import com.duylong.animemangacollection.model.Anime;
import com.duylong.animemangacollection.util.AnimeUtil;
import com.exblr.dropdownmenu.DropdownListItem;
import com.exblr.dropdownmenu.DropdownMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnimeFragment extends Fragment {

    private RequestQueue requestQueue;

    private ArrayList<Anime> animeList;

    private GridView animeGridView;

    private String[] filterCategories;

    private DropdownMenu dropdownMenu;

    private CategoryFilterAdapter categoryFilterAdapter;

    private AnimeAdapter animeAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_anime, container, false);

        animeGridView = root.findViewById(R.id.anime_list);

        requestQueue = Volley.newRequestQueue(getContext());
        animeList = new ArrayList<Anime>();


        filterCategories = getResources().getStringArray(R.array.anime_categories);
        ArrayList<DropdownListItem> categoryList = (ArrayList<DropdownListItem>) AnimeUtil.convertArrayToListDropdownItem(filterCategories);
        categoryFilterAdapter = new CategoryFilterAdapter(getContext(), categoryList);

        dropdownMenu = (DropdownMenu) root.findViewById(R.id.category_filter);

        View filterView = getLayoutInflater().inflate(R.layout.category_filter_layout, null, false);
        GridView gridView = (GridView) filterView.findViewById(R.id.category_gv);

        gridView.setAdapter(categoryFilterAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DropdownListItem item = categoryFilterAdapter.setSelectedItem(i);
                dropdownMenu.setCurrentTitle(item.getText());
                dropdownMenu.dismissCurrentPopupWindow();
                getTopList(item.getText());
            }
        });

        dropdownMenu.add("Category Filter", filterView);

        //set default value
        categoryFilterAdapter.setSelectedItem(0);
        dropdownMenu.setCurrentTitle(filterCategories[0]);

        getTopList(filterCategories[0]);

        return root;
    }

    public void getTopList(String category) {
        category = category.toLowerCase();
        if (category.equals("popularity")) {
            category = "bypopularity";
        }

        String top_url = APIEndpoint.TOP_ANIME_API_URL.replace("{anime_category}", category);

        Log.i("top url", top_url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, top_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJson = new JSONObject(response);
                            JSONArray topContents = responseJson.getJSONArray("top");
                            animeList = new ArrayList<Anime>();
                            for (int i = 0; i < topContents.length(); i++) {
                                JSONObject animeItem = topContents.getJSONObject(i);
                                Anime animeObject = AnimeUtil.convertJsonToAnimeObject(animeItem);
                                animeList.add(animeObject);
                            }
                            Log.i("first anime", animeList.get(0).getTitle());
                            animeAdapter = new AnimeAdapter(getContext(), animeList);
                            animeGridView.setAdapter(animeAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("response error", error.toString());
                        Toast.makeText(getContext(), APIEndpoint.API_FAILED, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        // Add the request to the RequestQueue.
        this.requestQueue.add(stringRequest);
    }

}