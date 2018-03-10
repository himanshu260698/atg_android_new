package com.ATG.World.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chetan on 06-12-2017.
 */

public class AtgClient {
    public static final String TEST_BASE_URL = "http://atg.party/";
    public static final String LIVE_BASE_URL = "https://www.atg.world/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(LIVE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}