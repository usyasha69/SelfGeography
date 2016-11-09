package com.example.pk.selfgeography.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.pk.selfgeography.database.DatabaseManager;
import com.example.pk.selfgeography.models.CountryModel;
import com.example.pk.selfgeography.net.Retrofit;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InsertCountriesDataLoader extends AsyncTaskLoader<Object> {
    private DatabaseManager databaseManager;

    public InsertCountriesDataLoader(Context context) {
        super(context);

        databaseManager = new DatabaseManager(context);
    }

    @Override
    public Object loadInBackground() {
        Retrofit.getCountries(new Callback<ArrayList<CountryModel>>() {
            @Override
            public void success(ArrayList<CountryModel> countryModels, Response response) {
                databaseManager.saveCountriesData(countryModels);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        return null;
    }
}
