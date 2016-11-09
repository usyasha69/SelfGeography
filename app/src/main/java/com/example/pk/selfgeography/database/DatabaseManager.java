package com.example.pk.selfgeography.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.pk.selfgeography.models.CountryModel;
import com.example.pk.selfgeography.models.UserInformationModel;

import java.util.ArrayList;

public class DatabaseManager {
    private ContentResolver contentResolver;

    public DatabaseManager(Context context) {
        contentResolver = context.getContentResolver();
    }

    /**
     * This method save countries data from API query to database.
     *
     * @param countryModels - array list with country parsing models
     */
    public void saveCountriesData(ArrayList<CountryModel> countryModels) {
        ContentValues[] contentValues = new ContentValues[countryModels.size()];

        for (int i = 0; i < contentValues.length; i++) {
            contentValues[i].put(CountryTable.Columns.NAME, countryModels.get(i).name);
            contentValues[i].put(CountryTable.Columns.CAPITAL, countryModels.get(i).capital);
            contentValues[i].put(CountryTable.Columns.REGION, countryModels.get(i).region);
            contentValues[i].put(CountryTable.Columns.SUB_REGION, countryModels.get(i).subRegion);
            contentValues[i].put(CountryTable.Columns.POPULATION, countryModels.get(i).population);
            contentValues[i].put(CountryTable.Columns.AREA, countryModels.get(i).area);
        }

        contentResolver.bulkInsert(CountryTable.CONTENT_URI, contentValues);
    }

    /**
     * This method read countries data from database.
     *
     * @return - array list with country models
     */
    public ArrayList<CountryModel> readCountriesData() {
        ArrayList<CountryModel> countryModels = new ArrayList<>();

        Cursor cursor = contentResolver.query(CountryTable.CONTENT_URI, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    CountryModel countryModel = new CountryModel();

                    countryModel.name = cursor.getString(CountryTable.ColumnIndices.NAME);
                    countryModel.capital = cursor.getString(CountryTable.ColumnIndices.CAPITAL);
                    countryModel.region = cursor.getString(CountryTable.ColumnIndices.REGION);
                    countryModel.subRegion = cursor.getString(CountryTable.ColumnIndices.SUB_REGION);
                    countryModel.population = cursor.getLong(CountryTable.ColumnIndices.POPULATION);
                    countryModel.area = cursor.getDouble(CountryTable.ColumnIndices.AREA);

                    countryModels.add(countryModel);
                } while (cursor.moveToNext());
            }

            cursor.close();
        }

        return countryModels;
    }

    /**
     * This method checked if table with countries in database is empty.
     *
     * @return - is exists
     */
    public boolean isCountriesDataExists() {
        boolean result = false;

        Cursor cursor = contentResolver.query(CountryTable.CONTENT_URI, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = true;
            }

            cursor.close();
        }

        return result;
    }

    /**
     * This method save user information to database.
     *
     * @param userInformationModel - value of shared preferences item
     */
    public void saveUserInformation(UserInformationModel userInformationModel) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(UserInformationTable.Columns.NAME, userInformationModel.name);
        contentValues.put(UserInformationTable.Columns.PASSWORD, userInformationModel.password);
        contentValues.put(UserInformationTable.Columns.COUNTRY, userInformationModel.country);

        contentResolver.insert(UserInformationTable.CONTENT_URI, contentValues);
    }

    /**
     * This method read user information from database.
     *
     * @param name - user name
     * @return - user information model
     */
    public UserInformationModel readUserInformation(String name) {
        UserInformationModel userInformationModel = new UserInformationModel();

        Cursor cursor = contentResolver.query(UserInformationTable.CONTENT_URI, null, "WHERE " +
                UserInformationTable.Columns.NAME + " =?", new String[] {name}, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                userInformationModel.name = cursor.getString(UserInformationTable.ColumnIndices.NAME);
                userInformationModel.password = cursor.getString(UserInformationTable.ColumnIndices.PASSWORD);
                userInformationModel.country = cursor.getString(UserInformationTable.ColumnIndices.COUNTRY);

            } while (cursor.moveToNext());

            cursor.close();
        }

        return userInformationModel;
    }

    /**
     * This method checked if user is registration.
     *
     * @param name - name of user
     * @return - is exists
     */
    public boolean isExistUser(String name) {
        boolean isExistUser = false;

        Cursor cursor = contentResolver.query(UserInformationTable.CONTENT_URI, null, "WHERE " +
                UserInformationTable.Columns.NAME + " =?", new String[] {name}, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            isExistUser = true;
            cursor.close();
        }

        return isExistUser;
    }
}
