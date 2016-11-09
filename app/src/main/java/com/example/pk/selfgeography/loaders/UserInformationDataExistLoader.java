package com.example.pk.selfgeography.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.pk.selfgeography.database.DatabaseManager;

public class UserInformationDataExistLoader extends AsyncTaskLoader<Boolean> {
    private DatabaseManager databaseManager;
    private String putName;

    public UserInformationDataExistLoader(Context context, String putName) {
        super(context);
        this.putName = putName;

        databaseManager = new DatabaseManager(context);
    }

    @Override
    public Boolean loadInBackground() {
        return databaseManager.isExistUser(putName);
    }
}
