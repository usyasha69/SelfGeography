package com.example.pk.selfgeography.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.pk.selfgeography.utils.Validator;

public class ValidateUserInformationFieldsLoader extends AsyncTaskLoader<Boolean> {
    private String putName;
    private String putPassword;
    private String putCountry;

    public ValidateUserInformationFieldsLoader(Context context, String putName, String putPassword, String putCountry) {
        super(context);
        this.putName = putName;
        this.putPassword = putPassword;
        this.putCountry = putCountry;
    }

    @Override
    public Boolean loadInBackground() {
        boolean result = false;

        Validator validator = new Validator(getContext());

        if (!putName.isEmpty() && validator.isValidatePassword(putPassword)
                && validator.isValidateCountry(putCountry)) {
            result = true;
        }

        return result;
    }
}
