package com.duylong.animemangacollection.ui.manga;

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
import com.duylong.animemangacollection.adapter.CategoryFilterAdapter;
import com.duylong.animemangacollection.adapter.MangaAdapter;
import com.duylong.animemangacollection.constant.APIEndpoint;
import com.duylong.animemangacollection.model.Manga;
import com.duylong.animemangacollection.util.Util;
import com.exblr.dropdownmenu.DropdownListItem;
import com.exblr.dropdownmenu.DropdownMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MangaFragment extends Fragment {

    private RequestQueue requestQueue;

    private GridView mangaGridView;

    private ArrayList<Manga> mangaArrayList;

    private String[] mangaCategories;

    private CategoryFilterAdapter categoryFilterAdapter;

    private DropdownMenu dropdownMenu;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_manga, container, false);

        mangaGridView = root.findViewById(R.id.manga_list);

        requestQueue = Volley.newRequestQueue(getContext());

        mangaArrayList = new ArrayList<Manga>();

        mangaCategories = getResources().getStringArray(R.array.manga_categories);

        ArrayList<DropdownListItem> categoryList = (ArrayList<DropdownListItem>) Util.convertArrayToListDropdownItem(mangaCategories);
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
        dropdownMenu.setCurrentTitle(mangaCategories[0]);

        getTopList(mangaCategories[0]);

        return root;
    }

    public void getTopList(String category) {
        category = category.toLowerCase();
        if (category.equals("popularity")) {
            category = "bypopularity";
        }

        String top_url = APIEndpoint.TOP_MANGA_URL.replace("{manga_category}", category);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, top_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJson = new JSONObject(response);
                            JSONArray topContents = responseJson.getJSONArray("top");
                            mangaArrayList = new ArrayList<Manga>();
                            for (int i = 0; i < topContents.length(); i++) {
                                JSONObject mangaItem = topContents.getJSONObject(i);
                                Manga mangaObject = Util.convertJsonToMangaObject(mangaItem);
                                mangaArrayList.add(mangaObject);
                            }
                           MangaAdapter animeAdapter = new MangaAdapter(getContext(), mangaArrayList);
                           mangaGridView.setAdapter(animeAdapter);
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