package com.example.pk.selfgeography.utils;

import android.content.Context;

import com.example.pk.selfgeography.models.CountryParsingModel;

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
        int validate = 0;

        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'
                , 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (char c : alphabet) {
            if (password.contains(String.valueOf(c))) {
                validate++;
                break;
            }
        }

        for (int number : numbers) {
            if (password.contains(String.valueOf(number))) {
                validate++;
                break;
            }
        }

        if (password.length() > 8) {
            validate++;
        }

        return validate == 3;
    }

    /**
     * This method checked if country is validate.
     *
     * @param country - country of user
     * @return - is validate
     */
    public boolean isValidateCountry(String country) {
        boolean isValidate = false;

        DataKeeper dataKeeper = new DataKeeper(context);
        ArrayList<CountryParsingModel> countryParsingModels = dataKeeper.readData();

        for (CountryParsingModel countryParsingModel : countryParsingModels) {
            if (country.equals(countryParsingModel.name)) {
                isValidate = true;
            }
        }

        return isValidate;
    }
}
