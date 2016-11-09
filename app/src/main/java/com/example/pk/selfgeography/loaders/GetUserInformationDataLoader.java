package com.example.pk.selfgeography.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.pk.selfgeography.database.DatabaseManager;
import com.example.pk.selfgeography.models.UserInformationModel;

public class GetUserInformationDataLoader extends AsyncTaskLoader<UserInformationModel> {
    private DatabaseManager databaseManager;
    private String putName;

    public GetUserInformationDataLoader(Context context, String putName) {
        super(context);
        this.putName = putName;

        databaseManager = new DatabaseManager(context);
    }

    @Override
    public UserInformationModel loadInBackground() {
        return databaseManager.readUserInformation(putName);
    }
}
