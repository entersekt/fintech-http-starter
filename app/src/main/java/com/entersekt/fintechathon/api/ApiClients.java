package com.entersekt.fintechathon.api;

import com.entersekt.fintechathon.http.BuildConfig;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

class ApiClients {

    private static Map<String, Retrofit> clients = new HashMap<>();

    private ApiClients() {
        // Private constructor to prevent accidental instantiation.
    }

    static synchronized Retrofit getSpringApiClient() {
        return getClient(BuildConfig.SPRING_SERVER_URL);
    }

    static synchronized Retrofit getMockyApiClient() {
        return getClient(BuildConfig.MOCKY_SERVER_URL);
    }

    private static Retrofit getClient(String basUrl) {
        Retrofit client = clients.get(basUrl);
        if (client == null) {
            client = new Retrofit.Builder()
                    .baseUrl(basUrl)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();
            clients.put(basUrl, client);
        }
        return client;
    }
}
