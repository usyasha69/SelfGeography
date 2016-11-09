package com.example.pk.selfgeography.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.pk.selfgeography.database.DatabaseManager;

public class CountriesDataExistLoader extends AsyncTaskLoader<Boolean> {
    private DatabaseManager databaseManager;


    public CountriesDataExistLoader(Context context) {
        super(context);

        databaseManager = new DatabaseManager(context);
    }

    @Override
    public Boolean loadInBackground() {
        return databaseManager.isCountriesDataExists();
    }
}
