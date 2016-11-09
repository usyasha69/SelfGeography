package com.example.pk.selfgeography.net;

import com.example.pk.selfgeography.models.CountryModel;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;

public class Retrofit {
    private static final String COUNTRIES_QUERY_BASE_URL = "https://restcountries.eu";
    private static CountriesAPI countriesAPI;

    static {
        initialize();
    }

    private interface CountriesAPI {
        @GET("/rest/v1/all")
        void getCountries(Callback<ArrayList<CountryModel>> callback);
    }

    private static void initialize() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(COUNTRIES_QUERY_BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        countriesAPI = adapter.create(CountriesAPI.class);
    }

    public static void getCountries(Callback<ArrayList<CountryModel>> callback) {
        countriesAPI.getCountries(callback);
    }
}
