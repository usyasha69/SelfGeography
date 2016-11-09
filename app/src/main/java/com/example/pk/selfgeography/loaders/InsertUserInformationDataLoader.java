package com.example.pk.selfgeography.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.pk.selfgeography.database.DatabaseManager;
import com.example.pk.selfgeography.models.UserInformationModel;

public class InsertUserInformationDataLoader extends AsyncTaskLoader<Object> {
    private DatabaseManager databaseManager;
    private UserInformationModel userInformationModel;

    public InsertUserInformationDataLoader(Context context, UserInformationModel userInformationModel) {
        super(context);

        databaseManager = new DatabaseManager(context);
        this.userInformationModel = userInformationModel;
    }

    @Override
    public Object loadInBackground() {
        databaseManager.saveUserInformation(userInformationModel);
        return null;
    }
}
