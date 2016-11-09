package com.example.pk.selfgeography.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.pk.selfgeography.database.DatabaseManager;
import com.example.pk.selfgeography.models.CountryModel;

import java.util.ArrayList;

public class GetCountriesDataLoader extends AsyncTaskLoader<ArrayList<CountryModel>> {
    private DatabaseManager databaseManager;

    public GetCountriesDataLoader(Context context) {
        super(context);

        databaseManager = new DatabaseManager(context);
    }

    @Override
    public ArrayList<CountryModel> loadInBackground() {
        return databaseManager.readCountriesData();
    }
}
