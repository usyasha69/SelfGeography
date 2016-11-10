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
    private ArrayList<CountryModel> countries;

    public InsertCountriesDataLoader(Context context, ArrayList<CountryModel> countries) {
        super(context);

        this.countries = countries;
        databaseManager = new DatabaseManager(context);
    }

    @Override
    public Object loadInBackground() {
        databaseManager.saveCountriesData(countries);
        return null;
    }
}
