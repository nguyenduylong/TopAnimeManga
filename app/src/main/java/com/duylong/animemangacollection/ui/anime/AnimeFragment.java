package com.duylong.animemangacollection.ui.anime;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.duylong.animemangacollection.R;
import com.duylong.animemangacollection.adapter.AnimeAdapter;
import com.duylong.animemangacollection.constant.APIEndpoint;
import com.duylong.animemangacollection.model.Anime;
import com.duylong.animemangacollection.util.AnimeUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AnimeFragment extends Fragment {

    private RequestQueue requestQueue;

    private ArrayList<Anime> animeList;

    private GridView animeGridView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_anime, container, false);

        animeGridView = root.findViewById(R.id.anime_list);

        requestQueue = Volley.newRequestQueue(getContext());
        animeList = new ArrayList<Anime>();
        getTopList();
        return root;
    }

    public void getTopList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, APIEndpoint.TOP_ANIME_API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJson = new JSONObject(response);
                            JSONArray topContents = responseJson.getJSONArray("top");
                            for (int i = 0; i < topContents.length(); i++) {
                                JSONObject animeItem = topContents.getJSONObject(i);
                                Anime animeObject = AnimeUtil.convertJsonToAnimeObject(animeItem);
                                animeList.add(animeObject);
                            }
                            AnimeAdapter animeAdapter = new AnimeAdapter(getContext(), animeList);
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