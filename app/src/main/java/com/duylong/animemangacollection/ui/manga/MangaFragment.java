package com.duylong.animemangacollection.ui.manga;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.duylong.animemangacollection.adapter.MangaAdapter;
import com.duylong.animemangacollection.constant.APIEndpoint;
import com.duylong.animemangacollection.model.Manga;
import com.duylong.animemangacollection.util.AnimeUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MangaFragment extends Fragment {

    private RequestQueue requestQueue;

    private GridView mangaGridView;

    private ArrayList<Manga> mangaArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_manga, container, false);

        mangaGridView = root.findViewById(R.id.manga_list);

        requestQueue = Volley.newRequestQueue(getContext());

        mangaArrayList = new ArrayList<Manga>();
        getTopList();

        return root;
    }

    public void getTopList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, APIEndpoint.TOP_MANGA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJson = new JSONObject(response);
                            JSONArray topContents = responseJson.getJSONArray("top");
                            for (int i = 0; i < topContents.length(); i++) {
                                JSONObject mangaItem = topContents.getJSONObject(i);
                                Manga mangaObject = AnimeUtil.convertJsonToMangaObject(mangaItem);
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