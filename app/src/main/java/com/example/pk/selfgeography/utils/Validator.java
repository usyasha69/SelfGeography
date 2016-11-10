package com.example.pk.selfgeography.utils;

import android.content.Context;

import com.example.pk.selfgeography.database.DatabaseManager;
import com.example.pk.selfgeography.models.CountryModel;

import java.util.ArrayList;

public class Validator {
    private Context context;

    public Validator(Context context) {
        this.context = context;
    }

    /**
     * This method checked if password is validate.
     *
     * @param password - password
     * @return - is validate
     */
    public boolean isValidatePassword(String password) {
        return password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?!.*\\s).{8,}");
    }

    /**
     * This method checked if country is validate.
     *
     * @param country - country of user
     * @return - is validate
     */
    public boolean isValidateCountry(String country) {
        boolean isValidate = false;

        DatabaseManager databaseManager = new DatabaseManager(context);
        ArrayList<CountryModel> countryModels = databaseManager.readCountriesData();

        for (CountryModel countryModel : countryModels) {
            if (country.equals(countryModel.name)) {
                isValidate = true;
            }
        }

        return isValidate;
    }
}
