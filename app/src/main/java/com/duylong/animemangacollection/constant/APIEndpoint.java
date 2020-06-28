package com.duylong.animemangacollection.constant;

public class APIEndpoint {
    // anime api
    public static final String TOP_ANIME_API_URL = "https://api.jikan.moe/v3/top/anime/1/{anime_category}";

    //anime detail
    public static final String DETAIL_API_URL = "https://api.jikan.moe/v3/{type}/{item_id}";

    //maga api
    public static final String TOP_MANGA_URL = "https://api.jikan.moe/v3/top/manga/1/{manga_category}";

    // api message,
    public static final String API_FAILED = "Api Failed!";
}
